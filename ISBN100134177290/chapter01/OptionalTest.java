package chapter01;  // optional

import java.io.*;
import java.nio.charset.*;
/**
import java.nio.file.*;
 */
import java.util.*;
import javax.swing.JOptionPane;
import ipi.*;

/**
 * {@code OptionalTest} class Listing 1.3 <br />
 * This program demonstrates the <code>optional</code> API <br />
 */
public class OptionalTest {
	private static final String MAIN_CLASS = "chapter01.Chapter01";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException {
		/**
		 * Altered to enable web start support. <br />
		String contents = new String(Files.readAllBytes(
				Paths.get("../gutenberg/alice30.txt")), StandardCharsets.UTF_8);
		 */
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "txt", "", "alice30.txt", cs, "", "", "For Source Text File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		String contents = file.toString();
		List<String> wordList = Arrays.asList(contents.split("\\PL+"));
		
		final String searchText = JOptionPane.showInputDialog(null, "Please give a part of word or whole "
				+ "word to search for:", "fred") + "";
		Optional<String> optionalValue = wordList.stream()
				.filter(s -> s.contains(searchText))
				.findFirst();
		System.out.println(optionalValue.orElse("No word") + " contains " + searchText);
		
		Optional<String> optionalString = Optional.empty();
		String result = optionalString.orElse("N/A");
		System.out.println("result: " + result);
		
		result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
		System.out.println("result: " + result);
		
		try {
			result = optionalString.orElseThrow(IllegalStateException::new);
			System.out.println("result: " + result);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		String searchText1 = JOptionPane.showInputDialog(null, "Please give a part of word or whole word to "
				+ "search for:", "red") + "";
		optionalValue = wordList.stream()
				.filter(s -> s.contains(searchText1))
				.findFirst();
		optionalValue.ifPresent(s -> System.out.println(s + " contains " + searchText1));
		
		Set<String> results = new HashSet<>();
		optionalValue.ifPresent(results::add);
		Optional<Boolean> added = optionalValue.map(results::add);
		System.out.println(added);
		
		System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
		System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
		System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));
		
		Optional<Double> result2 = Optional.of(-4.0)
				.flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
		System.out.println(result2);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	public static Optional<Double> inverse(Double x) {
		return x == 0 ? Optional.empty() : Optional.of(1 / x);
	}
	
	public static Optional<Double> squareRoot(Double x) {
		return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
	}
}
