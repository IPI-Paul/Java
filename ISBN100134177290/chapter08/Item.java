package chapter08;  // set

import java.util.*;
/**
import byetcodeAnnotations.*;
 */

/**
 * {@code Item} class Listing 8.13 <br />
 * {@link SetTest} class Listing 8.14 <br />
 * {@link EntryLogger} class extends ClassVisitor Listing 8.12 <br />
 * An item with a description and a part number. <br />
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class Item {
	private String description;
	private int partNumber;
	
	/**
	 * Constructs and item. <br />
	 * @param aDescription the item's description <br />
	 * @param aPartNumber the item's part number <br />
	 */
	public Item(String aDescription, int aPartNumber) {
		description = aDescription;
		partNumber = aPartNumber;
	}
	
	/**
	 * Gets the description of this item. <br />
	 * @return the description <br />
	 */
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "[description=" + description + ", partNumber=" + partNumber + "]";
	}
	
	@LogEntry(logger = "com.horstmann")
	public boolean equals(Object otherObject) {
		if (this == otherObject) return true;
		if (otherObject == null) return false;
		if (getClass() != otherObject.getClass()) return false;
		Item other = (Item) otherObject;
		return Objects.equals(description, other.description) && partNumber == other.partNumber;
	}
	
	@LogEntry(logger = "com.horstmann")
	public int hashCode() {
		return Objects.hash(description, partNumber);
	}
}
