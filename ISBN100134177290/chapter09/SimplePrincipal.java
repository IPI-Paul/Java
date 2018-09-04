package chapter09;  // jaas

import java.io.Serializable;
import java.security.*;
import java.util.*;

/**
 * {@code SimplePrincipal} class implements Principal Listing 9.10 <br /> 
 * {@link SimpleLoginModule} class implements LoginModule Listing 9.11 <br />
 * {@link SimpleCallbackHandler} class implements CallbackHandler listing 9.12 <br />
 * {@link JAASTest} class Listing 9.13 <br />
 * {@link JAASFrame} class extends JFrame <br />
 * <a href="../sourceFiles/permissions/JAASTest-ch09.policy">JAASTest-ch09.policy</a> <br />
 * <a href="../sourceFiles/settings/JAASTest-jaas-ch09.config">JAASTest-jaas-ch09.config</a> <br />
 * <a href="../sourceFiles/text/password-ch09.txt">password-ch09.txt</a> <br />
 * A principal with a named value (such as "role=HR" or "username=harry"). <br />
 */
public class SimplePrincipal implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
	private String descr;
	private String value;
	
	/**
	 * Constructs a SimplePrincipal to hold a description and a value. <br />
	 * @param descr the description <br />
	 * @param value the associated value <br />
	 */
	public SimplePrincipal(String name) {
		this.descr = name.split("=")[0];
		this.value = name.split("=")[1];
	}
	
	/**
	 * Constructs a SimplePrincipal to hold a description and a value. <br />
	 * @param descr the description <br />
	 * @param value the associated value <br />
	 */
	public SimplePrincipal(String descr, String value) {
		this.descr = descr;
		this.value = value;
	}
	

	/**
	 * Returns the role name of this principal. <br />
	 * @return the role name <br />
	 */
	public String getName() {
		return descr + "=" + value;
	}
	
	
	/**
	 * Returns the role name of this principal. <br />
	 * @return the role name <br />
	 */
	public String toString() {
		return descr + "=" + value;
	}

	public boolean equals(Object otherObject) {
		if (this == otherObject) return true;
		if (otherObject ==  null) return false;
		if (getClass() != otherObject.getClass()) return false;
 		SimplePrincipal other = (SimplePrincipal) otherObject;
		return Objects.equals(getName(), other.getName());
	}
	
	public int hashCode() {
		return Objects.hashCode(getName());
	}
}
