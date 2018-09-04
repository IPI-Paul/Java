package chapter07;  // dateFormat

import java.util.*;
import javax.swing.*;

/**
 * {@code EnumCombo}&lt;T> class extends {@link JComboBox}&lt;String> listing 7.3 <br />
 * A combo box that lets users choose from among static field
 * values whose names are given in the constructor.  <br />
 * @version 1.15 2016-05-06
 * @author Cay Horstmann
 */
public class EnumCombo<T> extends JComboBox<String> {
	private static final long serialVersionUID = 1L;
	private Map<String, T> table = new TreeMap<>();
	
	/**
	 * Constructs an {@link EnumCombo} yielding values of type T. <br />
	 * @param cl a class <br />
	 * @param labels an array of strings describing static field names of cl that have type T <br />
	 */
	public EnumCombo(Class<?> cl, String... labels) {
		for (String label : labels) {
			String name = label.toUpperCase().replace(' ', '_');
			try {
				java.lang.reflect.Field f = cl.getField(name);
				@SuppressWarnings("unchecked") T value = (T) f.get(cl);
				table.put(label, value);
			} catch (Exception  e) {
				label = "(" + label + ")";
				table.put(label, null);
			}
			addItem(label);
		}
		setSelectedItem(labels[0]);
	}
	
	/**
	 * Returns the value of the field that the user selected. <br />
	 * @return the static field value <br />
	 */
	public T getValue() {
		return table.get(getSelectedItem());
	}
}
