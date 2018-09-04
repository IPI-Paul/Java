package chapter12;

import java.io.*;

/**
 * {@code Printf3} class Listing 12.15 <br />
 * chapter12_Printf3.h Listing 12.15 <br />
 * {@link Printf3Test} class Listing 12.14 <br />
 * chapter12_Printf3.c Listing 12.16 <br />
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf3 {
	public static native void fprint(PrintWriter out, String format, double x);
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Printf3");
	}
}
