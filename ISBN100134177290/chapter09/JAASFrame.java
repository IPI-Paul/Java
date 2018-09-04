package chapter09;  // jaas

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.security.AccessControlException;

import javax.security.auth.*;
import javax.security.auth.login.*;
import javax.swing.*;

/**
 * {@code JAASFrame} class extends JFrame <br />
 * {@link JAASTest} class Listing 9.13 <br />
 * {@link SimplePrincipal} class implements Principal Listing 9.10 <br />
 * {@link SimpleLoginModule} class implements LoginModule Listing 9.11 <br /> 
 * {@link SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
 * This frame has text fields for user name and password, a field for the name of the requested
 * system property, and a field to show the propertty value. <br />
 * @author mthli
 */
public class JAASFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	private JTextField propertyName;
	private JTextField propertyValue;
	
	public JAASFrame() {
		username = new JTextField(20);
		password = new JPasswordField(20);
		propertyName = new JTextField("user.home");
		propertyValue = new JTextField(20);
		propertyValue.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("username:"));
		panel.add(username);
		panel.add(new JLabel("password:"));
		panel.add(password);
		panel.add(propertyName);
		panel.add(propertyValue);
		add(panel, BorderLayout.CENTER);
		
		JButton getValueButton = new JButton("Get Value");
		getValueButton.addActionListener(EventHandler.create(ActionListener.class, this, "getValue"));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getValueButton);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public void getValue() {
		try {
			LoginContext context = new LoginContext("Login1", new SimpleCallbackHandler(
					username.getText(), password.getPassword()));
			System.out.println("Trying to log in with " + username.getText() + " and " + 
					new String(password.getPassword()));
			context.login();
			Subject subject = context.getSubject();
			try {
				propertyValue.setText("" +
						Subject.doAsPrivileged(subject, new SysPropAction(propertyName.getText()), null));
			} catch (AccessControlException e) {
				propertyValue.setText("Please ask your admin for access rights!");
			}
			context.logout();
		} catch (LoginException ex) {
			ex.printStackTrace();
			Throwable ex2 = ex.getCause();
			if (ex2 != null) ex2.printStackTrace();
			propertyValue.setText("Incorrect password or username!");
		}
	}
}
