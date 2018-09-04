package chapter04;  // post

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.AccessControlException;
import java.util.*;
import ipi.*;

/**
 * {@code PostTest} class Listing 4.7 <br />
 * <a href="../sourceFiles/properties/posttest-ch04.properties">posttest-ch04.properties</a> contains url
 * and variables for the action script <br />
 * <a href="../sourceFiles/properties/posttest1-ch04.properties">posttest1-ch04.properties</a> contains url
 * and variables for the action script <br />
 * This program demonstrates how to use the {@link URLConnection} class for a POST
 * request. <br />
 * @version 1.40 2016-04-24
 * @author Cay Horstmann
 */
public class PostTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException {
		/**
		String propsFilename = args.length > 0 ? args[0] : "post/post.properties";
		 */
		Charset cs = StandardCharsets.UTF_8;
		if (args == null || args.length == 0) {
			file.setChoice("DS", "prop", "", "posttest-ch04.properties", cs, "", "",  "For Properties File");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
		}
		String propsFilename = args != null && args.length > 0 ? args[0] : file.getFilePath();		
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get(propsFilename))) {
			props.load(in);
		} catch (AccessControlException | NullPointerException ex) {
			props.load(file.getInputStream());
		}
		String urlString = props.remove("url").toString();
		Object userAgent = props.remove("User-Agent");
		Object redirects = props.remove("redirects");
		try {
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		} catch (AccessControlException ex) {}
		String result = doPost(new URL(urlString), props,
			userAgent == null ? null : userAgent.toString(),
			redirects == null ? -1 : Integer.parseInt(redirects.toString())
			);
		System.out.println(result);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Do an HTTP POST. <br />
	 * @param url the URL to post to <br />
	 * @param nameValuePairs the query parameters <br />
	 * @param userAgent the user agent to use, or null for the default user agent <br />
	 * @param redirects the number of redirects to follow manaully, or -1 for automatic redirects
	 * @return the data returned from the server <br />
	 */
	public static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, 
			int redirects) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (userAgent != null) 
			connection.setRequestProperty("User-Agent", userAgent);
		
		if (redirects >= 0) 
			connection.setInstanceFollowRedirects(false);
		
		connection.setDoOutput(true);
		
		try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
			boolean first = true;
			for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
				if (first) first = false;
				else out.print("&");
				String name = pair.getKey().toString();
				String value = pair.getValue().toString();
				out.print(name);
				out.print('=');
				out.print(URLEncoder.encode(value, "UTF-8"));
			}
		}
		String encoding = connection.getContentEncoding();
		if (encoding == null) encoding = "UTF-8";
		
		if (redirects > 0) {
			int responseCode = connection.getResponseCode();
			if (responseCode ==HttpURLConnection.HTTP_MOVED_PERM 
					|| responseCode == HttpURLConnection.HTTP_MOVED_TEMP
					|| responseCode == HttpURLConnection.HTTP_SEE_OTHER
					) {
				String location = connection.getHeaderField("Location");
				if (location != null) {
					URL base = connection.getURL();
					connection.disconnect();
					return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
				}
			}
		} else if (redirects == 0) {
			throw new IOException("Too many redirects");
		}
		
		StringBuilder response = new StringBuilder();
		try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
			while (in.hasNextLine()) {
				response.append(in.nextLine());
				response.append("\n");
			}
		} catch (IOException e) {
			InputStream err = connection.getErrorStream();
			if (err == null) throw e;
			try (Scanner in = new Scanner(err)) {
				response.append(in.nextLine());
				response.append("\n");
			}
		}
		
		return response.toString();
	}
}
