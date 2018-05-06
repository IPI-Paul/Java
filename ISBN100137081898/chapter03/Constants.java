package chapter03;

/**
 * final is used to declare constants. The one used below is method specific 
 * @author Paul *
 */
public class Constants {
	public static void main(String[] args) {
		final double CM_PER_INCH = 2.54;
		double paperWidth = 8.5;
		double paperHeight = 11;
		System.out.println("Paper size in centimeters: " 
				+ paperWidth * CM_PER_INCH + " by " + paperHeight * CM_PER_INCH);
		System.out.println("Using Constants2 Paper size in centimeters: " 
				+ paperWidth * Constants2.CM_PER_INCH + " by " + paperHeight * Constants2.CM_PER_INCH);
		Constants2.main(null);
	}
}
