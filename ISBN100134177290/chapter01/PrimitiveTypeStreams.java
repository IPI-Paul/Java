package chapter01;  // streams

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 */
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import ipi.*;

/**
 * {@code PrimitiveTypeStreams} class Listing 1.7 <br />
 * This program gives examples for the API of primitive type streams. <br />
 * @author Cay Horstmann
 */
public class PrimitiveTypeStreams {
	private static final String MAIN_CLASS = "chapter01.Chapter01";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void show(String title, IntStream stream) {
		final int SIZE = 10;
		int[] firstElements = stream.limit(SIZE + 1).toArray();
		System.out.print(title + ": ");
		for (int i = 0; i < firstElements.length; i++) {
			if (i > 0) System.out.print(", ");
			if (i < SIZE) System.out.print(firstElements[i]);
			else System.out.print("...");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
		show("is1", is1);
		IntStream is2 = IntStream.range(5, 10);
		show("is2", is2);
		IntStream is3 = IntStream.rangeClosed(5, 10);
		show("is3", is3);
		
		/**
		 * Altered to enable web start support. <br />
		Path path = Paths.get("../gutenberg/alice30.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		 */
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "txt", "", "alice30.txt", cs, "", "", "For Source Text File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		String contents = file.toString();
		
		Stream<String> words = Stream.of(contents.split("\\PL+"));
		IntStream is4 = words.mapToInt(String::length);
		show("is4", is4);
		
		String sentence = "\uD835\uDD46 is the set of octonions.";
		System.out.println(sentence);		
		IntStream codes = sentence.codePoints();
		System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(
				Collectors.joining()));
		
		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		IntStream is5 = integers.mapToInt(Integer::intValue);
		show("is5", is5);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
