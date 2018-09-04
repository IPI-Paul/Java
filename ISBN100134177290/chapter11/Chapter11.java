package chapter11;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter11 {
	public static final String CLASS_NAME = Chapter11.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 11.1 Shape Test",
			"Listing 11.2 Stroke Test",
			"Lisiting 11.3-5 Composite Test",
			"Listing 11.6 Render Quality Test",
			"Listing 11.7 Image IO Test",
			"Listing 11.8 Raster Image Test",
			"Listing 11.9 Image Processing Test",
			"Listing 11.10-11 Print Test",
			"Listing 11.12-15 Book Test",
			"Listing 11.16 Print Service Test",
			"Listing 11.17 Text Transfer Test",
			"Listing 11.18 Image Transfer Test",
			"Listing 11.19 Serial Transfer Test",
			"Listing 11.20-21 Swing DnD Test",
			"Listing 11.22 Image List DnD Test",
			"Listing 11.23 Splash Screen Test",
			"Listing 11.24 Desktop App Test",
			"Listing 11.25 System Tray Test"
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
				 * {@link ShapeTest} class Listing 11.1 <br />
				 * {@link ShapeTestFrame} inner class extends JFrame <br />
				 * {@link ShapeComponent} inner class extends JComponent <br />
				 * {@link ShapeMaker} abstract inner class <br />
				 * {@link LineMaker} inner class extends ShapeMaker <br />
				 * {@link RectangleMaker} inner class extends ShapeMaker <br />
				 * {@link RoundRectangleMaker} inner class extends ShapeMaker <br />
				 * {@link EllipseMaker} inner class extends ShapeMaker <br />
				 * {@link ArcMaker} inner class extends ShapeMaker <br />
				 * {@link PolygonMaker} inner class extends ShapeMaker <br />
				 * {@link QuadCurveMaker} inner class extends ShapeMaker <br />
				 * {@link CubicCurveMaker} inner class extends ShapeMaker <br />
				 * This program demonstrates the various 2D shapes. <br />
				 */
				ShapeTest.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link StrokeTest} class Listing 11.2 <br />
				 * {@link StrokeTestFrame} inner class extends JFrame <br />
				 * {@link StrokeComponent} inner class extends JComponent <br />
				 * This program demonstrates different stroke types.
				 */
				StrokeTest.main(null);
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link CompositeTestFrame} class extends JFrame Listing 11.3 <br />
				 * {@link CompositeComponent extends Component Listing 11.4 <br />
				 * {@link Rule} class Listing 11.5 <br />
				 * This frame contains a combo box to choose a composition rule, a slider to change the 
				 * source alpha channel, and a component that shows the composition. 
				 */
				frame = Views.newFrame(new CompositeTestFrame(), "Composite Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link RenderQualityTestFrame} class extends JFrame Listing 11.6 <br />
				 * {@link RenderQualityComponent} inner class extends JComponent <br />
				 * This frame contains buttons to set rendering hints and an image that is drawn with the 
				 * selected hints.
				 */
				frame = Views.newFrame(new RenderQualityTestFrame(), "Render Quality Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link ImageIOFrame} class extends JFrame Listing 11.7 <br />
				 * This frame displays the loaded images. The menu has items for loading and saving files.
				 */
				frame = Views.newFrame(new ImageIOFrame(), "Image IO Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link RasterImageFrame} class extends JFrame Listing 11.8 <br />
				 * This frame shows an image with a Mandelbrot set.
				 */
				frame = Views.newFrame(new RasterImageFrame(), "Raster Image Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link ImageProcessingFrame} class extends JFrame Listing 11.9 <br />
				 * This frame has a menu to load an image and to specify various transformations, and a 
				 * component to show the resulting image.
				 */
				frame = Views.newFrame(new ImageProcessingFrame(), null);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[7]) {
				/**
				 * {@link PrintTestFrame} class extends JFrame Listing 11.10 <br />
				 * {@link PrintComponent} class extends JComponent and implements Printable Listing 11.11 <br />
				 * This frame shows a panel with 2D graphics and buttons to print the graphics and to set 
				 * up the page format.
				 */
				frame = Views.newFrame(new PrintTestFrame(), "Print Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[8]) {
				/**
				 * {@link BookTestFrame} class extends JFrame Listing 11.12 <br />
				 * {@link Banner} class implements Printable Listing 11.13 <br />
				 * {@link CoverPage} inner class implements Printable Listing 11.13 <br />
				 * {@link PrintPreviewDialog} class extends JDialog Listing 11.14 <br />
				 * {@link PrintPreviewCanvas} class extends JComponent Listing 11.15 <br />
				 * This frame has a text field for the banner  text and buttons for printing, page setup, 
				 * and print preview.
				 */
				frame = Views.newFrame(new BookTestFrame(), "Book Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[9]) {
				/**
				 * {@link PrintServiceTest} class Listing 11.16 <br />
				 * This program demonstrates the use of print services.The program lets you print a GIF 
				 * image to the print services that support the GIF document flavor.
				 */
				PrintServiceTest.main(null);
				return;
			}
			else if (exampleType == example[10]) {
				/**
				 * {@link TextTransferFrame} class extends JFrame Listing 11.17 <br />
				 * This frame has a text area and buttons for copying and pasting text.
				 */
				frame = Views.newFrame(new TextTransferFrame(), "Text Transfer Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[11]) {
				/**
				 * {@link ImageTransferFrame} class extends JFrame Listing 11.18 <br />
				 * {@link ImageTransferable} inner class implements Transferable <br />
				 * This frame has an image label and buttons for copying and pasting an image.
				 */
				frame = Views.newFrame(new ImageTransferFrame(), "Image Transfer Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if(exampleType == example[12]) {
				/**
				 * {@link SerialTransferFrame} class extends JFrame Listing 11.19 <br />
				 * {link SerialTransferable} inner class implements Transferable Listing 11.19 <br />
				 * This frame contains a color chooser, and copy and paste buttons.
				 */
				frame = Views.newFrame(new SerialTransferFrame(), "Serial Transfer Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[13]) {
				/**
				 * {@link SwingDnDTest} class Listing 11.20 <br />
				 * {@link SampleComponents} class Listing 11.21 <br />
				 * This program demonstrates the basic Swing support for drag and drop.
				 */
				SwingDnDTest.main(null);
				return;
			}
			else if (exampleType == example[14]) {
				/**
				 * {@link ImageListDnDFrame} class extends JFrame Listing 11.22 <br />
				 * {@link ImageList} inner class extends JList&gt;ImageIcon> Listing 11.22 <br />
				 * {@link ImageListTransferHandler} inner class extends TransferHandler Listing 11.22 <br />
				 * This frame contains 2 JList components that allow images to be dragged between them.
				 */
				frame = Views.newFrame(new ImageListDnDFrame(), "Image List DnD Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[15]) {
				/**
				 * {@link SplashScreenTest} class Listing 11.23 <br />
				 * This program demonstrates the splash screen API.
				 */
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("-splash:sourceFiles/images/Earth.jpg");
				params.add("chapter11.SplashScreenTest");
				ProcessBuilder pb = new ProcessBuilder(params);
				try {
					Process p = pb.start();
					InputStream in = p.getInputStream();
					int ch;
					while ((ch = in.read()) != -1)
						System.out.print((char) ch);
					p.waitFor();
					p.destroyForcibly();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if (exampleType == example[16]) {
				/**
				 * {@link DesktopAppFrame} class extends JFrame Listing 11.24 <br />
				 * This program lets you open, edit, or print a file of your choice, browse a URL, or 
				 * launch your e-mail program.
				 */
				frame = Views.newFrame(new DesktopAppFrame(), "Desktop App Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[17]) {
				/**
				 * {@link SystemTrayTest} class Listing 11.25 <br />
				 * This program demonstrates the system tray API.
				 */
				SystemTrayTest.main(null); 
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

