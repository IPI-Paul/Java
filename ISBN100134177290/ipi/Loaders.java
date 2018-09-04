package ipi;

import java.awt.FileDialog;
/**
import java.awt.List;
 */
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import javax.jnlp.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.swing.filechooser.FileFilter;

/**
 * Loaders class provides InputStream readers for Web Start access to files <br />
 * @version 1.0 2018-04-30
 * @author Paul I Ighofose
 */
public class Loaders {
	private File file;
	private String fileName;
	private FileContents contents;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Path path;
	private Charset cs = StandardCharsets.UTF_8;
	private boolean runClass;
	private String[] LOAD_TYPE;
	private String input;
	private int useConsoleInput;
	private int useDefaultFile;
	private int useDialogInput;
	private int useFileDialog;
	private boolean isJNLP;
	private boolean isTrusted;
	private String url;
	private String[] fileType = new String[] {"txt", "htm", "html", "java", "xml", "xhtml", "css", "dat", 
			"sql", "vbs", "js", "cpp", "bat", "csv", "xslt", "xsl", "xsd", "zip", "jar", "config", "inf", 
			"ini", "svg", "properties", "log", "class", "caesar", "key"
			};
	private FileFilter fileFilter = (new FileFilter() {
		public boolean accept(File f) {
			String ext = f.getName().toLowerCase();
			return acceptType(ext, f);
		}		
		public String getDescription() {
			return "Text Based Files";
		}
	});
	private FileNameExtensionFilter cls = new FileNameExtensionFilter(
			"Class Files", "class", "caesar"
			);
	private FileNameExtensionFilter cnf = new FileNameExtensionFilter(
			"Config Files", "config", "inf", "ini", "properties"
			);
	private FileNameExtensionFilter dat = new FileNameExtensionFilter(
			"Data Files", "accdb", "dat", "log", "mdb", "mdf", "mdw", "sdf"
			);
	private FileNameExtensionFilter key = new FileNameExtensionFilter(
			"Encryption Key Files", "key"
			);
	private FileNameExtensionFilter img = new FileNameExtensionFilter(
			"Image Files", "bmp", "gif", "ico", "jpg", "jpeg", "png", "svg"
			);
	private FileNameExtensionFilter scr = new FileNameExtensionFilter(
			"Script Files", "bat", "cpp", "java", "js", "sql", "vbs"
			);
	private FileNameExtensionFilter stl = new FileNameExtensionFilter(
			"Stylesheet Files", "css", "xsl", "xslt"
			);
	private FileNameExtensionFilter txt = new FileNameExtensionFilter(
			"Text Files", "csv", "txt"
			);
	private FileNameExtensionFilter htm = new FileNameExtensionFilter(
			"Web Files", "htm", "html", "xhtml"
			);
	private FileNameExtensionFilter xml = new FileNameExtensionFilter(
			"XML Files", "xml", "xsd"
			);
	private FileNameExtensionFilter zip = new FileNameExtensionFilter(
			"ZIP Files", "jar", "tgz", "zip"
			);
	private FileFilter chooserType = txt;
	
	public Loaders() {
	}

	private void getOptions(String selection) {
		String[][] loadString = new String[4][2]; 
		loadString[0][0] = "C";
		loadString[1][0] = "D";
		loadString[2][0] = "J";
		loadString[3][0] = "S";
		loadString[0][1] = "Use Console Input";
		loadString[1][1] = "Use Default File/Input";
		loadString[2][1] = "Use Dialog Input";
		loadString[3][1] = "Select and Open File";
		LOAD_TYPE = new String[selection.length()];
		for (int i = 0; i < selection.length(); i++) {
			for (int j = 0; j < 4; j++)
				if (loadString[j][0].equalsIgnoreCase(selection.substring(i, i + 1))) {
					LOAD_TYPE[i] = loadString[j][1];
				}
		}
		useConsoleInput = selection.indexOf("C");
		useDefaultFile = selection.indexOf("D");
		useDialogInput = selection.indexOf("J");
		useFileDialog = selection.indexOf("S");
	}
	
	public boolean acceptType(String ext, File f) {
		return ext.endsWith(".txt") || f.isDirectory() ||  ext.endsWith(".htm") ||  ext.endsWith(".xhtml") || 
				ext.endsWith(".html") || ext.endsWith(".java") || ext.endsWith(".vbs") ||
				ext.endsWith(".js") || ext.endsWith(".cpp") || ext.endsWith(".xml") || 
				ext.endsWith(".css") || ext.endsWith(".bat") || ext.endsWith(".csv") || 
				ext.endsWith(".xslt") || ext.endsWith(".xsl") || ext.endsWith(".xsd")  || 
				ext.endsWith(".dat") || ext.endsWith(".zip") || ext.endsWith(".jar") || 
				ext.endsWith(".config") || ext.endsWith(".inf") || ext.endsWith(".ini") || 
				ext.endsWith(".sql") || ext.endsWith(".svg") || ext.endsWith(".properties") || 
				ext.endsWith(".log") || ext.endsWith(".class") || ext.endsWith(".caesar") || 
				ext.endsWith(".key");
	}
	
	public String[] acceptChoice(String type, String folder) {
		String[] accept = new String[2];
		try {
			BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
			if(basic != null) {} 
			setJNLP(true);
		} catch (UnavailableServiceException e) {
			setJNLP(false);
		}
		try {
			if (type.equals("cls")) {
				accept[0] = FolderPaths.getPath();
				setChooserType(cls);
			}
			else if (type.equals("cnf")) {
				accept[0] = FolderPaths.getSettingsFolder();
				setChooserType(cnf);
			}
			else if (type.equals("csv")) {
				accept[0] = FolderPaths.getCSVFolder();
				setChooserType(txt);
			}
			else if (type.equals("dat")) {
				accept[0] = FolderPaths.getDATFolder();
				setChooserType(dat);
			}
			else if (type.equals("db")) {
				accept[0] = FolderPaths.getDatabaseFolder();
				setChooserType(dat);
			}
			else if (type.equals("htm")) {
				accept[0] = FolderPaths.getHTMLFolder();
				setChooserType(htm);
			}
			else if (type.equals("img")) {
				accept[0] = FolderPaths.getImagesFolder();
				setChooserType(img);
			}
			else if (type.equals("key")) {
				accept[0] = FolderPaths.getKeysFolder();
				setChooserType(key);
			}
			else if (type.equals("log")) {
				accept[0] = FolderPaths.getLogsFolder();
				setChooserType(dat);
			}
			else if (type.equals("prop")) {
				accept[0] = FolderPaths.getPropertiesFolder();
				setChooserType(cnf);
			}
			else if (type.equals("scr")) {
				accept[0] = FolderPaths.getScriptFolder();
				setChooserType(scr);
			}
			else if (type.equals("stl")) {
				accept[0] = FolderPaths.getStyleFolder();
				setChooserType(stl);
			}
			else if (type.equals("txt")) {
				accept[0] = FolderPaths.getTextFolder();
				setChooserType(txt);
			}
			else if (type.equals("xml")) {
				accept[0] = FolderPaths.getXMLFolder();
				setChooserType(xml);
			}
			else if (type.equals("app")) {
				accept[0] = FolderPaths.getWebAppsFolder();
				setChooserType(fileFilter);
			}
			else if (type.equals("zip")) {
				accept[0] = FolderPaths.getZIPFolder();
				setChooserType(zip);
			}
			if (!folder.equals("")) accept[0] = FolderPaths.getPath() + "/" + folder;
			setTrusted(true);
		} catch (AccessControlException ex) {
			setTrusted(false);
		}
		if (!folder.equals("")) accept[1] = folder + "/";
		else if (type.equals("cls")) accept[1] = FolderPaths.getPath();
		else if (type.equals("cnf")) accept[1] = ResourcePaths.getSettingsResource();
		else if (type.equals("csv")) accept[1] = ResourcePaths.getCSVResource();
		else if (type.equals("dat")) accept[1] = ResourcePaths.getDATResource();
		else if (type.equals("db")) accept[1] = ResourcePaths.getDatabaseResource();
		else if (type.equals("htm")) accept[1] = ResourcePaths.getHTMLResource();
		else if (type.equals("img")) accept[1] = ResourcePaths.getImagesResource();
		else if (type.equals("key")) accept[1] = ResourcePaths.getKeysResource();
		else if (type.equals("log")) accept[1] = ResourcePaths.getLogsResource();
		else if (type.equals("prop")) accept[1] = ResourcePaths.getPropertiesResource();
		else if (type.equals("scr")) accept[1] = ResourcePaths.getScriptResource();
		else if (type.equals("stl")) accept[1] = ResourcePaths.getStyleResource();
		else if (type.equals("txt")) accept[1] = ResourcePaths.getTextResource();
		else if (type.equals("xml")) accept[1] = ResourcePaths.getXMLResource();
		else if (type.equals("app")) accept[1] = ResourcePaths.getWebAppsResource();
		else if (type.equals("zip")) accept[1] = ResourcePaths.getZIPResource();
		return accept;
	}

	private JFileChooser getChooserExtensions(JFileChooser chooser) {
		chooser.addChoosableFileFilter(fileFilter);
		chooser.addChoosableFileFilter(cls);
		chooser.addChoosableFileFilter(cnf);
		chooser.addChoosableFileFilter(dat);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(scr);
		chooser.addChoosableFileFilter(stl);
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(htm);
		chooser.addChoosableFileFilter(xml);
		chooser.addChoosableFileFilter(zip);
		chooser.setFileFilter(chooserType);
		return chooser;
	}
	
	/**
	 * @return the chooserType
	 */
	public FileFilter getChooserType() {
		return chooserType;
	}

	/**
	 * @param chooserType the chooserType to set
	 */
	public void setChooserType(FileFilter chooserType) {
		this.chooserType = chooserType;
	}

	private String[] getFileType() {
		return fileType;
	}

	/**
	 * Provides a folder open dialog using the JFileChooser class <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getFolderChooser(String path) throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(path));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Open Folder");
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) 
			setPath(chooser.getSelectedFile().getPath());
	}
	
	/**
	 * Provides a folder open dialog using the FileDialog class and it's SAVE mehtod <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getDirectory(String path) {
		FileDialog fd = new FileDialog(new JFrame(), "Choose a Folder", FileDialog.SAVE);
		fd.setFile("Retrieves Folder and Will Not Save A File");
		fd.setDirectory(path);
		fd.setVisible(true);
		if (fd.getFile() != null)
			setPath(fd.getDirectory());
	}
	
	/**
	 * Provides a file open dialog using the JFileChooser class <br />
	 * Also sets the <code>file</code> attribute to enable File access <br />
	 * @return returns the File selected <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getTextFileChooser() throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		getChooserExtensions(chooser);
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) 
			setFile(chooser.getSelectedFile());
	}
	/**
	 * Provides a file open dialog using the JFileChooser class <br />
	 * Also sets the <code>file</code> attribute to enable File access <br />
	 * @param cs character set to decode file with <br />
	 * @return returns the File selected <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getTextFileChooser(Charset cs) throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		getChooserExtensions(chooser);
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) {
			setFile(chooser.getSelectedFile());
			setCharset(cs);
		}
	}

	/**
	 * Provides a file open dialog using the JFileChooser class <br />
	 * Also sets the <code>file</code> attribute to enable File access <br />
	 * @param file the default file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 * @return returns the File selected <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getTextFileChooser(File file, Charset cs) throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(file);
		getChooserExtensions(chooser);
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) {
			setFile(chooser.getSelectedFile());
			setCharset(cs);
		}
	}

	/**
	 * Provides a file open dialog using the JFileChooser class <br />
	 * Also sets the <code>file</code> attribute to enable File access <br />
	 * @param path the default path to a file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 * @return returns the File selected <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public void getTextFileChooser(String path, Charset cs) throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(path));
		getChooserExtensions(chooser);
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) {
			setFile(chooser.getSelectedFile());
			setCharset(cs);
		}
	}
	
	/**
	 * Provides a File Open Dialog using the FileDialog class when using web start<br />
	 * Also sets the <code>file</code> attribute to enable File access <br />	
	 * @param frame The frame caller <br />
	 */
	public void getTextFileDialog(JFrame frame) {
		FileDialog fd = new FileDialog(frame, "Choose a File to Open", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return acceptType(name, dir);
			}
		});
		fd.setFile("*.txt");
		fd.setVisible(true);
		if (fd.getFile() != null) 
			setFile(new File(fd.getDirectory() + fd.getFile()));
	}
	
	/**
	 * Provides a File Open Dialog using the FileDialog class when using web start<br />
	 * Also sets the <code>file</code> attribute to enable File access <br />	
	 * @param frame The frame caller <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileDialog(JFrame frame, Charset cs) {
		FileDialog fd = new FileDialog(frame, "Choose a File to Open", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return acceptType(name, dir);
			}
		});
		fd.setFile("*.txt");
		fd.setVisible(true);
		if (fd.getFile() != null) {
			setFile(new File(fd.getDirectory() + fd.getFile()));
			setCharset(cs);
		}
	}
	
	/**
	 * Provides a File Open Dialog using the FileDialog class when using web start<br />
	 * Also sets the <code>file</code> attribute to enable File access <br />	
	 * @param frame The frame caller <br />
	 * @param file the default file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileDialog(JFrame frame, File file, Charset cs) {
		FileDialog fd = new FileDialog(frame, "Choose a File to Open", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return acceptType(name, dir);
			}
		});
		fd.setFile(file.getPath());
		fd.setVisible(true);
		if (fd.getFile() != null) {
			setFile(new File(fd.getDirectory() + fd.getFile()));
			setCharset(cs);
		}
	}
	
	/**
	 * Provides a File Open Dialog using the FileDialog class when using web start<br />
	 * Also sets the <code>file</code> attribute to enable File access <br />	
	 * @param frame The frame caller <br />
	 * @param path the default path to a file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileDialog(JFrame frame, String file, Charset cs) {
		FileDialog fd = new FileDialog(frame, "Choose a File to Open", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return acceptType(name, dir);
			}
		});
		fd.setFile(file);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			setFile(new File(fd.getDirectory() + fd.getFile()));
			setCharset(cs);
		}
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected when using web start <br />
	 * Also sets the <code>file</code> and <code>contents</code> attributes to enable File and FileContents 
	 * access <br />
	 */
	public void getTextFileService() {
		File file = null;
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(".", getFileType());
			if (contents != null) {
				file = new File(contents.toString());
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if (file != null) {
			setFile(file);
			setContents(contents);		
		}
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected when using web start <br />
	 * Also sets the <code>file</code> and <code>contents</code> attributes to enable File and FileContents 
	 * access <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileService(Charset cs) {
		File file = null;
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(".", getFileType());
			if (contents != null) {
				file = new File(contents.toString());
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if (file != null) {
			setFile(file);
			setCharset(cs);
			setContents(contents);		
		}
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected when using web start <br />
	 * Also sets the <code>file</code> and <code>contents</code> attributes to enable File and FileContents 
	 * access <br />
	 * @param file the default file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileService(File file, Charset cs) {
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(file.getPath(), getFileType());
			if (contents != null) {
				file = new File(contents.toString());
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if (file != null) {
			setFile(file);
			setCharset(cs);
			setContents(contents);		
		}
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected when using web start <br />
	 * Also sets the <code>file</code> and <code>contents</code> attributes to enable File and FileContents 
	 * access <br />
	 * @param path the default path to a file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileService(String path) {
		File file = null;
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(path, getFileType());
			if (contents != null) {
				file = new File(contents.toString());
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if (file != null) {
			setFile(file);
			setContents(contents);		
		}
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected when using web start <br />
	 * Also sets the <code>file</code> and <code>contents</code> attributes to enable File and FileContents 
	 * access <br />
	 * @param path the default path to a file to locate and select <br />
	 * @param cs character set to decode file with <br />
	 */
	public void getTextFileService(String path, Charset cs) {
		File file = null;
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(path, getFileType());
			if (contents != null) 
				file = new File(contents.toString());
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if (file != null) {
			setFile(file);
			setCharset(cs);
			setContents(contents);		
		}
	}
	
	public EntityResolver getEntityResolver(DocumentBuilder builder) {
		EntityResolver resolver = new EntityResolver() {					
			@Override
			public InputSource resolveEntity(String publicId, String systemId) 
					throws SAXException, IOException {
				if (systemId.endsWith("gridbag.dtd")) {
					return new InputSource(getInputStream("chapter03/gridbag.dtd"));
				}
				else if (systemId.equals("http://java.sun.com/dtd/preferences.dtd")) {
					return new InputSource(getInputStream("sourceFiles/dtd/preferences.dtd"));
				}
				else if (publicId.contains("http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd")) {
					return new InputSource(getInputStream("sourceFiles/dtd/xhtml1-strict.dtd"));
				}
				return null;  
			}
		};
		return resolver;
	}
	
	/**
	 * Gets an InputStream from contents if they are not null. <br />
	 * @return the InputStream
	 */
	public InputStream getInputStream() {
		try {
			return Files.newInputStream(getPath());
		} catch (IOException | AccessControlException | NullPointerException  ex) {
			try {
				if (contents == null) 
					setContents();
				return contents.getInputStream();
			} catch (IOException | NullPointerException e) {
				return getInputStream(getPath());
			}
		}
	}

	/**
	 * Gets an InputStream from the supplied path <br />
	 * @param path The path to the file to be worked with <br />
	 */
	public InputStream getInputStream(Path path) {
		try {
			inputStream = Files.newInputStream(path);
		} catch (NoSuchFileException | AccessControlException ex) {
			ClassLoader cl =  Thread.currentThread().getContextClassLoader();
			URL url = cl.getResource(ResourcePaths.getResourcePath(path));
			try {
				inputStream = url.openStream();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				try {
					if (this.path == null || !this.path.toString().equals(path.toString())) {
						setFile(new File(path.toString()));
						setContents();
					}
					inputStream = contents.getInputStream();
				} catch (IOException exp) {
				}
			} 
		} catch (IOException ex) {
		}
		return inputStream;
	}	
	
	/**
	 * Gets an InputStream from using the ClassLoader to load a file as a resource. <br />
	 * Can be used in loading DTDs when parsing XML files. <br/> 
	 * @return the InputStream
	 */
	public InputStream getInputStream(String resource) {
		ClassLoader cl =  Thread.currentThread().getContextClassLoader();
		InputStream is = cl.getResourceAsStream(resource);
		return is;
	}
	
	/**
	 * Gets an OutputStream from contents if they are not null. <br />
	 * @return the OutputStream
	 */
	public OutputStream getOutputStream() {
		return outputStream;
	}
	
	/**
	 * Saves to a file using the OutputStream class and a dialog SAVE mehtod <br />
	 * @param type the extension type, displays the default folder for that type <br />
	 * @param path the file name and path to intially use <br /> 
	 */
	public OutputStream getOutputStream(String type, String path) {
		String[] accept = acceptChoice(type, "");
		try {
			JFileChooser chooser = new JFileChooser(path);
			chooser.setCurrentDirectory(new File(accept[0]));
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				setFile(chooser.getSelectedFile());
				outputStream = Files.newOutputStream(getPath());
			}
		} catch (IOException ex) {
		} catch (AccessControlException ex) {
			FileDialog fd = new FileDialog(new JFrame(), "Choose a Folder", FileDialog.SAVE);
			fd.setDirectory(accept[1]);
			fd.setVisible(true);
			if (fd.getFile() != null) {
				try {
					ExtendedService es = (ExtendedService) ServiceManager.lookup("javax.jnlp.ExtendedService");
					outputStream = es.openFile(new File(fd.getDirectory() + fd.getFile())).getOutputStream(true);
				} catch (UnavailableServiceException e) {			
				} catch (IOException e) {
				}
			}
		}
		return outputStream;
	}
	
	/**
	 * Gets the contents attribute after running either the <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code><br />
	 * @return returns the contents of the current file once set <br />
	 */
	public FileContents getContents() {
		return contents;
	}

	/**
	 * Sets the contents attribute to that of the current file after running either the <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 * @param contents The contents of the current file <br />
	 */
	public void setContents() {
		ClassLoader cl =  Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource(getFilePath());
		try {
			contents = (FileContents) url.getContent();
		} catch (Exception e) {
			try {
				ExtendedService es = (ExtendedService) ServiceManager.lookup("javax.jnlp.ExtendedService");
				contents = es.openFile(file);
			} catch (UnavailableServiceException ex) {			
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * Sets the contents attribute to that of the current file after running either the <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 * @param contents The contents of the current file <br />
	 */
	public void setContents(FileContents contents) {
		this.contents = contents;
	}

	/**
	 * Gets the file attribute <br />
	 * @return returns the current file once set after running either the <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 */	
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file attribute to that of the current file after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 * @param file The current file
	 */
	public void setFile(File file) {
		this.file = file;
		if (file != null) setPath();
	}	

	/**
	 * Sets the file attribute <br />
	 * @param file The file to be worked with <br />
	 * @param cs character set to decode file with <br />
	 */
	public void setFile(File file, Charset cs) {
		this.file = file;
		setContents();
		setPath();
		setCharset(cs);
	}	

	/**
	 * Sets the file attribute <br />
	 * @param path The path to the file to be worked with <br />
	 * @param cs character set to decode file with <br />
	 */
	public void setFile(String path, Charset cs) {
		this.file = new File(path);
		setContents();
		setPath();
		setCharset(cs);
	}	

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file attributes path <br />
	 * @return returns the current file path once set after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 */	
	public String getFilePath() {
		try {
			return this.file.getPath();
		} catch (NullPointerException ex) {
			return path.toString();
		}
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the isJNLP
	 */
	public boolean isJNLP() {
		return isJNLP;
	}

	/**
	 * @param isJNLP the isJNLP to set
	 */
	public void setJNLP(boolean isJNLP) {
		this.isJNLP = isJNLP;
	}

	/**
	 * Gets the path attribute <br />
	 * @return returns the current file path once set after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 */	
	public Path getPath() {
		return this.path;
	}


	/**
	 * Sets the path attribute after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 */	
	public void setPath() {
		this.path = Paths.get(file.getPath());
	}

	/**
	 * Sets the path attribute after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 */	
	public void setPath(String path) {
		this.path = Paths.get(path);
		setContents();
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the isTrusted
	 */
	public boolean isTrusted() {
		return isTrusted;
	}

	/**
	 * @param isTrusted the isTrusted to set
	 */
	public void setTrusted(boolean isTrusted) {
		this.isTrusted = isTrusted;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Reads the contents of the current file  after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> and sends all 
	 * lines to a stream <br /> 
	 * @param cs the Character Set to use in decoding <br />
	 * @return returns all lines in the file <br />
	 */
	public Stream<String> toLines(Charset cs) {
		Stream<String> lines = null;
		if (contents != null) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(contents.getInputStream(), cs));
				lines = reader.lines();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				lines = Files.lines(path, cs);
			} catch (Exception e) {
				ClassLoader cl =  Thread.currentThread().getContextClassLoader();
				URL url = cl.getResource(ResourcePaths.getResourcePath(path));
				BufferedReader reader;
				try {
					reader = new BufferedReader(
							new InputStreamReader(url.openStream(), cs));
					lines = reader.lines();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return lines;
	}

	/**
	 * Reads the contents of the current file  after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> and sends all 
	 * lines to a stream <br /> 
	 * @param cs the Character Set to use in decoding <br />
	 * @return returns all lines in the file <br />
	 */
	public List<String> toList(Charset cs) {
		List<String> list = new ArrayList<>();
		String line;
		if (contents != null) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(contents.getInputStream(), cs));
				while ((line = reader.readLine()) != null) 
					list.add(line);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				list = Files.readAllLines(path, cs);
			} catch (Exception e) {
				ClassLoader cl =  Thread.currentThread().getContextClassLoader();
				URL url = cl.getResource(ResourcePaths.getResourcePath(path));
				BufferedReader reader;
				try {
					reader = new BufferedReader(
							new InputStreamReader(url.openStream(), cs));
					while ((line = reader.readLine()) != null) 
						list.add(line);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * Reads the contents of the file after running <code>getTextFileChooser</code>, 
	 * <code>getTextFileDialog</code> or <code>getTextFileService</code> <br />
	 * @return returns all text in the selected file for this stream <br />
	 */
	public String toString() {
		StringBuilder text = new StringBuilder();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(getPath())));
		try {
			while ((line = reader.readLine()) != null) 
				text.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text.toString();
/**
		if (contents != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(contents.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) 
					text = text + line + System.lineSeparator();
			} catch (IOException ex) {
//				ClassLoader cl =  Thread.currentThread().getContextClassLoader();
//				InputStream is = cl.getResourceAsStream(getFilePath());
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is, cs));
//				String line;
//				try {
//					while ((line = reader.readLine()) != null) 
//						text = text + line + System.lineSeparator();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				ex.printStackTrace();
			}
		} else {
			try {
				text = new String(Files.readAllBytes(path), cs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
*/	}

	public Charset getCharset() {
		return cs;
	}

	public void setCharset(Charset cs) {
		this.cs = cs;
	}

	public boolean getChoice() {
		return runClass;
	}
	
	private void setRunClass(boolean b) {
		runClass = b;
	}
		
	/**
	 * Provides a choice of file loading operations and checks for application runtime type. <br />
	 * @param selection the message dialog list specifier, uses the following selection: 
	 * <ul><li>C - Use Console Input</li> <li>D - Use Default File</li> <li>J - Use Dialog Input</li>
	 * <li>S - Select and Open a File</li></ul>
	 * @param type the type of file being loaded. Also looks in a default location for that file. <br />
	 * @param folder only specify when not wishing to look in the default folders <br />
	 * @param fileName name of file <br />
	 * @param cs character set to decode with <br />
	 */
	public int setChoice(String selection, String type, String folder, String fileName, Charset cs, 
			String dialogText, String value, String title) {
		setFile(null);
		setFileName(fileName);
		getOptions(selection);
		String[] accept = acceptChoice(type, folder);
		int choice = 0;
		
		if (accept[1].equals("")) {
			setRunClass(false);
			return 0;
		}

		Object runType;
		if (selection.length() == 1 && !selection.equals("S"))
			runType = LOAD_TYPE[0];
		else if (selection.length() == 1 && selection.equals("S"))
			runType = LOAD_TYPE[useFileDialog];
		else
			runType = JOptionPane.showInputDialog(null, "Please Select an Option!", "Load Type " + title,
					JOptionPane.QUESTION_MESSAGE, null, LOAD_TYPE, LOAD_TYPE[0]);

		if (useConsoleInput != -1 && runType == LOAD_TYPE[useConsoleInput]) {
			choice = useConsoleInput;
		} else if (useDialogInput != -1 && runType == LOAD_TYPE[useDialogInput]) {
			String in = JOptionPane.showInputDialog(null, dialogText, value);
			if (in.startsWith("http://") || in.startsWith("ftp://") || in.startsWith("mailto:")) 
				setUrl(in);
			else setInput(in);
			choice = useDialogInput;
		} else if (useDefaultFile != -1 && runType == LOAD_TYPE[useDefaultFile]) {
			if (isJNLP() == false && fileName.length() > 0)
				setFile(accept[0] + fileName, cs);
			else if (fileName.length() > 0)
				setPath(accept[1] + fileName);
			else if (value.startsWith("http://") || value.startsWith("ftp://") || value.startsWith("mailto:"))
				setUrl(value);
			else if (value.length() > 0)
				setPath(accept[1] + value);
			choice = useDefaultFile;
		} else if (useFileDialog != -1 && runType == LOAD_TYPE[useFileDialog]) {
			try {
				getTextFileChooser(accept[0] + fileName, cs);
			} catch (AccessControlException ex) {
				getTextFileService(accept[1] + fileName, cs);
			}
			choice = useFileDialog;
		} 
		if (runType == null || (getFile() == null && getPath() == null && url + input == null &&
				useConsoleInput != -1)) {
			setRunClass(false);
		} else {
			setRunClass(true);	
		}
		return choice;
	}	
	
	/**
	 * Provides a choice of folder loading operations and checks for application runtime type. <br />
	 */
	public void setFolder() {
		try {
			getFolderChooser(FolderPaths.getPath());
		} catch (AccessControlException ex) {
			getDirectory(".");
		}
		if (getPath() == null) {
			setRunClass(false);
		} else {
			setRunClass(true);	
		}
	}	
	
	/**
	 * Saves a file using the FileDialog class and it's SAVE mehtod <br />
	 */
	public void saveFile(String path) {
		FileDialog fd = new FileDialog(new JFrame(), "Choose a Folder", FileDialog.SAVE);
		fd.setDirectory(FolderPaths.getFolderPath(path));
		fd.setFile(path);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			setPath(fd.getDirectory() + fd.getFile());
			try {
				ExtendedService es = (ExtendedService) ServiceManager.lookup("javax.jnlp.ExtendedService");
				contents = es.openFile(new File(fd.getDirectory() + fd.getFile()));
			} catch (UnavailableServiceException ex) {			
			} catch (IOException ex) {
			}
		}
	}
	
	/**
	 * Writes text to a file and then saves it using the jnlp FileSave Service. <br />
	 * @param path location of the file. <br />
	 * @param in the input stream. <br />
	 */
	public static void saveFile(String path, InputStream in) {
		FileSaveService fss;
		try {
			fss = (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
		} catch (UnavailableServiceException ex) {
			fss = null;
		}
		if (fss != null) {
			try {
				fss.saveFileDialog(null, null, in, path);
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * Creates a output print stream to save data to a file. Writes to the file and then saves it. <br />
	 * @param source the text to write to the file. <br />
	 */
	public void printOut(String source) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printOut = new PrintStream(out);
			printOut.print(source);
			InputStream data = new ByteArrayInputStream(out.toByteArray());
			FileSaveService service = (FileSaveService) 
					ServiceManager.lookup("javax.jnlp.FileSaveService");
			service.saveFileDialog(path.toString(), new String[] {"dat"}, data, path.toString());
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
