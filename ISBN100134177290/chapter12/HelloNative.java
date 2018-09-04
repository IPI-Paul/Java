package chapter12;

/**
 * {@code HelloNative} class Listing 12.1 <br />
 * {@link HelloNativeTest} class Listing 12.4 <br />
 * chapter12_HelloNative.h Listing 12.2 <br />
 * chapter12_HelloNative.c Listing 12.3 <br />
 * This class calls a C Function.
 * @version 1.11 2007-10-26
 * @author Cay Horstmann
 */
class HelloNative {
	public static native void greeting();
}
