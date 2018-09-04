package chapter09;  // aes

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * {@code AESTest} class Listing 9.18 <br />
 * {@link Util} class Listing 9.19 <br />
 * This program tests the AES cipher. <br /> 
 * Usage: <br />
 * java chapter09.AESTest -genkey keyfile <br />
 * java chapter09.AESTest -encyrypt plaintext encrypted keyfile <br />
 * java chapter09.AESTest -decrypt encrypted decrypted keyfile <br />
 * @version 1.01 2012-06-10
 * @author Cay Horstmann
 */
public class AESTest {
	public static void main(String[] args) throws IOException, GeneralSecurityException, 
			ClassNotFoundException {
		if (args[0].equals("-genkey")) {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]))){
				out.writeObject(key);
			}
		} else {
			int mode;
			if (args[0].equals("-encrypt")) mode = Cipher.ENCRYPT_MODE;
			else mode = Cipher.DECRYPT_MODE;
			
			try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
					InputStream in = new FileInputStream(args[1]);
					OutputStream out = new FileOutputStream(args[2])
					){
				Key key = (Key) keyIn.readObject();
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(mode, key);
				Util.crypt(in, out, cipher);
			}
		}
	}
}
