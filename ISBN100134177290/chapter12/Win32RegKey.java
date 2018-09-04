package chapter12;

import java.util.*;

/**
 * {@code Win32RegKey} class Listing 12.21 <br />
 * {@link Win32RegKeyNameEnumeration} inner class implements Enumeration&gt;String> Listing 12.21 <br />
 * {@link Win32RegKeyException} inner class extends RuntimeException Listing 12.21 <br />  
 * chapter12_Win32RegKey.h Listing 12.21 <br />
 * chapter12_Win32RegKey.c Listing 12.22 <br />
 * {@link Win32RegKeyTest} class Listing 12.23 <br />
 * A Win32RegKey object can be used to get and set values of a registry key in the Windows registry.
 * @version1.00 1997-07-01
 * @author Cay Horstmann
 */
public class Win32RegKey {
	public static final int HKEY_CLASSES_ROOT = 0x80000000;
	public static final int HKEY_CURRENT_USER = 0x80000001;
	public static final int HKEY_LOCAL_MACHINE = 0x80000002;
	public static final int HKEY_USERS = 0x80000003;
	public static final int HKEY_CURRENT_CONFIG = 0x80000005;
	public static final int HKEY_DYN_DATA = 0x80000006;
	
	private int root;
	private String path;
	
	/**
	 * Gets the value of a registry entry. <br />
	 * @param name the entry name <br />
	 * @return the associated value
	 */
	public native Object getValue(String name);
	
	/**
	 * Sets the value of a registry entry. <br />
	 * @param name the entry name <br />
	 * @param value the new value
	 */
	public native void setValue(String name, Object value);
	
	/**
	 * Construct a registry key object. <br />
	 * @param theRoot one of HKEY_CLASSES_ROOT, HKEY_CURRENT_USER, HKEY_LOCAL_MACHINE, HKEY_USERS, 
	 * HKEY_CURRENT_CONFIG, HKEY_DYN_DATA <br />
	 * @param the Path the registry key path 
	 */
	public Win32RegKey(int theRoot, String thePath) {
		root = theRoot;
		path = thePath;
	}
	
	/**
	 * Enumerates all names of registry entries under the path that this object describes. <br />
	 * @return an enumeration listing all entry names 
	 */
	public Enumeration<String> names() {
		return new Win32RegKeyNameEnumeration(root, path);
	}
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Win32Regkey");
	}
}

class Win32RegKeyNameEnumeration implements Enumeration<String> {
	public native String nextElement();
	public native boolean hasMoreElements();
	private int root;
	private String path;
	private int index = -1;
	private int hkey = 0;
	private int maxsize;
	private int count;
	
	Win32RegKeyNameEnumeration(int theRoot, String thePath) {
		root = theRoot;
		path = thePath;
	}
}

class Win32RegKeyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public Win32RegKeyException() {
	}
	
	public Win32RegKeyException(String why) {
		super(why);
	}
}
