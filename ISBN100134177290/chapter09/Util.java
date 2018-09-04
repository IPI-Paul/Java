package chapter09; // aes

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * {@code Util} class Listing 9.19 <br />
 * {@link AESTest} class Listing 9.18 <br />
 */
public class Util {
	/**
	 * Uses a cipher to transform the bytes in an input stream and sends the transformed bytes to 
	 * an output stream. <br />
	 * @param in the input stream <br />
	 * @param out the output stream <br />
	 * @param cipher the cipher that transforms the bytes <br />
	 */
	public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, 
			GeneralSecurityException {
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		
		int inLength = 0;
		boolean more = true;
		while (more) {
			inLength = in.read(inBytes);
			if (inLength == blockSize) {
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			} else more = false;
		}
		if (inLength > 0) outBytes = cipher.doFinal(inBytes, 0, inLength);
		else outBytes = cipher.doFinal();
		out.write(outBytes);
	}
}
