package chapter12;

/**
 * {@code HelloNativeTest} class Listing 12.4 <br />
 * {@link HelloNative} class Listing 12.1 <br />
 * chapter12_HelloNative.h Listing 12.2 <br />
 * chapter12_HelloNative.c Listing 12.3 <br />
 * This program loads a class that calls a C Function, and also loads the library.
 * @version 1.11 2007-10-26
 * @author Cay Horstmann
 */
class HelloNativeTest {
	public static void main(String[] args) {
		HelloNative.greeting();
	}
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_HelloNative");
	}
}
