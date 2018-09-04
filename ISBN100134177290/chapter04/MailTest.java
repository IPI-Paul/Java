package chapter04;  // mail

import java.io.*;
import java.nio.charset.*;
import java.security.AccessControlException;
/**
import java.nio.file.*;
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ipi.*;

/**
 * {@code MailTest} class Listing 4.8 <br />
 * <a href="../sourceFiles/properties/mailtest-ch04.properties">mailtest-ch04.properties</a> contains smtp 
 * connection settings <br />
 * This program shows how to use <code>JavaMail</code> to send mail messages. <br />
 * @version 1.00 2012-06-04
 * @author Cay Horstmann
 */
public class MailTest {
	private static final String MAIN_CLASS	= "chapter04.Chapter04";
	private static String messageText = "";
	private static Loaders file = new Loaders();
	private static Loaders body = new Loaders();
	
	public static void main(String[] args) throws MessagingException, IOException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "prop", "", "mailtest-ch04.properties", cs, "", "", "For Message Header");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, messageText);
			return;
		}
		if (args == null || args.length == 0) {
			body.setChoice("DS", "txt", "", "mailtest-ch04.txt", cs, "", "", "For Message Body");
			if (body.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, messageText);
				return;
			}
		}
		Properties props = new Properties();
		/**
		 * Amended to enable Web Start and file loading options <br />
		try (InputStream in = Files.newInputStream(Paths.get("mail" , "mail.properties"))) {
			props.load(in);
		}
		List<String> lines = Files.readAllLines(
				Paths.get(args[0], Charset.forName("UTF-8"));
		 */
		props.load(file.getInputStream(file.getPath()));
		if (args != null && args.length > 0 ) body.setFile(args[0], cs);
		List<String> lines = body.toList(cs); 
		String from = lines.get(0);
		String to = lines.get(1);
		String subject = lines.get(2);
		
		StringBuilder builder = new StringBuilder();
		for (int i = 3; i < lines.size(); i++) {
			builder.append(lines.get(i));
			builder.append("\n");
		}
		String password = "";
		try {
			Console console = System.console();
			password = new String(console.readPassword("Password: "));
		} catch (NullPointerException ex) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Please enter a password for " + from +": ");
			JPasswordField pwd = new JPasswordField(10);
			panel.add(label);
			panel.add(pwd);
			String[] options = new String[] {"OK", "Cancel"};
			int option = JOptionPane.showOptionDialog(null, panel, "Mail Host Password", 
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (option == 0 && (new String(pwd.getPassword())).length() != 0) 
				password = new String(pwd.getPassword());
			else 
				Views.openWindowOpener(MAIN_CLASS, messageText);
		}
		Session mailSession;
		try {
			mailSession = Session.getDefaultInstance(props);
		}catch (AccessControlException ex) {
			mailSession = Session.getInstance(props);
		}
		mailSession.setDebug(true);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(builder.toString());
		Transport tr = mailSession.getTransport();
		try {
			tr.connect(from, password);
			tr.sendMessage(message, message.getAllRecipients());
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			tr.close();
		}
		System.out.println("Message sent to: " + to);
		Views.openWindowOpener(MAIN_CLASS, messageText);
	}
}
