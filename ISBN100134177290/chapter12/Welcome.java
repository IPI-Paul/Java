package chapter12;

/**
 * {@code Welcome} class ISBN100137081898 Listing 2.1 <br />
 * chapter12_InvocationTest.c Listing 12.20 <br />
 * This program displays a greeting from the authors.
 * @version 1.20 2004-02-28
 * @author Cay Horstmann
*/
public class Welcome {
	public static void main(String[] args) {
		String[] greeting = new String[3];
		greeting[0] = "Welcome to Core Java";
		greeting[1] = "by Cay Horstmann";
		greeting[2] = "and Gary Cornell";
		
		for (String g : greeting) {
			System.out.println(g);
		}
		System.out.flush();
	}
}