package chapter09;  // jaas

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

/**
 * {@code SimpleLoginModule} class implements LoginModule Listing 9.11 <br />  
 * {@link SimplePrincipal} class implements Principal Listing 9.10 <br />
 * {@link SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
 * {@link JAASTest} class Listing 9.13 <br />
 * {@link JAASFrame} class extends JFrame <br />
 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
 * This login module authenticates users by reading usernames, passwords, and roles from a text file. <br />
 */
public class SimpleLoginModule implements LoginModule {
	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> options;
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, 
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.options = options;
	}
	
	public boolean login() throws LoginException {
		if (callbackHandler == null) throw new LoginException("no handler");
		
		NameCallback nameCall = new NameCallback("username: ");
		PasswordCallback passCall = new PasswordCallback("password: ", false);
		try {
			callbackHandler.handle(new Callback[] {nameCall, passCall});
		} catch (UnsupportedCallbackException e) {
			LoginException e2 = new LoginException("Unsupported callback");
			e2.initCause(e);
			throw e2;
		} catch (IOException e) {
			LoginException e2 = new LoginException("I/O exception in callback");
			e2.initCause(e);
			throw e2;
		}
		
		try {
			return checkLogin(nameCall.getName(), passCall.getPassword());
		} catch (IOException ex) {
			LoginException ex2 = new LoginException();
			ex2.initCause(ex);
			throw ex2;
		}
	}
	
	/**
	 * Checks whether the authentication information is valid. If it is, the subject acquires
	 * principals for the user name and role. <br />
	 * @param username the user name <br />
	 * @param password a character array containing the password <br />
	 * @return true if the authentication information s valid <br />
	 */
	private boolean checkLogin(String username, char[] password) throws LoginException, IOException {
		try (Scanner in = new Scanner(Paths.get("" + options.get("pwfile")), 
				"UTF-8")){
			while (in.hasNextLine()) {
				String[] inputs = in.nextLine().split("\\|");
				if (inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(), password)) {
					String role = inputs[2];
					Set<Principal> principals = subject.getPrincipals();
					principals.add(new SimplePrincipal("username", username));
					principals.add(new SimplePrincipal("role", role));
					return true;
				}
			}
			return false;
		}
	}
	
	public boolean logout() {
		return true;
	}
	
	public boolean abort() {
		return true;
	}
	
	public boolean commit() {
		return true;
	}
}
