package chapter08; // set

import java.util.*;
import java.util.logging.*;

/**
 * {@code SetTest} class Listing 8.14 <br />
 * {@link EntryLogger} class extends ClassVisitor Listing 8.12 <br />
 * {@link Item} class Listing 8.13 <br />
 * This program demonstrates the power of bytecode engineering. Annotations are used to add 
 * directives to a program, and a bytecode editing tool picks up the directives and modifies 
 * the virtual machine instructions. <br />
 * @version 1.02 2012-01-26
 * @author Cay Horstmann
 */
public class SetTest {
	public static void main(String[] args) {
		Logger.getLogger("com.horstmann").setLevel(Level.FINEST);
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINEST);
		Logger.getLogger("com.horstmann").addHandler(handler);
		
		Set<Item> parts = new HashSet<>();
		parts.add(new Item("Toaster", 1279));
		parts.add(new Item("Microwave", 4104));
		parts.add(new Item("Toaster", 1279));
		System.out.println(parts);
	}
}
