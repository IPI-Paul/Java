package chapter12;

import java.io.*;

/**
 * {@code Printf4} class Listing 12.18 <br />
 * chapter12_Printf4.c Listing 12.17 <br />
 * chapter12_Printf4.h Listing 12.18 <br>
 * {@link Printf4Test} class Listing 12.19 <br />
 * @version1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf4 {
	public static native void fprint(PrintWriter ps, String format, double x);
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Printf4");
	}
}
