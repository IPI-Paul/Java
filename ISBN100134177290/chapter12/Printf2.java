package chapter12;

/**
 * {@code Printf2} class Listing 12.9 <br />
 * {@link Printf2Test} class Listing 12.8 <br />
 * chapter12_Printf2.h Listing 12.9 <br />
 * chapter12_Printf2.c Listing 12.10 <br />
 * This class contains the native print method.
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf2 {
	public static native String sprint(String format, double x);
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Printf2");
	}
}
