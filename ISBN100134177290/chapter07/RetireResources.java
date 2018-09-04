package chapter07;  // retire

import java.awt.*;

/**
 * {@code RetireResources} class extends java.util.ListResourceBundle Listing 7.6 <br />
 * These are the English non-string resources for the retirement calculator. <br />
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources extends java.util.ListResourceBundle {
	private static final Object[][] contents = {
			// BEGIN LOACALIZE
			{"colorPre", Color.blue}, {"colorGain", Color.white}, {"colorLoss", Color.red}
			//END LOCALIZE
	};
	
	public Object[][] getContents() {
		return contents;
	}
}
