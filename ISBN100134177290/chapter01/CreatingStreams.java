package chapter01;  // streams

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 */
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ipi.*;

/**
 * {@code CreatingStreams} class Listing 1.2 <br />
 * This program demonstrates the various ways of crating a stream.
 */
public class CreatingStreams {
	private static final String MAIN_CLASS = "chapter01.Chapter01";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static <T> void show(String title, Stream<T> stream) {
		final int SIZE = 10;
		List<T> firstElements = stream
				.limit(SIZE + 1)
				.collect(Collectors.toList());
		System.out.print(title + ": ");
		for (int i = 0; i < firstElements.size(); i++) {
			if (i > 0) System.out.print(", ");
			if (i < SIZE) System.out.print(firstElements.get(i));
			else System.out.print("...");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		/**
		 * Altered to enable web start support. <br />
		path = Paths.get(FolderPaths.getTextFolder() + "alice30.txt");
		contents = new String(Files.readAllBytes(
				path), StandardCharsets.UTF_8);
		 */
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "txt", "", "alice30.txt", cs, "", "", "For Source Text File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		/**
		 * Amended to enable web start mode <br />
		Path path = file.getPath();
		 */
		String contents = file.toString();		
		Stream<String> words = Stream.of(contents.split("\\PL+"));
		show("words", words);
		Stream<String> song = Stream.of("gently", "down", "the", "stream");
		show("song", song);
		Stream<String> silence = Stream.empty();
		show("silence", silence);
		
		Stream<String> echos = Stream.generate(() -> "Echo");
		show("echos", echos);
		
		Stream<Double> randoms = Stream.generate(Math::random);
		show("randoms", randoms);
		
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
		show("integers", integers);
		
		Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(
				contents);
		show("wordsAnotherWay", wordsAnotherWay);
		
		/**
		 * Amended to enable web start mode <br />
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			show("lines", lines);
		}
		*/
		show("lines", file.toLines(StandardCharsets.UTF_8));
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
