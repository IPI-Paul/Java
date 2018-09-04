/**
	chapter12_Printf1.c Listing 12.6
	Printf1 class Listing 12.5
	chapter12_Printf1.h Listing 12.5
	Printf1Test class Listing 12.7 
 	@version 1.10 1997-07-01
	@author Cay Horstmann	
	This function assembles a format string "%x.pf" in the variable fmt, then calls printf. It returns the 
	number of characters printed. 
*/

#include "chapter12_Printf1.h"
#include <stdio.h>

JNIEXPORT jint JNICALL Java_chapter12_Printf1_print (JNIEnv* env, jclass cl, jint width, jint precision, 
		jdouble x){
	char fmt[30];
	jint ret;
	sprintf(fmt, "%%%d.%df", width, precision);
	ret = printf(fmt, x);
	fflush(stdout);
	return ret;
}
