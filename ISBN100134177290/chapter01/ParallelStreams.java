package chapter01;  // parallel

import static java.util.stream.Collectors.*;
import java.io.*;
import java.nio.charset.*;
/**
import java.nio.file.*;
 */
import java.util.*;
import java.util.stream.*;
import javax.swing.JOptionPane;
import ipi.*;

/**
 * {@code ParallelStreams} class Listing 1.8 <br />
 * This program demonstrates how to work with parallel streams.
 * @author Cay Horstmann
 */
public class ParallelStreams {
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
		
		// Very bad code ahead
		int[] shortWords = new int[10];
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) shortWords[s.length()]++;
		});
		System.out.println("Very bad code: " + Arrays.toString(shortWords));
		
		// Try again--the result will likely be different (and also wrong)
		Arrays.fill(shortWords, 0);
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) shortWords[s.length()]++;
		});
		System.out.println("Very bad code: " + Arrays.toString(shortWords));
		
		// Remedy: Group and count
		Map<Integer, Long> shortWordsCounts = wordList.parallelStream()
				.filter(s -> s.length() < 10)
				.collect(groupingBy(String::length, counting()));
		System.out.println("shortWordsCounts: " + shortWordsCounts);
		
		// Downstream order not deterministic
		Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
				groupingByConcurrent(String::length, counting()));
		System.out.println(wordCounts);
		
		String wordFilter = JOptionPane.showInputDialog(null, "Please give the word length to filter on:", "14");
		if (wordFilter == null) wordFilter = "14";
		Integer wordLength = Integer.parseInt(wordFilter);
		Map<Integer, List<String>> result = wordList.parallelStream().collect(
				Collectors.groupingByConcurrent(String::length));
		System.out.println("result.get(" + wordLength + "): " + result.get(wordLength));
		
		result = wordList.parallelStream().collect(
				Collectors.groupingByConcurrent(String::length));
		System.out.println("result.get(" + wordLength + "): " + result.get(wordLength));
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
