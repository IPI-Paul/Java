package chapter08;  // runtimeAnnotations

import java.awt.event.*;
import java.lang.reflect.*;

/**
 * {@code ActionListenerInstaller} class Listing 8.9 <br />
 * {@link ButtonFrame3} class extends JFrame Listing 8.10 <br />
 * {@link ActionListenerFor} annotated interface Listing 8.11 <br />
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
public class ActionListenerInstaller {
	/**
	 * Processes all ActionListenerFor annotations in the given object. <br />
	 * @param obj an object whose methods may have ActionListenerFor annotations <br />
	 */
	public static void processAnnotations(Object obj) {
		try {
			Class<?> cl = obj.getClass();
			for (Method m : cl.getDeclaredMethods()) {
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if (a != null) {
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj), obj, m);
				}
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an action listener that calls a given method. <br />
	 * @param source the event source to which an action listener is added <br />
	 * @param param the implicit parameter of the method that the listener calls <br />
	 * @param m the method that the listener calls <br />
	 */
	public static void addListener(Object source, final Object param, final Method m) throws 
		ReflectiveOperationException {
			InvocationHandler handler = new InvocationHandler() {
				public Object invoke(Object proxy, Method mm, Object[] args) throws Throwable {
					return m.invoke(param);
				}
			};
			
			Object listener = Proxy.newProxyInstance(null, new Class[] {
					java.awt.event.ActionListener.class
			}, handler);
			Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
			adder.invoke(source, listener);
	}
}
