package chapter09; // auth

import java.io.Serializable;
import java.security.*;

/**
 * {@code SysPropAction} class implements PrivilegedAction&gt;String> <br /> 
 * {@link AuthTest} class Listing 9.6 <br />
 * <a href="../sourceFiles/permissions/AuthTest-ch09.policy">AuthTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/jaas-ch09.config">jaas-ch09.config</a> <br />
 * This action looks up a system property. <br />
 * @version 1.01 2007-10-06
 * @author Cay Horstmann
 */
public class SysPropAction implements PrivilegedAction<String>, Serializable {
	private static final long serialVersionUID = 1L;
	private String propertyName;
	
	/** 
	 * Constructs an action for looking up a given property. <br />
	 * @param propertyName the property name (such as "user.home") <br /> 
	 */
	public SysPropAction(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String run() {
		return System.getProperty(propertyName);
	}
}
