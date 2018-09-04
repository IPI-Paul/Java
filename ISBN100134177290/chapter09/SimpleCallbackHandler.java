package chapter09;  // jaas

import javax.security.auth.callback.*;

/**
 * {@code SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
 * {@link SimplePrincipal} class implements Principal Listing 9.10 <br />
 * {@link SimpleLoginModule} class implements LoginModule Listing 9.11 <br />  
 * {@link JAASTest} class Listing 9.13 <br />
 * {@link JAASFrame} class extends JFrame <br />
 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
 * This simple callback handler presents the given user name and password. <br />
 */
public class SimpleCallbackHandler implements CallbackHandler {
	private String username;
	private char[] password;
	
	/**
	 * Constructs the callback handler. <br />
	 * @param username the user name <br />
	 * @param password a character array containing the password <br />
	 */
	public SimpleCallbackHandler(String username, char[] password) {
		this.username = username;
		this.password = password;
	}
	
	public void handle(Callback[] callbacks) {
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				((NameCallback) callback).setName(username);
			} else if (callback instanceof PasswordCallback) {
				((PasswordCallback) callback).setPassword(password);
			}
		}
	}
}
