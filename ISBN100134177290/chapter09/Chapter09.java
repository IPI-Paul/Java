package chapter09;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter09 {
	public static final String CLASS_NAME = Chapter09.class.getName();
	public static String message = "";
	public static JFrame frame;
	private static Loaders file;
	private static final Charset cs = StandardCharsets.UTF_8; 
	
	private static final String[] example = {
			"Listing 9.1 Class Loader Test",
			"Listing 9.2 Encrypt Class File Using Caesar Cipher",
			"Listing 9.3 Verifier Test",
			"Listing 9.4-5 Permission Test",
			"Listing 9.6-9 Authorisation Test",
			"Listing 9.10-15 Java Authentication and Authorisation Service",
			"Listing 9.16 Digest",
			"Listing 9.17 File Read Applet",
			"Listing 9.18-19 AES Test",
			"Listing 9.20 RSA Test"
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
				 * {@link ClassLoaderTest} class Listing 9.1 <br />
				 * {@link ClassLoaderFrame} inner class extends JFrame <br />
				 * {@link CryptoClassLoader} inner class extends ClassLoader <br />
				 * This program demonstrates a custom class loader that decrypts class files. <br />
				 */
				ClassLoaderTest.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@code Caesar class Listing 9.2 <br />
				 * Encrypts a file using the Caesar cipher. <br />
				 */
				List<String> params = new ArrayList<>();
				Charset cs = StandardCharsets.UTF_8;
				Loaders in = new Loaders();		
				in.setChoice("S", "cls", "chapter09/", "", cs, "", "", "");
				Object key = JOptionPane.showInputDialog(null, "Please give the encyption key", 3);
				String regex;
				if (in.getFilePath().contains("\\")) regex = "\\\\";
				else regex = "/";
				String[] fullPath = in.getFilePath().split(regex);
				String path = fullPath[fullPath.length - 2].toString() + "/" 
						+ fullPath[fullPath.length - 1].toString();
				params.add("java");
				params.add("chapter09.Caesar");
				params.add(path);
				params.add(path.replace(".class", ".caesar"));
				params.add(key.toString());
				ProcessBuilder pb = new ProcessBuilder(params);
				pb.inheritIO();
				try {
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Threads.waitFor(3);
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link VerifierTest} class Listing 9.3 <br />
				 * This application demonstrates the bytecode verifier of the virtual machine. If you use a  
				 * hex editor to modify the class file, then the virtual machine should detect the tampering. <br />
				 */
				VerifierTest.main(null);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link PermissionTest} class Listing 9.6 <br />
				 * {@link PermissionTestFrame} inner class extends JFrame <br /> 
				 * {@link WordCheckTextArea} inner class extends JTextArea <br />
				 * {@link WordCheckPermission} class extends Permission Listing 9.5 <br />
				 * This program demonstrates the custom WordCheckPersmission. <br />
				 */
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("chapter09.PermissionTest");
				ProcessBuilder pb = new ProcessBuilder(params);
				try {
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Threads.waitFor(5);
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link AuthTest} class Listing 9.6 <br />
				 * {@link SysPropAction} class implements PrivilegedAction&gt;String> <br /> 
				 * <a href="../sourceFiles/permissions/AuthTest-ch09.policy">AuthTest-ch09.policy</a> <br />
				 * <a href="../sourceFiles/settings/jaas-ch09.config">jaas-ch09.config</a> <br />
				 * This program authenticates a user via a custom login and then executes the 
				 * SysPropAction with the user's privileges. <br />
				 */
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("-classpath");
				params.add("webapps/login-ch09.jar;webapps/action-ch09.jar");
				params.add("-Djava.security.policy=sourceFiles/permissions/AuthTest-ch09.policy");
				params.add("-Djava.security.auth.login.config=sourceFiles/settings/jaas-ch09.config");
				params.add("chapter09.AuthTest");
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
			else if (exampleType == example[5]) {
				/**
				 * {@link JAASTest} class Listing 9.13 <br />
				 * {@link SimplePrincipal} class implements Principal Listing 9.10 <br />
				 * {@link SimpleLoginModule} class implements LoginModule Listing 9.11 <br /> 
				 * {@link SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
				 * {@link JAASFrame} class extends JFrame <br />
				 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
				 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
				 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
				 * This program authenticates a user via a custom login and then looks up a system 
				 * property with the user's privileges. <br />
				 */
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("-classpath");
				params.add("webapps/JAASTest-login-ch09.jar;webapps/JAASTest-action-ch09.jar");
				params.add("-Djava.security.policy=sourceFiles/permissions/JAASTest-ch09.policy");
				params.add("-Djava.security.auth.login.config=sourceFiles/settings/JAASTest-jaas-ch09.config");
				params.add("chapter09.JAASTest");
				ProcessBuilder pb = new ProcessBuilder(params);
				pb.inheritIO();
				try {
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Threads.waitFor(5);
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link Digest} class Listing 9.16 <br />
				 * This program computes the message digest of a file. <br />
				 */
				Charset cs = StandardCharsets.UTF_8;
				file = new Loaders();
				file.setChoice("S", "txt", "", "", cs, "", "", "For Source Text File");
				if (file.getChoice() == false) {
					Views.openWindowOpener(CLASS_NAME, message);
					return;
				}
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("chapter09.Digest");
				params.add(file.getFilePath());
				params.add("SHA-256");
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
			else if (exampleType == example[7]) {
				/**
				 * {@link FileReadApplet} class Listing 9.17 <br />
				 * <a href="FileReadApplet.html">FileReadApplet.html</a> <br />
				 * <a href="../sourceFiles/permissions/applet-ch09.policy">applet-ch09.policy</a> <br />
				 * This applet can run "outside the sandbox" and read local files when it is given the
				 * right permissions. <br />
				 */
				List<String> params = new ArrayList<>();
				params.add("appletviewer");
				params.add("-J-Djava.security.policy=sourceFiles/permissions/applet-ch09.policy");
				params.add("chapter09/FileReadApplet.html");
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
			else if (exampleType == example[8]) {
				/**
				 * {@link AESTest} class Listing 9.18 <br />
				 * {@link Util} class Listing 9.19 <br />
				 * This program tests the AES cipher. <br /> 
				 */
				String[] type = {"Generate Key File", "Encrypt a File", "Decrypt a File"};
				List<String> params = new ArrayList<String>();;
				file = new Loaders();
				Loaders outFile = new Loaders();
				Loaders keyFile = new Loaders();
				Object runType = JOptionPane.showInputDialog(null, "Please Select a Run Type!",
						 "AES Test Run Type", JOptionPane.QUESTION_MESSAGE, null, 
						type, 
						 type[0]);
				if (runType == type[0]) {
					file.saveFile("AESTest-ch09.key");
				} else {
					file.setChoice("S", "txt", "", "", cs, "", "", "Selecting File to Encrypt/Decrypt");
					if (runType == type[1]) 
						outFile.saveFile(FolderPaths.getFilePath(file.getFilePath(), "Encrypted" , null, 
								"Decrypted "));
					if (runType == type[2]) 
						outFile.saveFile(FolderPaths.getFilePath(file.getFilePath(), "Decrypted" , null, 
								"Encrypted "));
					keyFile.setChoice("S", "key", "", "AESTest-ch09.key", cs, "", "", "Selecting Encryption Key File to use");
				}
				if (runType == type[0]) {
					params.add("java");
					params.add("chapter09.AESTest");
					params.add("-genkey");
					params.add(file.getFilePath());
				} else if (runType == type[1]) {
					params.add("java");
					params.add("chapter09.AESTest");
					params.add("-encrypt");
					params.add(file.getFilePath());
					params.add(outFile.getFilePath());
					params.add(keyFile.getFilePath());
				} else if (runType == type[2]) {
					params.add("java");
					params.add("chapter09.AESTest");
					params.add("-decrypt");
					params.add(file.getFilePath());
					params.add(outFile.getFilePath());
					params.add(keyFile.getFilePath());
				}
				if (runType != null) {
					ProcessBuilder pb = new ProcessBuilder(params);
					try {
						Process p = pb.start();
						InputStream in = p.getInputStream();
						int ch;
						while ((ch = in.read()) != -1)
							System.out.print((char) ch);
						p.waitFor();
						p.destroyForcibly();
						params.clear();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else if (exampleType == example[9]) {
				/**
				 * {@link RSATest} class Listing 9.20 <br />
				 * {@link Util} class Listing 9.19 <br />
				 * This program test the RSA cipher <br />
				 */
				String[] type = {"Generate Public and Private Key Files", "Encrypt a File", "Decrypt a File"};
				List<String> params = new ArrayList<String>();;
				file = new Loaders();
				Loaders outFile = new Loaders();
				Loaders keyFile = new Loaders();
				Object runType = JOptionPane.showInputDialog(null, "Please Select a Run Type!",
						 "RSA Test Run Type", JOptionPane.QUESTION_MESSAGE, null, 
						type, 
						 type[0]);
				if (runType == type[0]) {
					file.saveFile("RSATest-public-ch09.key");
					keyFile.saveFile("RSATest-private-ch09.key");
				} else if (runType == type[1]) {
					file.setChoice("S", "txt", "", "", cs, "", "", "Selecting File to Encrypt/Decrypt");
					outFile.saveFile(FolderPaths.getFilePath(file.getFilePath(), "Encrypted" , null,
							"Decrypted "));
					keyFile.setChoice("S", "key", "", "RSATest-public-ch09.key", cs, "", "", "Selecting Encryption Key File to use");
				} else if (runType == type[2]) {
					file.setChoice("S", "txt", "", "", cs, "", "", "Selecting File to Encrypt/Decrypt");
					outFile.saveFile(FolderPaths.getFilePath(file.getFilePath(), "Decrypted" , null, 
							"Encrypted "));
					keyFile.setChoice("S", "key", "", "RSATest-private-ch09.key", cs, "", "", "Selecting Encryption Key File to use");
				}
				if (runType == type[0]) {
					params.add("java");
					params.add("chapter09.RSATest");
					params.add("-genkey");
					params.add(file.getFilePath());
					params.add(keyFile.getFilePath());
				} else if (runType == type[1]) {
					params.add("java");
					params.add("chapter09.RSATest");
					params.add("-encrypt");
					params.add(file.getFilePath());
					params.add(outFile.getFilePath());
					params.add(keyFile.getFilePath());
				} else if (runType == type[2]) {
					params.add("java");
					params.add("chapter09.RSATest");
					params.add("-decrypt");
					params.add(file.getFilePath());
					params.add(outFile.getFilePath());
					params.add(keyFile.getFilePath());
				}
				if (runType != null) {
					ProcessBuilder pb = new ProcessBuilder(params);
					try {
						Process p = pb.start();
						InputStream in = p.getInputStream();
						int ch;
						while ((ch = in.read()) != -1)
							System.out.print((char) ch);
						p.waitFor();
						p.destroyForcibly();
						params.clear();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}
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

