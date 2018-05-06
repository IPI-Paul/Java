package chapter04;

import java.util.*;
import javax.swing.*;
import ipi.*;
import java.text.DateFormatSymbols;

/**
 * This class combines all examples and programs from chapter 4
 * @author Paul I Ighofose
 */
public class Chapter04 {
	private static String title = "Example Of";

	private static final String DATE_CONSTRUCT = "Date Construct";
	private static final String CALENDAR_TEST = "Listing 4.1 Calendar Test";
	private static final String EMPLOYEE_TEST = "Listing 4.2 Employee Test";
	private static final String STATIC_TEST = "Listing 4.3 Static Test";
	private static final String PARAM_TEST = "Listing 4.4 Parameter Test";
	private static final String CONSTRUCTOR_TEST = "Listing 4.5 Constructor Test";
	private static final String PACKAGE_TEST = "Listing 4.6 Package Test";
	private static final String[] example = {DATE_CONSTRUCT, CALENDAR_TEST, EMPLOYEE_TEST, STATIC_TEST,  
			PARAM_TEST, CONSTRUCTOR_TEST, PACKAGE_TEST}; 
	private static int runAgain;

	public static void main(String[] args) {
		Threads.setDefaultLocale();
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,JOptionPane.QUESTION_MESSAGE,null,example,DATE_CONSTRUCT);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
		if (exampleType == CALENDAR_TEST) {
				/**
				 * CalendarTest class Listing 4.1
				 */
				CalendarTest.main(null);
				return;
			}
			else if (exampleType == CONSTRUCTOR_TEST) {
				/**
				 * ConstructorTest class Listing 4.5
				 * Employee inner class
				 */
				ConstructorTest.main(null);
				return;
			}
			else if (exampleType == DATE_CONSTRUCT) {
				DateConstruct();
			}
			else if (exampleType == EMPLOYEE_TEST) {
				/**
				 * EmployeeTest class Listing 4.2
				 * Employee inner class
				 */
				EmployeeTest.main(null);
				return;
			}
			else if (exampleType == PACKAGE_TEST) {
				/**
				 * PackageTest class Listing 4.6
				 * chapter04.com.horstmann.corejava.Employee class Listing 4.7
				 */
				PackageTest.main(null);
				return;
			}
			else if (exampleType == PARAM_TEST) {
				/**
				 * ParamTest class Listing 4.4
				 */
				ParamTest.main(null);
				return;
			}
			else if (exampleType == STATIC_TEST) {
				/**
				 * StaticTest class Listing 4.3
				 */
				StaticTest.main(null);
				return;
			}
			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}
	public static void DateConstruct() {
		GregorianCalendar now = new GregorianCalendar();
		System.out.println("GregorianCalendar now = new GregorianCalendar();");
		System.out.println("(Date) now.getTime() = " + (Date) now.getTime());
		int month = now.get(Calendar.MONTH);
		System.out.println("int month = now.get(Calendar.MONTH); month: " + month + " (Date) now.getTime(): " 
				+ (Date) now.getTime());
		int weekday = now.get(Calendar.DAY_OF_WEEK);
		System.out.println("int weekday = now.get(Calendar.DAY_OF_WEEK); weekday = " + weekday);
		GregorianCalendar deadline = new GregorianCalendar(1999, Calendar.DECEMBER, 25);
		System.out.println("GregorianCalendar deadline = new GregorianCalendar(1999, Calendar.DECEMBER, 25);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.set(Calendar.YEAR, 2001);
		System.out.println("deadline.set(Calendar.YEAR, 2001);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.set(Calendar.MONTH, Calendar.APRIL);
		System.out.println("deadline.set(Calendar.MONTH, Calendar.APRIL);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.set(Calendar.DAY_OF_MONTH, 15);
		System.out.println("deadline.set(Calendar.DAY_OF_MONTH, 15);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.set(1966,Calendar.NOVEMBER,8,7,45,0);
		System.out.println("deadline.set(1966,Calendar.NOVEMBER,8,7,45,0);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.add(Calendar.YEAR, 51);
		System.out.println("deadline.add(Calendar.YEAR, 51);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.add(Calendar.MONTH, 3);
		System.out.println("deadline.add(Calendar.MONTH, 3);");		
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		deadline.add(Calendar.MONTH, -1);
		System.out.println("deadline.add(Calendar.MONTH, -1);");
		System.out.println("(Date) deadline.getTime() = " + (Date) deadline.getTime());
		String[] weekdayNames = new DateFormatSymbols().getWeekdays();
		System.out.println("String[] weekdayNames = new DateFormatSymbols().getWeekdays();");
		System.out.println(Arrays.toString(weekdayNames));
		String[] shortDayNames = new DateFormatSymbols().getShortWeekdays();
		System.out.println("String[] shortDayNames = new DateFormatSymbols().getShortWeekdays();");
		System.out.println(Arrays.toString(shortDayNames));
		String[] monthNames = new DateFormatSymbols().getMonths();
		System.out.println("String[] monthNames = new DateFormatSymbols().getMonths();");
		System.out.println(Arrays.toString(monthNames));
		String[] shortMonthNames = new DateFormatSymbols().getShortMonths();
		System.out.println("String[] shortMonthNames = new DateFormatSymbols().getShortMonths();");
		System.out.println(Arrays.toString(shortMonthNames));
	}
}
