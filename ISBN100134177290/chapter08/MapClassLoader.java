package chapter08;  // compiler

import java.util.*;

/**
 * {@code MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
 * {@link CompilerTest} class Listing 8.7 <br />
 * {@link StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
 * {@link ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br />
 * {@link ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
 * Listing 8.6 <br />
 * A class loader that loads classes from a map whose keys are class names and whose values are 
 * byte code arrays. <br />
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public class MapClassLoader extends ClassLoader {
	private Map<String, byte[]> classes;
	
	public MapClassLoader(Map<String, byte[]> classes) {
		this.classes = classes;
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = classes.get(name);
		if (classBytes == null) throw new ClassNotFoundException(name);
		Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
		if (cl == null) throw new ClassNotFoundException(name);
		return cl;
	}
}
