package chapter08;  // script

import java.awt.*;
import java.beans.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.script.*;
import javax.swing.*;
import ipi.*;

/**
 * {@code ScriptTest} class Listing 8.1 <br />
 * {@link ButtonFrame} extends JFrame Listing 8.2 <br />
 * This program demonstrates how to use scripting for Java GUI programming. <br />
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class ScriptTest {
	private static final String MAIN_CLASS = "chapter08.Chapter08";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ScriptEngineManager manager = new ScriptEngineManager();
				String language;
				if (args == null || args.length == 0) {
					System.out.println("Available factories: ");
					for (ScriptEngineFactory factory : manager.getEngineFactories()) 
						System.out.println(factory.getEngineName());
					
					language = "nashorn";
				} else 
					language = args[0];
				
				final ScriptEngine engine = manager.getEngineByName(language);
				if (engine == null) {
					System.err.println("No engine for " + language);
					/** 
					System.exit(1);
					 */
					Views.openWindowOpener(MAIN_CLASS, message);
				}
				
				/**
				final String frameClassName = args.length < 2 ? "buttons1.ButtonFrame" : args[1];
				 */
				final String frameClassName;
				if (args != null) 
					frameClassName = args.length < 2 ? "chapter08.ButtonFrame" : args[1];
				else
					frameClassName = "chapter08.ButtonFrame";
				JFrame frame = (JFrame) Class.forName(frameClassName).newInstance();
				InputStream in = frame.getClass().getResourceAsStream("init." + language);
				if (in != null) engine.eval(new InputStreamReader(in));
				Map<String, Component> components = new HashMap<>();
				getComponentBindings(frame, components);
				components.forEach((name, c) -> engine.put(name, c));
				
				final Properties events = new Properties();
				/**
				in = frame.getClass().getResourceAsStream(language + ".properties");
				 */
				in = new Loaders().getInputStream(
						"sourceFiles/properties/" + language + "-ch08.properties"
						);
				events.load(in);
				
				for (final Object e : events.keySet()) {
					String[] s = ((String) e).split("\\.");
					addListener(s[0], s[1], (String) events.get(e), engine, components);
				}
				frame.setTitle("Script Test");
				/**
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 */
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			} catch (ReflectiveOperationException | IOException | ScriptException | 
					IntrospectionException ex) {
				ex.printStackTrace();
			}
		});
	}
	
	/**
	 * Gathers all named components in a container. <br />
	 * @param c the component <br />
	 * @param namedComponents a map into which to enter the component names and components <br />
	 */
	private static void getComponentBindings(Component c, Map<String, Component> namedComponents) {
		String name = c.getName();
		if (name != null) {namedComponents.put(name, c);}
		if (c instanceof Container) {
			for (Component child : ((Container) c).getComponents()) 
				getComponentBindings(child, namedComponents);
		}
	}
	
	/**
	 * Adds a listener to an object whose listener method executes a script. <br />
	 * @param beanName the name of the bean to which the listener should be added <br />
	 * @param eventName the name of the listener type, such as "action" or "change" <br />
	 * @param scriptCode the script code to be executed <br />
	 * @param engine the engine that executes the code <br />
	 * @param bindings the bindings for the execution <br />
	 * @throws IntrospectionException <br />
	 */
	private static void addListener(String beanName, String eventName, final String scriptCode,
		final ScriptEngine engine, Map<String, Component> components) throws 
		ReflectiveOperationException, IntrospectionException {
		Object bean = components.get(beanName);
		EventSetDescriptor descriptor = getEventSetDescriptor(bean, eventName);
		if (descriptor == null) return;
		descriptor.getAddListenerMethod().invoke(bean, Proxy.newProxyInstance(null, new Class[] {
				descriptor.getListenerType()
		}, (proxy, method, args) -> {
			engine.eval(scriptCode);
			return null;
		}));
	}
	
	private static EventSetDescriptor getEventSetDescriptor(Object bean, String eventName) throws 
		IntrospectionException {
		for (EventSetDescriptor descriptor : Introspector.getBeanInfo(bean.getClass())
				.getEventSetDescriptors())
			if (descriptor.getName().equals(eventName)) return descriptor;
		return null;
	}
}
