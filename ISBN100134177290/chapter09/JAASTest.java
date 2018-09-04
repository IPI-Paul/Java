package chapter09;  // jaas

import java.awt.*;
import javax.swing.*;

/**
 * {@code JAASTest} class Listing 9.13 <br />
 * {@link SimplePrincipal} class implements Principal Listing 9.10 <br />
 * {@link SimpleLoginModule} class implements LoginModule Listing 9.11 <br /> 
 * {@link SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
 * {@link JAASFrame} class extends JFrame <br />
 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
 * This program authenticates a user via a custom login and then looks up a system 
 * property with the user's privileges. <br />
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class JAASTest {
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		EventQueue.invokeLater(() -> {
			JFrame frame = new JAASFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("JAAS Test");
			frame.setVisible(true);
		});
	}
}
