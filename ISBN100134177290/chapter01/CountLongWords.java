package chapter01;  // streams

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
import java.nio.file.Files;
import java.nio.file.Paths;
 */
import java.util.Arrays;
import java.util.List;
import ipi.*;

/**
 * {@code CountLongWords} class Listing 1.1 <br />
 * This program demonstrates a stream that is created with the <code>stream</code> or 
 * <code>parallelStream</code> method. The <code>filter</code> method transforms it, and 
 * <code>count</code> is the terminal operation <br />
 */
public class CountLongWords {
	private static final String MAIN_CLASS = "chapter01.Chapter01";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException {
		/**
		 * Altered to enable web start support. <br />
		contents = new String(Files.readAllBytes(
				Paths.get(FolderPaths.getTextFolder() + "alice30.txt")), StandardCharsets.UTF_8);
		 */
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "txt", "", "alice30.txt", cs, "", "", "For Source Text File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		String contents = file.toString();
		List<String> words = Arrays.asList(contents.split("\\PL+"));
		
		long count = 0;
		for (String w : words) {
			if (w.length() > 12) count++;
		}
		System.out.println(count);
		
		count = words.stream().filter(w -> w.length() > 12).count();
		System.out.println(count);
		
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		System.out.println(count);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
