package chapter03;  // transform

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import ipi.*;

/**
 * {@code TransformTest} class Listing 3.15 <br />
 * {@link EmployeeReader} inner class implements {@link XMLReader} <br />
 * <a href="makehtml.xsd">makehtml.xsd</a> Listing 3.13 <br />
 * <a href="makeprop.xsl">makeprop.xsl</a> Listing 3.14 <br />
 * This program demonstrates XSL transformations. It applies a transformation to a 
 * set of employee records. The records are stored in the file 
 * <a href="sourceFiles/dat/employee.dat">employee.dat</a> and turned 
 * into XML format. Specify the stylesheet on the command line, e.g.
 * java chapter03.TransformTest chapter03/makeprop.xsl <br /> 
 * @version 1.03 2016-04-27
 * @author Cay Horstmann
 */
public class TransformTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static Loaders file = new Loaders();
	private static Loaders xsl = new Loaders();
	private static Object choice;
	
	public static void main(String[] args) throws Exception {
		Path path;
		Charset cs = StandardCharsets.UTF_8;

		if (args == null || args.length == 0) {
			file.setChoice("DS", "dat", "", "employee.dat", cs, "", "", "For DAT File");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
		}
		if (args != null && args.length > 0) path = Paths.get(args[0]);
		/**
		else path = Paths.get("transform", "makehtml.xsl");
		 */
		else {
			final String[] choices = {"makehtml.xsl", "makeprop.xsl"};
			choice = JOptionPane.showInputDialog(null, "Please Select an Example!", "Run Type",
							JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			if (choice == null) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
			path = Paths.get("chapter03", choice.toString());
		} 
		/**
		try (InputStream styleIn = Files.newInputStream(path)) {
		 */
		try {
			InputStream styleIn = xsl.getInputStream(path);
			StreamSource styleSource = new StreamSource(styleIn);
			
			Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			/**
			try (InputStream docIn = Files.newInputStream(Paths.get("transform", "employee.dat"))) {
			 */
			InputStream docIn = file.getInputStream(file.getPath()); 
			t.transform(
					new SAXSource(new EmployeeReader(), new InputSource(docIn))
					, new StreamResult(System.out)
					);
			System.out.println();
			docIn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

/**
 * This class reads the flat file employee.dat and reports SAX parser events to act as it was 
 * parsing an XML file. <br />
 */
class EmployeeReader implements XMLReader {
	private ContentHandler handler;
	
	public void parse(InputSource source) throws IOException, SAXException {
		InputStream stream = source.getByteStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String rootElement = "staff";
		AttributesImpl atts = new AttributesImpl();
		
		if (handler == null) throw new SAXException("No content handler");
		
		handler.startDocument();
		handler.startElement("", rootElement, rootElement, atts);
		try {
			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				handler.startElement("", "employee", "employee", atts);
				StringTokenizer t = new StringTokenizer(line, "|");
				
				handler.startElement("", "name", "name", atts);
				String s = t.nextToken();
				handler.characters(s.toCharArray(), 0, s.length());
				handler.endElement("", "name", "name");
				
				handler.startElement("", "salary", "salary", atts);
				s = t.nextToken();
				handler.characters(s.toCharArray(), 0, s.length());
				handler.endElement("", "salary", "salary");
				
				/**
				 * 2.2.3 Saving Objects in Text Format only had 3 tokens as did Listing 2.1
				atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
				atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
				atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
				 */
				s = t.nextToken();
				atts.addAttribute("", "year", "year", "CDATA", s.substring(0, 4));
				atts.addAttribute("", "month", "month", "CDATA", s.substring(5, 7));
				atts.addAttribute("", "day", "day", "CDATA", s.substring(8, 10));
				handler.startElement("", "hiredate", "hiredate", atts);
				handler.endElement("", "hiredate", "hiredate");
				atts.clear();
				
				handler.endElement("", "employee", "employee");
			}
		} catch (NoSuchElementException ex) {
			System.out.println("Run chapter02.TextFileTest to save the employee.dat file in the correct "
					+ "format!");
		}

		handler.endElement("", rootElement, rootElement);
		handler.endDocument();
	}
	
	public void setContentHandler(ContentHandler newValue) {
		handler = newValue;
	}
	
	public ContentHandler getContentHandler() {
		return handler;
	}
	
	// the following methods are just do-nothing implementations
	public void parse(String systemId) throws IOException, SAXException {}
	public void setErrorHandler(ErrorHandler handler) {}
	public ErrorHandler getErrorHandler() { return null; }
	public void setDTDHandler(DTDHandler handler) {}
	public DTDHandler getDTDHandler() {return null;}
	public void setEntityResolver(EntityResolver resolver) {}
	public EntityResolver getEntityResolver() {return null;}
	public void setProperty(String name, Object value) {}
	public Object getProperty(String name) {return null;}
	public void setFeature(String name, boolean value) {}
	public boolean getFeature(String name) {return false;}
}


