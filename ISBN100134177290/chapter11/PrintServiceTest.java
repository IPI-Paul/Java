package chapter11;  // printService

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.print.*;
import java.net.URL;
import javax.swing.JOptionPane;

import ipi.Loaders;
import ipi.Views;

/**
 * {@link PrintServiceTest} class Listing 11.16 <br />
 * This program demonstrates the use of print services.The program lets you print a GIF 
 * image to the print services that support the GIF document flavor. <br />
 * @version 1.10 2007-08-16
 * @author Cay Horstmann
 */
public class PrintServiceTest {
	private static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";
	private static Loaders file;
	
	public static void main(String[] args) {
		/**
		DocFlavor flavor = DocFlavor.URL.GIF;
		 */
		DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
		PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
		/**
		if (args != null && args.length == 0) {
			if (services.length == 0) System.out.println("No printer for flavor " + flavor);
			else {
				System.out.println("specify a file of flavor " + flavor 
						+ "\nand optionally the number of the desired printer.");
				for (int i = 0; i < services.length; i++) 
					System.out.println((i + 1) + ": " + services[i].getName()); 
			}
			System.exit(0);
		}
		*/
		int p = 1;
		List<String> printers = new ArrayList<>();
		for (int i = 0; i < services.length; i++) 
			printers.add(services[i].toString());
		Object printer = JOptionPane.showInputDialog(null, "Please Select a Printer!", "Print To",
				JOptionPane.QUESTION_MESSAGE, null, services, services[0]);
		p = printers.indexOf(printer.toString()) + 1;
		String fileName;
		if (args != null && args.length > 0) 
			fileName = args[0];
		else {
			file = new Loaders();
			Charset cs = StandardCharsets.UTF_8;
			file.setChoice("JS", "img", "", "", cs, "", "http://localhost/java/sourceFiles/images/Earth.jpg",  "For GIF File");
			if (file.getUrl() == null)
				fileName = file.getFilePath();
			else
				fileName = file.getUrl();
		}
		if (args != null && args.length > 1) p = Integer.parseInt(args[1]);
		if (fileName == null) return;
		try (InputStream in = Files.newInputStream(Paths.get(fileName))) {
			Doc doc = new SimpleDoc(in, flavor, null);
			DocPrintJob job = services[p - 1].createPrintJob();
			job.print(doc, null);
		} catch (InvalidPathException ex) {
			try {
				URL url = new URL(fileName);
				InputStream in = url.openStream();
				Doc doc = new SimpleDoc(in, flavor, null);
				DocPrintJob job = services[p - 1].createPrintJob();
				job.print(doc, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
