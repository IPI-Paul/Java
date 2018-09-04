package chapter09;  // verifier

import java.applet.*;
import java.awt.*;
import ipi.*;

/**
 * {@code VerifierTest} class Listing 9.3 <br />
 * This application demonstrates the bytecode verifier of the virtual machine. If you use a  
 * hex editor to modify the class file, then the virtual machine should detect the tampering. <br />
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */
public class VerifierTest extends Applet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	public static void main(String[] args) {
		System.out.println("1 + 2 == " + fun());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * A function that computes 1 + 2. <br />
	 * @return 3, if the code has not been corrupted <br />
	 */
	public static int fun() {
		int m;
		int n;
		m = 1;
		n = 2;
		// use hex editor to change to "m = 2" in class file
		int r = m + n;
		return r;
	}
	
	public void paint(Graphics g) {
		g.drawString("1 + 2 == " + fun(), 20, 20);
	}
}
