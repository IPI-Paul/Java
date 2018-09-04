package chapter12;

/**
 * {@code Printf1} class Listing 12.5 <br />
 * chapter12_Printfl.h Listing 12.5 <br />
 * chapter12_Printfl.c Listing 12.6 <br />
 * {@link Printf1Test} class Listing 12.7 <br />
 * This program uses a native method to print a floating-point number with a given field 
 * width and precision.
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf1 {
	public static native int print(int width, int precision, double x);
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Printf1");
	}
}
