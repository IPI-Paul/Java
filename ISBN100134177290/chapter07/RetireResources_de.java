package chapter07;  // retire

import java.awt.*;

/**
 * {@code RetireResources_de} class extends java.util.ListResourceBundle Listing 7.7 <br />
 * These are the German non-string resources for the retirement calculator. <br />
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_de extends java.util.ListResourceBundle {
	private static final Object[][] contents = {
			// BEGIN LOACALIZE
			{"colorPre", Color.yellow}, {"colorGain", Color.black}, {"colorLoss", Color.red}
			//END LOCALIZE
	};
	
	public Object[][] getContents() {
		return contents;
	}
}
