package chapter08; // compiler

import java.net.*;
import javax.tools.*;

/**
 * {@code StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
 * {@link CompilerTest} class Listing 8.7 <br />
 * {@link ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br /> 
 * {@link ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
 * Listing 8.6 <br />
 * {@link MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
 * A Java source that holds the code in a string builder. <br /> 
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public class StringBuilderJavaSource extends SimpleJavaFileObject {
	private StringBuilder code;
	
	/**
	 * Constructs a new StringBuilderJavaSource. <br />
	 * @param name the name of the source file represented by this file object <br />
	 */
	public StringBuilderJavaSource(String name) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		code = new StringBuilder();
	}
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	
	public void append(String str) {
		code.append(str);
		code.append('\n');
	}
}
