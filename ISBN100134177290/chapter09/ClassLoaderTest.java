package chapter09;  // classLoader

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.awt.*;
/**
import java.awt.event.*;
 */
import javax.swing.*;
import ipi.*;

/**
 * {@code ClassLoaderTest} class Listing 9.1 <br />
 * {@link ClassLoaderFrame} inner class extends JFrame <br />
 * {@link CryptoClassLoader} inner class extends ClassLoader <br />
 * This program demonstrates a custom class loader that decrypts class files. <br />
 * @version 1.24 2016-05-10
 * @author Cay Horstmann
 */
public class ClassLoaderTest {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ClassLoaderFrame();
			frame.setTitle("Class loader Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

/**
 * this frame contains two text fields for the name of the class to load and the decryption key. <br />
 */
class ClassLoaderFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField keyField = new JTextField("3", 4);
	private JTextField nameField = new JTextField("Calculator", 30);
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ClassLoaderFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new GridBagLayout());
		add(new JLabel("Class"), new GBC(0, 0).setAnchor(GBC.EAST));
		add(nameField, new GBC(1, 0).setWeight(100, 0).setAnchor(GBC.WEST));
		add(new JLabel("Key"), new GBC(0, 1).setWeight(100, 0).setAnchor(GBC.EAST));
		add(keyField, new GBC(1, 1).setWeight(100, 0).setAnchor(GBC.WEST));
		JButton loadButton = new JButton("Load");
		add(loadButton, new GBC(0, 2, 2, 1));
		loadButton.addActionListener(event -> runClass(nameField.getText(), keyField.getText()));
		pack();
	}
	
	/**
	 * Runs the main method of a given class. <br />
	 * @param name the class name <br />
	 * @param key the decryption key for the class files <br />
	 */
	public void runClass(String name, String key) {
		try {
			ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
			Class<?> c = loader.loadClass(name);
			Method m = c.getMethod("main", String[].class);
			m.invoke(null, (Object) new String[] {});
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
}

/**
 * This class loader loads encrypted class files. <br />
 */
class CryptoClassLoader extends ClassLoader {
	private int key;
	
	/**
	 * Constructs a crypto class loader. <br />
	 * @param k the decryption key <br />
	 */
	public CryptoClassLoader(int k) {
		key = k;
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] classBytes = null;
			classBytes = loadClassBytes(name);
			Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
			if (cl == null) throw new ClassNotFoundException(name);
			return cl;
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
	}
	
	/**
	 * Loads and decrypts the class file bytes. <br />
	 * @param name the class name <br />
	 * @return an array with the class file bytes <br />
	 */
	private byte[] loadClassBytes(String name) throws IOException {
		String cname = name.replace('.', '/') + ".caesar";
		byte[] bytes = Files.readAllBytes(Paths.get(cname));
		for (int i = 0; i < bytes.length; i++) 
			bytes[i] = (byte) (bytes[i] - key);
		return bytes;
	}
}

