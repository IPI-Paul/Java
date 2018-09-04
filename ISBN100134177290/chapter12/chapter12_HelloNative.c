/*
	chapter12_HelloNative.c Listing 12.3 
	HelloNativeTest class Listing 12.4 
	HelloNative class Listing 12.1 
	chapter12_HelloNative.h Listing 12.2 
	@version 1.10 1997-07-01
	@author Cay Horstmann
*/

#include "chapter12_HelloNative.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_chapter12_HelloNative_greeting(JNIEnv* env, jclass cl) {
	printf("Hello Native World!\n");
	fflush(stdout);
}
	