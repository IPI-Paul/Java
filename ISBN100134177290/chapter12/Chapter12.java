package chapter12;

import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter12 {
	public static final String CLASS_NAME = Chapter12.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 12.1-4 Hello Native Test",
			"Listing 12.5-7 Print Floating Point Number",
			"Listing 12.8-10 Print String",
			"Listing 12.11-13 Employee Test",
			"Lisiting 12.14-16 Call Java Method from Native Method",
			"Listing 12.17-19 Throwing an Exception from a Native Method",
			"Listing 12.20 C++ JVM Invocation Test",
			"Listing 12.21-23 Win32 Reg Key Test"
			}; 
	private static int runAgain;
	private static String title = "Example Of";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod(CLASS_NAME, args[0]);
			return;
		}

		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
					JOptionPane.QUESTION_MESSAGE, null, example, example[0]);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == example[0]) {
				/**
				 * {@link HelloNativeTest} class Listing 12.4 <br />
				 * {@link HelloNative} class Listing 12.1 <br />
				 * chapter12_HelloNative.h Listing 12.2 <br />
				 * chapter12_HelloNative.c Listing 12.3 <br />
				 * This class calls a C Function.
				 */
				String[] params = {"java","chapter12.HelloNativeTest"};
				Threads.runProcess(params, CLASS_NAME, message);
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link Printf1Test} class Listing 12.7 <br />
				 * {@link Printf1} class Listing 12.5 <br />
				 * chapter12_Printf1.h Listing 12.5 <br />
				 * chapter12_Printf1.c Listing 12.6 <br />
				 * This program uses a native method to print a floating-point number with a given field 
				 * width and precision.
				 */
				String[] params = {"java", "chapter12.Printf1Test"};
				Threads.runProcess(params, CLASS_NAME, message);
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link Printf2Test} class Listing 12.8 <br />
				 * {@link Printf2} class Listing 12.9 <br />
				 * chapter12_Printf2.h Listing 12.9 <br />
				 * chapter12_Printf2.c Listing 12.10 <br />
				 * This program calls the C function <code>sprintf</code>.
				 */
				Printf2Test.main(null);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link EmployeeTest} class Listing 12.11 <br />
				 * {@link Employee} class Listing 12.12 <br />
				 * chapter12_Emplyoee.h Listing 12.12 <br />
				 * chapter12_Employee.c Listing 12.13 <br />
				 * This program calls a C function that implements the <code>raiseSalary</code> method as 
				 * a native method.
				 */
				EmployeeTest.main(null);
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link Printf3Test} class Listing 12.14 <br />
				 * {@link Printf3} class Listing 12.15 <br />
				 * chapter12_Printf3.h Listing 12.15 <br />
				 * chapter12_Printf3.c Listing 12.16 <br />
				 * This program calls Java functions from a native method.
				 */
				Printf3Test.main(null);
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link Printf4Test} class Listing 12.19 <br />
				 * chapter12_Printf4.c Listing 12.17 <br />
				 * {@link Printf4} class Listing 12.18 <br />
				 * chapter12_Printf4.h Listing 12.18 <br>
				 * This program demonstrates how the native method throws an exception when the formatting
				 * string is not valid.
				 */
				Printf4Test.main(null);
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * chapter12_InvocationTest.c Listing 12.20 <br />
				 * {@link Welcome} class ISBN100137081898 Listing 2.1 <br />
				 * this program sets up a virtual machine and calls the <method>main</method> method of 
				 * the <code>Welcome</code> class. 
				 */
				try {
					Process p = Runtime.getRuntime().exec("sourceFiles/exe/chapter12_InvocationTest.exe");
					InputStream is = p.getInputStream();
					int value = -1;
					while ((value = is.read()) != -1)
						System.out.print((char) value);
					p.waitFor();		
				} catch (Exception ex) {}
				Views.openWindowOpener(CLASS_NAME, message);
			}
			else if (exampleType == example[7]) {
				/**
				 * {@link Win32RegKey} class Listing 12.21 <br />
				 * {@link Win32RegKeyNameEnumeration} inner class implements Enumeration&gt;String> 
				 * Listing 12.21 <br />
				 * {@link Win32RegKeyException} inner class extends RuntimeException Listing 12.21 <br /> 
				 * chapter12_Win32RegKey.h Listing 12.21 <br />
				 * chapter12_Win32RegKey.c Listing 12.22 <br />
				 * {@link Win32RegKeyTest} class Listing 12.23 <br />
				 * This program adds three name/value pairs, a string, an integer, and a byte array to a
				 * registry key. 
				 */
				Win32RegKeyTest.main(null);
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication(CLASS_NAME);
	}
}

