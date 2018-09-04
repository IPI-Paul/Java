package chapter07;  // retire

import java.util.*;
import javax.swing.*;

/**
 * {@code LocaleCombo}&lt;T> class extends {@link JComboBox}&lt;String> copied from EnumCombo in listing 7.3 <br />
 * A combo box that lets users choose from among static field
 * values whose names are given in the constructor.  <br />
 * @version 1.15 2016-05-06
 * @author Cay Horstmann
 */
public class LocaleCombo<T> extends JComboBox<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs an {@link LocaleCombo} yielding values of type T. <br />
	 * @param locales an array of strings describing static field names of cl that have type T <br />
	 */
	public LocaleCombo(Locale[] locales) {
		for (Locale locale : locales) {
			addItem(locale.getDisplayName(locale));
		}
	}
}
