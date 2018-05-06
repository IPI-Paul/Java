package chapter14;  // threadPool

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import ipi.*;

/**
 * ThreadPoolTest class Listing 14.12 <br />
 * MatchCounterPool callable inner class <br />
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class ThreadPoolTest {
	private static final String MAIN_CLASS = "chapter14.Chapter14";
	private static String message = "";
	
	public static void main(String[] args) throws Exception {
		if (Threads.isInClosed() == false) {
			try {
				Scanner in = new Scanner(System.in);
				System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
				String directory = in.nextLine();
				System.out.print("Enter keyword (e.g. volatile): ");
				String keyword = in.nextLine();
				
				ExecutorService pool = Executors.newCachedThreadPool();
				
				MatchCounterPool counter = new MatchCounterPool(new File(directory), keyword, pool);
				Future<Integer> result = pool.submit(counter);
				
				try {
					System.out.println(result.get() + " matching files.");
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
				}
				pool.shutdown();
				
				int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
				System.out.println("largest pool size=" + largestPoolSize);
				Threads.closeStream(in);
			} catch (NoSuchElementException e) {
				message = Threads.runJarProcess("cp", ThreadPoolTest.class.getName(), "Chapter14", message);
			}
		} else {
			message = Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

/**
 * This task counts the files in a directory and its sub-directories that contain a given keyword.
 */
class MatchCounterPool implements Callable<Integer> {
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	
	/**
	 * Constructs a MatchCounterPool.
	 * @param directory the directory in which to start the search  
	 * @param keyword the keyword to look for
	 * @param pool the thread pool for submitting subtasks
	 */
	public MatchCounterPool(File directory, String keyword, ExecutorService pool) {
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}
	
	public Integer call() {
		count = 0;
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			
			for (File file : files)
				if (file.isDirectory()) {
					MatchCounterPool counter = new MatchCounterPool(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					results.add(result);
				} else {
					if (search(file)) count++;
				}
			for (Future<Integer> result : results)
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		} catch (InterruptedException e) {
		}
		return count;
	}
	
	public boolean search(File file) {
		try {
			try (Scanner in = new Scanner(file)) {
				boolean found = false;
				while (!found && in.hasNextLine()) {
					String line = in.nextLine();
					if (line.contains(keyword)) found = true;
				}
				return found;
			}
		} catch (IOException e) {
			return false;
		}
	}
}

