package ipi;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Filters {
	private static int count;
	private static Object[] objArray;
	
	private static boolean getfilter(String[] filters, Object obj) {
		if (obj != null && filters == null) {
			return true;
		}
		else if(filters != null && filters.length == 3) {
			if (filters[0].equals("eq")) return filters[1].equalsIgnoreCase(filters[2]);
			else if (filters[0].equals("ne")) return !filters[1].equals(filters[2]);
			else if (filters[0].equals("lt")) return filters[1].startsWith(filters[2]);
			else if (filters[0].equals("gt")) return filters[1].endsWith(filters[2]);
			else if (filters[0].equals("contains")) return filters[1].contains(filters[2]);
			else return false;
		}
		else
			return false;
	}
	
	/**
	 * This method filters an object using the filter passed in. <br />
	 */
	public static Stream<Path> getArray(Path[] paths) {
		Stream<Path> path = Arrays.stream(paths).filter(f -> getfilter(null, f));
		setObjArray(path.toArray());
		setCount(Math.toIntExact(objArray.length));
		return path;
	}
	
	/**
	 * This method filters an object using the filter passed in. <br />
	 */
	public static Stream<Path> getArray(String[] filters, Path[] paths) {
		Stream<Path> path = Arrays.stream(paths).filter(f -> getfilter(filters, f));
		setObjArray(path.toArray());
		setCount(Math.toIntExact(objArray.length));
		return path;
	}
	
	/**
	 * This method filters an object using the filter passed in. <br />
	 */
	public static Stream<String> getArray(String[] text) {
		Stream<String> str = Arrays.stream(text).filter(f -> getfilter(null, f));
		setObjArray(str.toArray());
		setCount(Math.toIntExact(objArray.length));
		return str;
	}
	
	/**
	 * This method filters an object using the filter passed in. <br />
	 */
	public static Stream<String> getArray(String[] filters, String[] text) {
		Stream<String> str = Arrays.stream(text).filter(f -> getfilter(filters, f));
		setObjArray(str.toArray());
		setCount(Math.toIntExact(objArray.length));
		return str;
	}

	/**
	 * @return the count
	 */
	public static int getCount(Path[] files) {
		getArray(files);
		return count;
	}

	/**
	 * @return the count
	 */
	public static int getCount(String[] text) {
		getArray(text);
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public static void setCount(int count) {
		Filters.count = count;
	}

	/**
	 * @return the objArray
	 */
	public static Object[] getObjArray(String[] text) {
		getArray(text);
		return objArray;
	}

	/**
	 * @param objArray the objArray to set
	 */
	public static void setObjArray(Object[] objArray) {
		Filters.objArray = objArray;
	}

	public static String toString(String[] text) {
		getArray(text);
		return Arrays.toString(objArray);
	}
}
