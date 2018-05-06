package ipi;

import java.awt.FileDialog;
import java.io.*;
import javax.jnlp.*;
import javax.swing.*;

/**
 * Streams class provides InputStream readers for Web Start access <br />
 * @version 1.0 2018-04-30
 * @author Paul I Ighofose
 */
public class Streams {
	private File file;
	private FileContents contents;
	
	public Streams() {
	}
	
	/**
	 * Provides a file open dialog and the reads the text file selected
	 * @return
	 */
	public void getTextFileService( ) {
		File file = null;
		FileContents contents = null;
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			contents = service.openFileDialog(".", new String[] {"txt", "htm", "html", "java", 
					"vbs", "js", "cpp"});
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
		setFile(file);
		setContents(contents);		
	}
		
	public void getTextFileDialog(JFrame frame) {
		FileDialog fd = new FileDialog(frame, "Choose a File", FileDialog.LOAD);
		fd.setVisible(true);
		setFile(file);
	}

	public FileContents getContents() {
		return contents;
	}

	public void setContents(FileContents contents) {
		this.contents = contents;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}	
}
