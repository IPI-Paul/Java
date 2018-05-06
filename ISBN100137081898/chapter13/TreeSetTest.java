package chapter13;  // treeSet

import java.util.*;
import ipi.*;

/**
 * TreeSetTest class Listing 13.3 <br />
 * Item class Listing 13.4 <br />
 * This program sorts a set of items by comparing their descriptions.
 * @version 1.12 2012-01-26
 * @author Cay Horstmann
 */
public class TreeSetTest {
	private static final String MAIN_CLASS = "chapter13.Chapter13";
	private static String message = "";
	
	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);
		
		SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {
			public int compare(Item a, Item b) {
				String descrA = a.getDescription();
				String descrB = b.getDescription();
				return descrA.compareTo(descrB);
			}
		});
			
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
