package chapter12;

import java.util.*;

import ipi.Views;

/**
 * {@code Win32RegKeyTest} class Listing 12.23 <br />
 * {@link Win32RegKey} class Listing 12.21 <br />
 * {@link Win32RegKeyNameEnumeration} inner class implements Enumeration&gt;String> 
 * Listing 12.21 <br />
 * {@link Win32RegKeyException} inner class extends RuntimeException Listing 12.21 <br /> 
 * chapter12_Win32RegKey.h Listing 12.21 <br />
 * chapter12_Win32RegKey.c Listing 12.22 <br />
 * This program adds three name/value pairs, a string, an integer, and a byte array to a
 * registry key.
 * @version 1.02 2007-10-26
 * @author Cay Horstmann
 */
public class Win32RegKeyTest {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";

	public static void main(String[] args) {
		Win32RegKey key = new Win32RegKey(Win32RegKey.HKEY_CURRENT_USER, "Software\\JavaSoft\\Prefs\\com\\"
				+ "horstmann\\corejava");
		try {
			key.setValue("Default user", "Harry Hacker");
			key.setValue("Lucky Number", new Integer(13));
			key.setValue("Small primes", new byte[] { 2, 3, 5, 7, 11 });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		try {
			Enumeration<String> e = key.names();
			
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				System.out.print(name + "=");
				
				Object value = key.getValue(name);
				
				if (value instanceof byte[]) 
					for (byte b : (byte[]) value) System.out.print((b & 0xFF) + " ");
				else 
					System.out.print(value);
				
				System.out.println();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
