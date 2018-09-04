package chapter08; // compiler

import java.io.*;
import java.net.*;
import javax.tools.*;

/**
 * {@code ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br />
 * {@link CompilerTest} class Listing 8.7 <br />
 * {@link StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
 * {@link ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
 * Listing 8.6 <br />
 * {@link MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
 * @version 1.00 2007-11-02 
 * @author Cay Horstmann
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject {
	private ByteArrayOutputStream stream;
	
	/**
	 * Constructs a new ByteArrayJavaClass. <br />
	 * @param name the name of the class file represented by this file object <br />
	 */
	public ByteArrayJavaClass(String name) {
		super(URI.create("bytes:///" + name), Kind.CLASS);
		stream = new ByteArrayOutputStream();
	}
	
	public OutputStream openOutputStream() throws IOException {
		return stream;
	}
	
	public byte[] getBytes() {
		return stream.toByteArray();
	}
}
