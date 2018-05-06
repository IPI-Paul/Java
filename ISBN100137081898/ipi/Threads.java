package ipi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessControlException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose
 * Threads class works with System.in and Process methods to enable web start function in full trust mode
 */
public class Threads {
	private static boolean inClosed;
	private static boolean inOpen;
	private static final String ADD_TRUST = "when it is signed and running in full trust mode." + System.lineSeparator() + "Ensure that you have "
	 		+ "added the trust certificate to your Control Panel -> Java -> Trusted Certificates!" + 
	 		System.lineSeparator();
	private static final String IN_MESSAGE = "You have already closed the Input Stream, you will to restart "
			+ "the Application before you can reuse the console input stream!" + System.lineSeparator();
	private static final String EVENT_TRACE_JNLP_MESSAGE = "EventSetDescriptor does not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run a EventSetDescriptor method from Web Start "
			 		+ ADD_TRUST;
	private static final String FORK_JOIN_MESSAGE = "ForkJoinPool does not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run a ForkJoinPool method from Web Start "
			 		+ ADD_TRUST;
	private static final String IN_JNLP_MESSAGE = "Scanner(System.in) does not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run a Scanner(System.in) method from Web Start "
			 		+ ADD_TRUST;
	private static final String LOG_JNLP_MESSAGE = "Logger and the Log Handler do not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run the Logger and the Log Handler methods "
			 		+ "from Web Start " + ADD_TRUST;
	private static final String PROCESS_JNLP_MESSAGE = "Process does not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run a Process method from Web Start "
			 		+ ADD_TRUST;
	private static final String ROBOT_JNLP_MESSAGE = "Robot does not work properly in Web Start modes !" 
			 + System.lineSeparator() + "This Application will only run a Robot method from Web Start "
			 		+ ADD_TRUST;

	/**
	 * Asks the user if they want to close the application. If they click Yes it uses System.exit to close 
	 * application
	 */
	public static void closeApplication() {
		int exit = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Exit Program", JOptionPane.YES_NO_OPTION);
		if (exit == JOptionPane.YES_OPTION) {
			if (isInOpen() == true) closeStream();
			System.exit(0);
		}
	}

	/**
	 * Closes the System.in stream
	 */
	public static void closeStream() {
		new Scanner(System.in).close();
		setInClosed(true);
	}
	
	/**
	 * Warns the user of a possible memory leak and asks them if they want to close the System.in stream
	 * @param in System.in stream causing the memory leak
	 * @return returns the closed or left open stream
	 */
	public static Scanner closeStream(Scanner in) {
		if (JOptionPane.showConfirmDialog(null, "Dou you want to close the console input stream to avoid a " + 
				"memory leak?" + System.lineSeparator() + "If you do you will not be able to test other "
				+ "console inputs until you restart the application", "Close Input Stream", 
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					in.close();
					setInClosed(true);
		} else {
			setInOpen(true);
		}
		return in;
	}

	/** 
	 * Sets the application defaultLocale
	 */
	public static void setDefaultLocale() {
		try {
			Locale.setDefault(Locale.UK);
		} catch (AccessControlException ex) {
			System.out.println(ex.toString());
		}
	}
	/** 
	 * Checks to see if the System.in stream is still open and returns true or false
	 * @return returns true if the System.in stream is open and false if not
	 */
	public static boolean isInClosed() {
		return inClosed;
	}

	/**
	 * Sets the inClosed attribute to true or false
	 * @param inClosed true or false variable
	 */
	public static void setInClosed(boolean inClosed) {
		Threads.inClosed = inClosed;
	}
	
	/**
	 * Gets the default message that notifies the user that System.in is closed
	 * @return returns the default message about System.in being closed
	 */
	public static String getInMessage() {
		return IN_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that Process does not work properly with web start
	 * @param caller the class name being traced
	 * @return returns the default message about System.in and web start issues
	 */
	public static String getEventTracerJnlpMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + EVENT_TRACE_JNLP_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that ForkJoinPool does not work properly with web start
	 * @param caller the class name trying to access the the ForkJoinPool
	 * @return returns the default message about ForkJoinPool and web start issues
	 */
	public static String getForkJoinMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + FORK_JOIN_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that System.in does not work properly with web start
	 * @param caller the class name trying to access the console
	 * @return returns the default message about System.in and web start issues
	 */
	public static String getInJnlpMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + IN_JNLP_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that Logger and the Log Handler do not work properly 
	 * with web start
	 * @param caller the class trying to access the Logger and Handler
	 * @return returns the default message about System.in and web start issues
	 */
	public static String getLogJnlpMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + LOG_JNLP_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that Process does not work properly with web start
	 * @return returns the default message about System.in and web start issues
	 */
	public static String getProcessJnlpMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + PROCESS_JNLP_MESSAGE;
	}

	/**
	 * Gets the default message that notifies the user that Robot and the Log Handler do not work properly 
	 * with web start
	 * @param caller the class name trying to access the Robot
	 * @return returns the default message about System.in and web start issues
	 */
	public static String getRobotJnlpMessage(String caller) {
		return System.lineSeparator() + caller + System.lineSeparator() + ROBOT_JNLP_MESSAGE;
	}
	
	/**
	 * Gets the System.in state held in the inOpen attribute
	 * @return returns the System.in state
	 */
	public static boolean isInOpen() {
		return inOpen;
	}
	
	public static void runCppProcess(InputStream is, String fl, String cl, String message) {
		try {
			fl = FolderPaths.getUserTempFolder() + "/" + fl;
			//fl = FolderPaths.getPath() + fl;
			fl = fl.replace("\\\\", File.separator);
			fl = fl.replace("/", File.separator);
			OutputStream out = new FileOutputStream(fl);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				out.write(b, 0, length);
			}			
			out.close();
			ProcessBuilder pb = new ProcessBuilder(fl);
			pb.redirectError();
			Process p = pb.start();
			is = p.getInputStream();
			int value = -1;
			while ((value = is.read()) != -1)
				System.out.print((char) value);
			int exitCode = p.waitFor();
			System.out.println(fl + " exited with " + exitCode);
		} catch (Exception ex) {
			message = getProcessJnlpMessage(cl);
		}
		try {
			Runtime.getRuntime().exec("cmd.exe /c del /q " + fl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Views.openWindowOpener(cl, message);
	}

	/** 
	 * Try to run application from system console to enable use of System.in and return message
	 * @param tp the type of java startup jar or cp
	 * @param cl the class to run as main process
	 * @param jar the jar file name
	 * @param message remains unchanged from the one passed in if no exception caught
	 * @return returns the message passed in
	 */
	public static String runJarProcess(String tp, String cl, String jar, String message) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start java -" + tp + " " + 
					FolderPaths.getWebAppsFolder() + jar + ".jar " + cl);
			p.waitFor();
		} catch (Exception ex) {
			System.out.println(ex.toString());
			if (tp.equals("jar")) {
				Views.openWindowOpener(cl, getProcessJnlpMessage(message));
			} else {
				return getProcessJnlpMessage(cl);
			}
		}		
		return message;
	}
	
	/**
	 * Sets the System.in state in the inOpen attribute
	 * @param inOpen System.in state to set
	 */
	public static void setInOpen(boolean inOpen) {
		Threads.inOpen = inOpen;
	}
}
