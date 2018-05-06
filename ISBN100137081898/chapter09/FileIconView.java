package chapter09;  // FileIconView

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

/**
 * FileIconView FileView Listing 9.23
 * ImageViewerFrame JFrame Listing 9.21
 * ImagePreviewer JLabel Listing 9.22
 * A file view that displays an icon for all files that match a file filter.
 * @author Cay Horstmann
 */
public class FileIconView extends FileView {
	private FileFilter filter;
	private Icon icon;
	
	/**
	 * Constructs a FileIconView.
	 * @param aFilter a file filter--all files that this filter accepts will be shown with the icon.
	 * @param anIcon--the icon shown with all accepted files.
	 */
	public FileIconView(FileFilter aFilter, Icon anIcon) {
		filter = aFilter;
		icon = anIcon;
	}
	
	public Icon getIcon(File f) {
		if (!f.isDirectory() && filter.accept(f)) return icon;
		else return null;
	}
}
