package chapter07;  // retire

import java.awt.*;

/**
 * {@code RetireResources_zh} class extends java.util.ListResourceBundle Listing 7.8 <br />
 * These are the Chinese non-string resources for the retirement calculator. <br />
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_zh extends java.util.ListResourceBundle {
	private static final Object[][] contents = {
			// BEGIN LOACALIZE
			{"colorPre", Color.red}, {"colorGain", Color.blue}, {"colorLoss", Color.yellow}
			//END LOCALIZE
	};
	
	public Object[][] getContents() {
		return contents;
	}
}
