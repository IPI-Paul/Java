package chapter02;  // regex

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

import javax.swing.JOptionPane;

import ipi.*;

/**
 * {@code RegexTest} class Listing 2.6 <br />
 * This program prompts for a pattern test expression matching. Enter a pattern and 
 * strings to match, or hit Cancel to exit. If the pattern contains groups, the grouping
 * boundaries are displayed. <br />
 * @version 1.02 2012-06-02
 * @author Cay Horstmann
 */
public class RegexTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws PatternSyntaxException {
		Charset cs = StandardCharsets.UTF_8;
		String patternString;
		String help = "Enter pattern like "
				+ "\n([Jj][a|i][a-z|']*{4,6}) - Works with Matcher.find()"
				+ "\nor "
				+ "\n([Jj]a.*)([Jj]i.*) - Works with Matcher.matches(): ";
		String value = "([Jj][a|i][a-z|']*{4,6}) or ([Jj]a.*)([Jj]i.*)";
		int runType = file.setChoice("CJS", "txt", "", "", cs, help, value, "");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		int occurence = 0;
		if (runType <= 1) {
			try {
				Scanner in = null;
				if (runType == 0) {
					in = new Scanner(System.in);
					System.out.println(help);
					patternString = in.nextLine();
				} else
					patternString = file.getInput();
				
				Pattern pattern = Pattern.compile(patternString);
				
				while (true) {
					System.out.println("Enter String to match: ");
					String input;
					if (runType == 0) input = in.nextLine();
					else input = JOptionPane.showInputDialog("Enter String to match: ");
					if (input == null || input.equals("")) {
						if (runType == 0) in.close();
						Views.openWindowOpener(MAIN_CLASS, message);
						return;
					}
					Matcher matcher = pattern.matcher(input);
					/**
					 * Added in to retrieve only words that match patterns like
					 *  <em>([Jj][a|i][a-z|']*{4,6})</em> and have them displayed on separate lines <br />
					 */
					while (matcher.find()) {
						if (matcher.group().length() != 0) {
							System.out.println(matcher.group().trim() + ", start: " + matcher.start() +
							", end: " + matcher.end());
							occurence++;
						}
					}
					System.out.println("Total occurences using Matcher.find(): " + String.format("%,d", occurence));
					/**
					 * Original code does not work with patterns like <em>([Jj][a|i][a-z|']*{4,4})</em> and returns all
					 * other words between with patterns like <em>([Jj]a.*)([Jj]i.*)</em> <br />
					 */
					if (matcher.matches()) {
						System.out.println("Match ");
						int g = matcher.groupCount();
						if (g > 0) { 
							for (int i = 0; i < input.length(); i++) {
								//Print any empty groups
								for (int j = 1; j <= g; j++) 
									if (i == matcher.start(j) && i == matcher.end(j))
										System.out.print("()");
								// Print ( for no-empty groups starting here
								for (int j = 1; j <= g; j++)
									if (i == matcher.start(j) && i != matcher.end(j))
										System.out.print("(");
								System.out.print(input.charAt(i));
								//Print ) for non-empty groups ending here
								for (int j = 1; j <= g; j++)
									if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j))
										System.out.print(")");
							}
							System.out.println();
						}
					} else {
						System.out.println("No match using Matcher.matches()!");
					}
				}
			} catch (NoSuchElementException ex) {
				message = Threads.runJarProcess("cp", RegexTest.class.getName(), "Chapter02", message);
			}
		} else {
			patternString = 
				JOptionPane.showInputDialog(help, value);
			
			Pattern pattern = Pattern.compile(patternString);
			
			String input = file.toString();
			Matcher matcher = pattern.matcher(input);
			/**
			 * Added in to retrieve only words that match patterns like <em>([Jj][a|i][a-z|']*{4,6})</em>
			 * and have them displayed on separate lines
			 */
			while (matcher.find()) {
				if (matcher.group().length() != 0) {
					System.out.println(matcher.group().trim() + ", start: " + matcher.start() +
					", end: " + matcher.end());
					occurence++;
				}
			}
			System.out.println("Total occurences using Matcher.find(): " + String.format("%,d", occurence));
			/**
			 * Original code does not work with patterns like <em>([Jj][a|i][a-z|']*{4,6})</em> and returns all
			 * other words between with patterns like <em>([Jj]a.*)([Jj]i.*)</em> <br />
			 */
			if (matcher.matches()) {
				System.out.println("Match ");
				int g = matcher.groupCount();
				if (g > 0) { 
					for (int i = 0; i < input.length(); i++) {
						//Print any empty groups
						for (int j = 1; j <= g; j++) 
							if (i == matcher.start(j) && i == matcher.end(j))
								System.out.print("()");
						// Print ( for no-empty groups starting here
						for (int j = 1; j <= g; j++)
							if (i == matcher.start(j) && i != matcher.end(j))
								System.out.print("(");
						System.out.print(input.charAt(i));
						//Print ) for non-empty groups ending here
						for (int j = 1; j <= g; j++)
							if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j))
								System.out.print(")");
					}
					System.out.println();
				}
			} else {
				System.out.println("No match using Matcher.matches()!");
			}
			message = "";
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
