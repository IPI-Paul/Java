package chapter08;  // compiler

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.tools.*;
import javax.tools.JavaFileObject.*;
import ipi.*;

/**
 * {@code CompilerTest} class Listing 8.7 <br />
 * {@link StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
 * {@link ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br />
 * {@link ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
 * Listing 8.6 <br />
 * {@link MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
 * The buildSource method in this program builds up this code and places it into a 
 * StringBuilderJavaSource object. That object is passed to the Java compiler. <br /> 
 * @version 1.01 2016-05-10
 * @author Cay Horstmann
 */
public class CompilerTest {
	private static final String MAIN_CLASS = "chapter08.Chapter08";
	private static String message = "";
	
	public static void main(final String[] args) throws IOException, ClassNotFoundException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		final List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();
		
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		
		JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		fileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager) {
			public JavaFileObject getJavaFileForOutput(Location location, 
					final String className, Kind kind, FileObject sibling) throws IOException {
				if (className.startsWith("x.")) {
					ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
					classFileObjects.add(fileObject);
					return fileObject;
				} else 
					return super.getJavaFileForOutput(location, className, kind, sibling);
			}
		};
		/**
		String frameClassName = args.length == 0 ? "buttons2.ButtonFrame" : args[0];
		 */
		String frameClassName;
		if (args != null)
			frameClassName = args.length == 0 ? "chapter08.ButtonFrame2" : args[0];
		else 
			frameClassName = "chapter08.ButtonFrame2";
		JavaFileObject source = buildSource(frameClassName);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, 
				null, null, Arrays.asList(source));
		Boolean result = task.call();
		
		for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) 
			System.out.println(d.getKind() + ": " + d.getMessage(null));
		fileManager.close();
		if (!result) {
			System.out.println("Compilation failed.");
			/**
			System.exit(1);
			 */
			Views.openWindowOpener(MAIN_CLASS, message);
		}
		
		EventQueue.invokeLater(() -> {
			try {
				Map<String, byte[]> byteCodeMap = new HashMap<>();
				for (ByteArrayJavaClass cl : classFileObjects) 
					byteCodeMap.put(cl.getName().substring(1), cl.getBytes());
				ClassLoader loader = new MapClassLoader(byteCodeMap);
				/**
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 */
				JFrame frame = (JFrame) loader.loadClass("x.Frame").newInstance();
				frame.setTitle("Compiler Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	/**
	 * Builds the source for the subclass that implements the addEventHandlers method. <br />
	 * @return a file object containing the source in a string builder <br /> 
	 */
	static JavaFileObject buildSource(String superclassName) throws IOException, ClassNotFoundException {
		StringBuilderJavaSource source = new StringBuilderJavaSource("x.Frame");
		source.append("package x;\n");
		source.append("public class Frame extends " + superclassName + " {");
		source.append("protected void addEventHandlers() {");
		final Properties props = new Properties();
		
		/**
		props.load(Class.forName(superclassName).getResourceAsStream("action.properties"));
		 */
		props.load(new Loaders()
				.getInputStream("sourceFiles/properties/action-ch08.properties"));
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			String beanName = (String) e.getKey();
			String eventCode = (String) e.getValue();
			source.append(beanName + ".addActionListener(event -> {");
			source.append(eventCode);
			source.append("});");
		}
		source.append("}}");
		return source;
	}
}
