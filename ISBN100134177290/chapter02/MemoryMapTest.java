package chapter02;  // memoryMap

import java.io.*;
import java.lang.reflect.Method;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.AccessControlException;
import java.util.zip.*;
import javax.jnlp.FileContents;
import javax.jnlp.JNLPRandomAccessFile;
import ipi.*;

/**
 * {@code MemoryMapTest} class Listing 2.5 <br />
 * This program computes the CRC checksum of a file in four ways. <br />
 * Original Usage: java chapter02.MemoryMapTest filename (<b>Modified to also use File Dialogs</b>) <br />
 * @version 1.01 2012-05-30
 * @author Cay Horstmann
 */
public class MemoryMapTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static long checksumInputStream(Path filename) throws IOException {
		try (InputStream in = Files.newInputStream(filename)) {
			CRC32 crc = new CRC32();
			
			int c;
			while ((c = in.read()) != -1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumBufferedInputStream(Path filename) throws IOException {
		try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
			CRC32 crc = new CRC32();
			
			int c;
			while ((c = in.read()) != -1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumRandomAccessFile(Path filename) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
			long length = file.length();
			CRC32 crc = new CRC32();
			
			for (long p = 0; p < length; p++) {
				file.seek(p);
				int c = file.readByte();
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumMappedFile(Path filename) throws IOException {
		try (FileChannel channel = FileChannel.open(filename)) {
			CRC32 crc = new CRC32();
			int length = (int) channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 
					0, length);
			
			for (int p = 0; p < length; p++) {
				int c = buffer.get(p);
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumInputStream(InputStream fileInputStream) throws IOException {
		try (InputStream in = fileInputStream) {
			CRC32 crc = new CRC32();
			
			int c;
			while ((c = in.read()) != -1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumBufferedInputStream(InputStream fileInputStream) throws IOException {
		try (InputStream in = new BufferedInputStream(fileInputStream)) {
			CRC32 crc = new CRC32();
			
			int c;
			while ((c = in.read()) != -1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumRandomAccessFile(FileContents contents) throws IOException {
		CRC32 crc = new CRC32();
		try {
			JNLPRandomAccessFile raf = contents.getRandomAccessFile("r");
			long length = raf.length();
			
			for (long p = 0; p < length; p++) {
				raf.seek(p);
				int c = raf.readByte();
				crc.update(c);
			}
		} finally {}
		return crc.getValue();
	}
	
	public static long checksumMappedFile(InputStream fileInputStream) throws IOException {
		try (FileChannel channel = ((FileInputStream) fileInputStream).getChannel()) {
			CRC32 crc = new CRC32();
			int length = (int) channel.size();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 
					0, length);
			
			for (int p = 0; p < length; p++) {
				int c = buffer.get(p);
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("S", "app", "", "", cs, "", "", "For ZIP/JAR File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		try {
			Path filename = file.getPath();
			System.out.println("Input Stream: ");
			long start = System.currentTimeMillis();
			long crcValue = checksumInputStream(filename);
			long end = System.currentTimeMillis();
			System.out.println("CRC32 Value: " + Long.toHexString(crcValue));
			System.out.println(String.format("Time Taken: %,d milliseconds", end - start)
					 + System.lineSeparator());
			
			System.out.println("Buffered Input Stream: ");
			start = System.currentTimeMillis();
			crcValue = checksumBufferedInputStream(filename);
			end = System.currentTimeMillis();
			System.out.println("CRC32 Value: " + Long.toHexString(crcValue));
			System.out.println(String.format("Time Taken: %,d  milliseconds", end - start)
					 + System.lineSeparator());
			
			System.out.println("Random Access File: ");
			start = System.currentTimeMillis();
			crcValue = checksumRandomAccessFile(filename);
			end = System.currentTimeMillis();
			System.out.println("CRC32 Value: " + Long.toHexString(crcValue));
			System.out.println(String.format("Time Taken: %,d  milliseconds", end - start)
					 + System.lineSeparator());
			
			System.out.println("Mapped File: ");
			start = System.currentTimeMillis();
			crcValue = checksumMappedFile(filename);
			end = System.currentTimeMillis();
			System.out.println("CRC32 Value: " + Long.toHexString(crcValue));
			System.out.println(String.format("Time Taken: %,d  milliseconds", end - start)
					 + System.lineSeparator());
		} catch (AccessControlException ex) {
			message = Threads.getZIPJnlpMessage("readZipInputStream");
			getResults("", "checksumInputStream");
			getResults("Buffered Input Stream: ", "checksumBufferedInputStream");
			getResults("Random Access File: ", "checksumRandomAccessFile");
			getResults("Mapped File: ", "checksumMappedFile");
			message = "";
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	public static void getResults(String title, String method) {
		if (title.length() > 0) System.out.println(title);
		long start = System.currentTimeMillis();
		long crcValue = 0;
		long end = System.currentTimeMillis();
		if (method.equals("checksumRandomAccessFile") ) {
			try {
				Method m = MemoryMapTest.class.getMethod(method, FileContents.class);
				start = System.currentTimeMillis();
				crcValue = (long) m.invoke(null, file.getContents());
				end = System.currentTimeMillis();
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		} else {
			try {
				Method m = MemoryMapTest.class.getMethod(method, InputStream.class);
				start = System.currentTimeMillis();
				crcValue = (long) m.invoke(null, file.getInputStream());
				end = System.currentTimeMillis();
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("CRC32 Value: " + Long.toHexString(crcValue));
		System.out.println(String.format("Time Taken: %,d milliseconds", end - start)
				 + System.lineSeparator());
	}
}
