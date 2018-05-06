package chapter03;

import java.io.*;
import java.math.*;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.util.*;
import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 3
 * @author Paul I Ighofose
 */
public class Chapter03 {
	private static String title = "Example Of";

	private static final String ARITMETIC_OPERATORS = "Arithmetic Operators";
	private static final String ARRAY_CONSTRUCTOR = "Array Constructor";
	private static final String BIG_DECIMAL = "Big Decimal";
	private static final String BIG_INTEGER = "Big Integer";
	private static final String BITWISE_OPERATORS = "Bitwise Operators";
	private static final String BREAK_LABEL = "Break Label";
	private static final String CONSTANTS = "Constants";
	private static final String CONSTANTS_2 = "Constants 2";
	private static final String CONTINUE_LABEL = "Continue Label";
	private static final String CONVERT_DOUBLE_TO_INTEGER = "Convert a Double and Cast as an Integer";
	private static final String CONVERT_INT_TO_FLOAT = "Convert Integer to Float";
	private static final String CONVERT_TO_BINARY = "Convert To a Binary Number";
	private static final String ENUMERATED_TYPES = "Enumerated Types";
	private static final String FILE_INPUT_OUTPUT = "File Input and Output";
	private static final String FORMATTING_DATE_OUTPUT = "Formatting Date Output";
	private static final String FORMATTING_NUMBER_OUTPUT = "Formatting Number Output";
	private static final String FP_TYPES = "Floating Point Types";
	private static final String INC_DEC_OPERATORS = "Increment and Decrement Operators";
	private static final String MATHEMATICAL_FUNCTIONS_AND_CONSTANTS = "Mathematical Functions and Constants";
	private static final String MULTIPLE_SELECTIONS_SWITCH = "Multiple Selections Switch";
	private static final String READ_INPUT = "Read Input";
	private static final String REL_BOOL_OPERATORS = "Relational and Boolean Operators";
	private static final String SPECIAL_CHARACTERS = "Special Characters";
	private static final String STRING_BUILDER_EXAMPLES = "String Builder Examples";
	private static final String STRINGS = "Strings";
	private static final String VARIABLE_TYPES = "Variable Types";
	private static final String FIRST_SAMPLE = "Listing 3.1 First Sample";
	private static final String INPUT_TEST = "Listing 3.2 Input Test";
	private static final String RETIREMENT = "Listing 3.3 Retirement";
	private static final String RETIREMENT_2 = "Listing 3.4 Retirement 2";
	private static final String LOTTERY_ODDS = "Listing 3.5 Lottery Odds";
	private static final String BIG_INTEGER_TEST = "Listing 3.6 Big Integer Test";
	private static final String LOTTERY_DRAWING = "Listing 3.7 Lottery Drawing";
	private static final String COMPOUND_INTEREST = "Listing 3.8 Compound Interest";
	private static final String LOTTERY_ARRAY = "Listing 3.9 Lottery Array";
	private static final String[] example = {ARITMETIC_OPERATORS, ARRAY_CONSTRUCTOR, BIG_DECIMAL,  
			BIG_INTEGER, BITWISE_OPERATORS, BREAK_LABEL, CONSTANTS, CONSTANTS_2,    
			CONTINUE_LABEL, CONVERT_DOUBLE_TO_INTEGER, CONVERT_INT_TO_FLOAT,     
			CONVERT_TO_BINARY, ENUMERATED_TYPES, FILE_INPUT_OUTPUT, FP_TYPES,      
			FORMATTING_DATE_OUTPUT,FORMATTING_NUMBER_OUTPUT, INC_DEC_OPERATORS,     
			MATHEMATICAL_FUNCTIONS_AND_CONSTANTS, MULTIPLE_SELECTIONS_SWITCH,    
			READ_INPUT, REL_BOOL_OPERATORS, SPECIAL_CHARACTERS, 
			STRING_BUILDER_EXAMPLES, STRINGS, VARIABLE_TYPES, FIRST_SAMPLE, INPUT_TEST, 
			RETIREMENT, RETIREMENT_2, LOTTERY_ODDS, BIG_INTEGER_TEST, LOTTERY_DRAWING, 
			COMPOUND_INTEREST, LOTTERY_ARRAY}; 
	private static int runAgain;
	enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod("chapter03.Chapter03", args[0]);
			return;
		}
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!", title,JOptionPane.QUESTION_MESSAGE,null,example,ARITMETIC_OPERATORS);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == ARITMETIC_OPERATORS) {
				ArithmeticOperators();
			}
			else if (exampleType == ARRAY_CONSTRUCTOR) {
				ArrayConstructor();
			}
			else if (exampleType == BIG_DECIMAL) {
				BigNumbersDecimals();
			}
			else if (exampleType == BIG_INTEGER) {
				BigNumbersIntegers();
			}
			else if (exampleType == BIG_INTEGER_TEST) {
				/**
				 * BigIntegerTest class Listing 3.6
				 */
				BigIntegerTest.main(null);
			}
			else if (exampleType == BITWISE_OPERATORS) {
				BitWiseOperators();
			}
			else if (exampleType == BREAK_LABEL) {
				BreakLabel();
			}
			else if (exampleType == COMPOUND_INTEREST) {
				/**
				 * CompoundInterest class Listing 3.8 
				 */
				CompoundInterest.main(null);
				return;
			}
			else if (exampleType == CONSTANTS) {
				Constants.main(null);
			}
			else if (exampleType == CONSTANTS_2) {
				Constants2.main(null);
			}
			else if (exampleType == CONTINUE_LABEL) {
				ContinueLabel();
			}
			else if (exampleType == CONVERT_DOUBLE_TO_INTEGER) {
				convertDoubleToInteger();
			}
			else if (exampleType == CONVERT_INT_TO_FLOAT) {
				convertIntToFloat();
			}
			else if (exampleType == CONVERT_TO_BINARY) {
				convertToBinaryNumber();
			}
			else if (exampleType == ENUMERATED_TYPES) {
				EnumeratedTypes();
			}
			else if (exampleType == FILE_INPUT_OUTPUT) {
				FileInputAndOutput();
			}
			else if (exampleType == FIRST_SAMPLE) {
				/**
				 * FirstSample class Listing 3.1
				 */
				FirstSample.main(null);
				return;
			}
			else if (exampleType == FORMATTING_DATE_OUTPUT) {
				FormattingDateOutput();
			}
			else if (exampleType == FORMATTING_NUMBER_OUTPUT) {
				FormattingNumberOutput();
			}
			else if (exampleType == FP_TYPES) {
				FloatingPointTypes();
			}
			else if (exampleType == INC_DEC_OPERATORS) {
				IncrementAndDecrementOperators();
			}
			else if (exampleType == INPUT_TEST) {
				/**
				 * InputTest class Listing 3.2
				 */
				InputTest.main(null);
				return;
			}
			else if (exampleType == LOTTERY_ARRAY) {
				/**
				 * LotteryArray class Listing 3.9
				 */
				LotteryArray.main(null);
				return;
			}
			else if (exampleType == LOTTERY_DRAWING) {
				/**
				 * LotteryDrawing class Listing 3.7
				 */
				LotteryDrawing.main(null);
				return;
			}
			else if (exampleType == LOTTERY_ODDS) {
				/**
				 * LotteryOdds class Listing 3.5
				 */
				LotteryOdds.main(null);
				return;
			}
			else if (exampleType == MATHEMATICAL_FUNCTIONS_AND_CONSTANTS) {
				MathematicalFunctionsAndConstants();
			}
			else if (exampleType == MULTIPLE_SELECTIONS_SWITCH) {
				MultipleSelectionsSwitch();
			}
			else if (exampleType == READ_INPUT) {
				ReadingInput();
			}
			else if (exampleType == REL_BOOL_OPERATORS) {
				RelationalAndBooleanOperators();
			}
			else if (exampleType == RETIREMENT) {
				/**
				 * Retirement class Listing 3.3
				 */
				Retirement.main(null);
				return;
			}
			else if (exampleType == RETIREMENT_2) {
				/**
				 * Retirement2 class Listing 3.4
				 */
				Retirement2.main(null);
				return;
			}
			else if (exampleType == SPECIAL_CHARACTERS) {
				SpecialCharacters();
			}
			else if (exampleType == STRING_BUILDER_EXAMPLES) {
				StringBuilderExamples();
			}
			else if (exampleType == STRINGS) {
				Strings();
			}
			else if (exampleType == VARIABLE_TYPES) {
				VariableTypes();
			}
			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?","Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}
	public static void ArithmeticOperators() {
		System.out.println("15 / 2: " + (15 / 2));
		System.out.println("15 % 2: " + (15 % 2));
		System.out.println("15.0 / 2: " + (15.0 / 2));
	}
	public static void ArrayConstructor() {
		int[] a = new int[10];
		System.out.println("int[] a = new int[10]; Arrays.toString(a) = " + Arrays.toString(a));
		int b[] = new int[5];
		System.out.println("int b[] = new int[5]; Arrays.toString(b) = " + Arrays.toString(b));
		String[] c = new String[5];
		System.out.println("String[] c = new String[5]; Arrays.toString(c) = " + Arrays.toString(c));
		String d[] = new String[2];
		System.out.println("String d[] = new String[2]; Arrays.toString(d) = " + Arrays.toString(d));
		for (int i = 0; i < 10; ++i) {
			a[i] = i;
		}
		System.out.println("for (int i = 0; i < 10; ++i) a[i] = i; Arrays.toString(a) = " + Arrays.toString(a));
		int[] smallPrimes = { 2, 3, 5, 17, 11, 13};
		System.out.println("int[] smallPrimes = { 2, 3, 5, 17, 11, 13}; Arrays.toString(smallPrimes) = " + Arrays.toString(smallPrimes));
		/**
		 * reinitialise an array using the following syntax
		 */
		smallPrimes = new int[] { 17, 19, 23, 29, 31, 37 };
		System.out.println("smallPrimes = new int[] { 17, 19, 23, 29, 31, 37 }; Arrays.toString(smallPrimes) = " + Arrays.toString(smallPrimes));
		System.out.println("for (int prime : smallPrimes) {System.out.print(prime + \", \");}");
		for (int prime : smallPrimes) {
			System.out.print(prime + ", ");
		}
		System.out.println("");
		int[] luckyNumbers = smallPrimes;
		System.out.println("int[] luckyNumbers = smallPrimes; Arrays.toString(luckyNumbers) = " + Arrays.toString(luckyNumbers));
		luckyNumbers[5] = 12;
		System.out.println("luckyNumbers[5] = 12; then smallPrimes[5] = " + smallPrimes[5]);
		int[] copiedLuckyNumbers = Arrays.copyOf(luckyNumbers, luckyNumbers.length);
		System.out.println("int[] copiedLuckyNumbers = Arrays.copyOf(luckyNumbers, luckyNumbers.length); "
				+ "Arrays.toString(copiedLuckyNumbers) = " + Arrays.toString(copiedLuckyNumbers));
		Arrays.sort(copiedLuckyNumbers);
		System.out.println("Arrays.sort(copiedLuckyNumbers);" + "Arrays.toString(copiedLuckyNumbers) = " + 
				Arrays.toString(copiedLuckyNumbers));
		copiedLuckyNumbers[5] = 52;
		System.out.println("copiedLuckyNumbers[5] = 52 then luckyNumbers[5] = " + luckyNumbers[5]);
		luckyNumbers = Arrays.copyOf(luckyNumbers, 2 * luckyNumbers.length);
		System.out.println("luckyNumbers = Arrays.copyOf(luckyNumbers, 2 * luckyNumbers.length); "
				+ "Arrays.toString(luckyNumbers) = " + Arrays.toString(luckyNumbers));
		luckyNumbers = Arrays.copyOfRange(luckyNumbers, 1, 4);
		System.out.println("luckyNumbers = Arrays.copyOfRange(luckyNumbers, 1, 4); "
				+ "Arrays.toString(luckyNumbers) = " + Arrays.toString(luckyNumbers));
		System.out.println("Arrays.binarySearch(luckyNumbers, 23); "
				+ Arrays.binarySearch(luckyNumbers, 23));
		Arrays.fill(luckyNumbers, 0);
		System.out.println("Arrays.fill(luckyNumbers, 0);" + "Arrays.toString(luckyNumbers) = " + 
				Arrays.toString(luckyNumbers));
		System.out.println("Arrays.equals(luckyNumbers, copiedLuckyNumbers); = " + 
				Arrays.equals(luckyNumbers, copiedLuckyNumbers));
		int[][] magicSquare = {{16, 3 , 2, 13},{5, 10, 11, 8},{9, 6, 7, 12},{4, 15, 14, 1}};
		System.out.println("int[][] magicSquare = {{16, 3 , 2, 13},{5, 10, 11, 8},{9, 6, 7, 12},{4, 15, 14, 1}};");
		System.out.println("System.out.println(Arrays.deepToString(magicSquare));");
		System.out.println(Arrays.deepToString(magicSquare));
		System.out.println("magicSquare[0][0] + \", \" + magicSquare[0][1] + \", \" + magicSquare[0][2] + \", \" + magicSquare[0][3] = " + 
				magicSquare[0][0] + ", " + magicSquare[0][1] + ", " + magicSquare[0][2] + ", " + magicSquare[0][3]);
	}
	/**
	 * The BigDecimal class implements arbitrary-precision arithmetic on numbers with a long sequence of 
	 * digits. The divide function must be supplied a rounding mode
	 */
	public static void BigNumbersDecimals() {
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("What is the value of a? ");
			double val = in.nextDouble(); 
			BigDecimal a = BigDecimal.valueOf(val);
			System.out.println("BigDecimal a = BigDecimal.valueOf(" + val + ");");
			System.out.println("a = " + String.format("%,.2f", a));
			System.out.println("");
			System.out.print("What is the value of b? ");
			val = in.nextDouble();  
			BigDecimal b = BigDecimal.valueOf(val);
			System.out.println("BigDecimal b = BigDecimal.valueOf(" + val + ");");
			System.out.println("b = " + String.format("%,.2f", b));
			System.out.println("");
			BigDecimal c = a.add(b);
			System.out.println("BigDecimal c = a.add(b);");
			System.out.println("c = " + String.format("%,.2f", c));
			System.out.println("");
			System.out.print("What is the value of d? ");
			double d = in.nextDouble();  
			BigDecimal e = c.multiply(b.add(BigDecimal.valueOf(d)));
			System.out.println("BigDecimal e = c.multiply(b.add(BigDecimal.valueOf(" + d + ")));");
			System.out.println("e = " + String.format("%,.2f", e));
			System.out.println("e.divide(BigDecimal.valueOf(1000.0, RoundingMode.HALF_UP) = " + String.format("%,.2f", e.divide(BigDecimal.valueOf(1000),RoundingMode.HALF_UP)));
			Threads.closeStream(in);
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"BigNumbersDecimals\"", "Chapter03", title);			
		}
	}
	/**
	 * The BigInteger class implements arbitrary-precision arithmetic on numbers with a long sequence of digits
	 */
	public static void BigNumbersIntegers() {
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("What is the value of a? ");
			int val = in.nextInt(); 
			BigInteger a = BigInteger.valueOf(val);
			System.out.println("BigInteger a = BigInteger.valueOf(" + val + ");");
			System.out.println("a = " + String.format("%,d", a));
			System.out.println("");
			System.out.print("What is the value of b? ");
			val = in.nextInt();  
			BigInteger b = BigInteger.valueOf(val);
			System.out.println("BigInteger b = BigInteger.valueOf(" + val + ");");
			System.out.println("b = " + String.format("%,d", b));
			System.out.println("");
			BigInteger c = a.add(b);
			System.out.println("BigInteger c = a.add(b);");
			System.out.println("c = " + String.format("%,d", c));
			System.out.println("");
			System.out.print("What is the value of d? ");
			int d = in.nextInt();  
			BigInteger e = c.multiply(b.add(BigInteger.valueOf(d)));
			System.out.println("BigInteger e = c.multiply(b.add(BigInteger.valueOf(" + d + ")));");
			System.out.println("e = " + String.format("%,d", e));
			System.out.println("e.divide(BigInteger.valueOf(1000) = " + String.format("%,d", e.divide(BigInteger.valueOf(1000))));
			Threads.closeStream(in);
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"BigNumbersIntegers\"", "Chapter03", title);			
		}
	}
	public static void BitWiseOperators() {
		String strNumber = JOptionPane.showInputDialog("Please give a value!", 8);
		long n = Long.parseLong(strNumber);
		String strBinNumber = convertToLongBinaryNumber(n);
		System.out.println("Long Binary Number: " + strBinNumber);
		strBinNumber = convertToShortBinaryNumber(n);
		System.out.println("Short Binary Number: " + strBinNumber);
		System.out.println("Binary 0b1000: " + String.format("%,(.0f", 0b1000 + 0.0));
		System.out.println("if n = " + n + " then n & 0b1000 is: " + String.format("%,(.0f", (n & 0b1000) + 0.0));
		System.out.println("if n = " + n + " then the fourth bit from the right of (n & 0b1000) / 0b1000 is: " + ((n & 0b1000) / 0b1000));
		System.out.println("if n = " + n + " then (n & (1 << 3)) >> 3: " + ((n & (1 << 3)) >> 3));
		strNumber = JOptionPane.showInputDialog("Please give a value!", 4);
		n = Integer.parseInt(strNumber);
		strBinNumber = convertToLongBinaryNumber(n);
		System.out.println("Long Binary Number: " + strBinNumber);
		strBinNumber = convertToShortBinaryNumber(n);
		System.out.println("Short Binary Number: " + strBinNumber);
		System.out.println("Binary 0b0100: " + String.format("%,(.0f", 0b0100 + 0.0));
		System.out.println("if n = " + n + " then n & 0b0100 is: " + String.format("%,(.0f", (n & 0b0100) + 0.0));
		System.out.println("if n = " + n + " then the third bit from the right of (n & 0b0100) / 0b0100; is: " + ((n & 0b0100) / 0b0100));
		System.out.println("if n = " + n + " then (n & (1 << 2)) >> 2: " + ((n & (1 << 2)) >> 2));
		System.out.println("Binary 0b1001: " + String.format("%,(.0f", 0b1001 +0.0));
		System.out.println("Binary 0b1111_0100_0010_0100_0000: " + String.format("%,(.0f", 0b1111_0100_0010_0100_0000 + 0.0));
	}
	/** 
	 * Breaks out of a block or loop as long as the block is labeled 
	 */
	public static void BreakLabel() {
		Scanner in = new Scanner(System.in);
		try {
			int n = 5;
			read_data:
				while(n < 7) {
					for (int i = 0; i < 10; i++) {
						System.out.print("Enter a number >= 0: ");
						n = in.nextInt();
						if (n < 0) { // should never happen
							break read_data;
							// break out of read_data loop
						}
					}
				}
			// this statement is executed immediately after the labelled break
			if (n < 0) { // check for bad situation
				// deal with bad situation
				System.out.println("n was less than 0");
			}
			else {
				// carry out normal processing
				System.out.println("n: " + n);
			}
			block_label: {
				if (n == -5) {
					break block_label;
				}
				System.out.println("n: " + n);
			}
			Threads.closeStream(in);
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"BreakLabel\"", "Chapter03", title);			
		}
}
	/** 
	 * Continues out of a loop as long as the loop is labelled 
	 * unlike Break it cannot continue out of a block if the block is not a loop
	 */
	public static void ContinueLabel() {
		Scanner in = new Scanner(System.in);
		try {
			int n = 5;
			read_data:
				while(n < 7 && n >= 0) {
					for (int i = 0; i < 10; i++) {
						System.out.print("Enter a number >= 0: ");
						n = in.nextInt();
						if (n < -5) { // should never happen
							continue;// does not do anything;
							// continue out of read_data loop
						}
						else if (n < 0) { // should never happen
							continue read_data;
							// continue out of read_data loop
						}
						else {
							// carry out normal processing
							System.out.println("For n: " + n);
						}
					}
				}
			// this statement is executed immediately after the labelled break
			if (n < 0) { // check for bad situation
				// deal with bad situation
				System.out.println("n was less than 0");
			}
			else {
				// carry out normal processing
				System.out.println("n: " + n);
			}
			Threads.closeStream(in);
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"ContinueLabel\"", "Chapter03", title);			
		}
	}
	/**
	 * convert a Double and Cast it as an Integer
	 */
	public static void convertDoubleToInteger() {
		int intConfirm;
		StringBuilder message = new StringBuilder();
		do {
			intConfirm = JOptionPane.NO_OPTION;
			try {
				message.append("The maximum Integer possible is: " + String.format("%,(.0f", (Double) (2147483647 + 0.0)));
				message.append("\nWhat is the value for x: ");
				System.out.println(message);
				// JOptionPane.showInputDialog(message)
				double x = Double.parseDouble(JOptionPane.showInputDialog(message));
				int nx = (int) x;
				System.out.println("double x = " + String.format("%,(.5f",x));
				System.out.println("int nx = (int) x;");
				System.out.println("nx = " + nx);
				System.out.println("Or");
				/**
				 * You still need to cast (int) when you call round
				 * because the return value of round is a long numeric type
				 */
				nx = (int) Math.round(x);
				System.out.println("double x = " + String.format("%,(.5f",x));
				System.out.println("int nx = (int) Math.round(x);");
				System.out.println("nx = " + nx);
			}
			catch(Exception e) {
				intConfirm = JOptionPane.showConfirmDialog(null, "The value you entered is not an Integer. "
						+ "Do you want to try again?", "Value Type Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		while (intConfirm == JOptionPane.YES_OPTION);
	}
	/**
	 * converts an Integer to a Float numeric type
	 */
	public static void convertIntToFloat() {
		int intConfirm;
		do {
			intConfirm = JOptionPane.NO_OPTION;
			try {
				// JOptionPane.showInputDialog(message)
				int n = Integer.parseInt(JOptionPane.showInputDialog("What is the value for n: "));
				float f = n;
				System.out.println("int n = " + n);
				System.out.println("float f = n;");
				System.out.println("f = " + f);
			}
			catch(Exception e) {
				intConfirm = JOptionPane.showConfirmDialog(null, "The value you entered is not an Integer. "
						+ "Do you want to try again?", "Value Type Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		while (intConfirm == JOptionPane.YES_OPTION);
	}
	/**
	 * Converts integer to a binary string
	 * n integer number to convert
	 * Binary number as a string representation
	 */
	public static void convertToBinaryNumber() {
		StringBuilder message = new StringBuilder();
		message.append("The Maximum Numbers Possible are: ");
		message.append("\n0b1111 = " + 0b1111);		
		message.append("\n0b1111_1111 = " + String.format("%,(.0f", 0b1111_1111 + 0.0));		
		message.append("\n0b1111_1111_1111 = " + String.format("%,(.0f", 0b1111_1111_1111 + 0.0));		
		message.append("\n0b1111_1111_1111_1111 = " + String.format("%,(.0f", 0b1111_1111_1111_1111 + 0.0));		
		message.append("\n0b1111_1111_1111_1111_1111 = " + String.format("%,(.0f", 0b1111_1111_1111_1111_1111 + 0.0));		
		message.append("\n0b1111_1111_1111_1111_1111_1111 = " + String.format("%,(.0f", 0b1111_1111_1111_1111_1111_1111 + 0.0));		
		message.append("\n0b1111_1111_1111_1111_1111_1111_1111 = " + String.format("%,(.0f", 0b1111_1111_1111_1111_1111_1111_1111 + 0.0));		
		message.append("\n0b0111_1111_1111_1111_1111_1111_1111_1111 = " + String.format("%,(.0f", 0b0111_1111_1111_1111_1111_1111_1111_1111 + 0.0));		
		message.append("\nPlease give a value!");
		String strNumber = JOptionPane.showInputDialog(message, 8);
		long n = Long.parseLong(strNumber);
		String strBinNumber = convertToLongBinaryNumber(n);
		System.out.println("Long Binary Number for " + n + ": " + strBinNumber);
		strBinNumber = convertToShortBinaryNumber(n);
		System.out.println("Short Binary Number " + n + ": " + strBinNumber);
	}
	/**
	 * Converts integer to a long binary string
	 * @param n integer number to convert
	 * @return Binary number as a string representation
	 */
	public static String convertToLongBinaryNumber(long n) {
		String strBinNumber = "0b" 
				+ ((n & (1 << 31)) >> 31) + ((n & (1 << 30)) >> 30) + ((n & (1 << 29)) >> 29) + ((n & (1 << 28)) >> 28)
				+ "_" + ((n & (1 << 27)) >> 27) + ((n & (1 << 26)) >> 26) + ((n & (1 << 25)) >> 25) + ((n & (1 << 24)) >> 24)
				+ "_" + ((n & (1 << 23)) >> 23) + ((n & (1 << 22)) >> 22) + ((n & (1 << 21)) >> 21) + ((n & (1 << 20)) >> 20)
				+ "_" + ((n & (1 << 19)) >> 19) + ((n & (1 << 18)) >> 18) + ((n & (1 << 17)) >> 17) + ((n & (1 << 16)) >> 16)
				+ "_" + ((n & (1 << 15)) >> 15) + ((n & (1 << 14)) >> 14) + ((n & (1 << 13)) >> 13) + ((n & (1 << 12)) >> 12)
				+ "_" + ((n & (1 << 11)) >> 11) + ((n & (1 << 10)) >> 10) + ((n & (1 << 9)) >> 9) + ((n & (1 << 8)) >> 8)
				+ "_" + ((n & (1 << 7)) >> 7) + ((n & (1 << 6)) >> 6) + ((n & (1 << 5)) >> 5) + ((n & (1 << 4)) >> 4)
				+ "_" + ((n & (1 << 3)) >> 3) + ((n & (1 << 2)) >> 2) + ((n & (1 << 1)) >> 1) + ((n & (1 << 0)) >> 0);
		return strBinNumber;
	}
	/**
	 * Converts integer to a short binary string
	 * @param n integer number to convert
	 * @return Binary number as a string representation
	 */
	public static String convertToShortBinaryNumber(long n) {
		String strBinNumber = convertToLongBinaryNumber(n);		
		if (n <= 0b1111) {
			strBinNumber = "0b" + strBinNumber.substring(37);
		}
		else if (n <= 0b1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(32);
		}
		else if (n <= 0b1111_1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(27);
		}
		else if (n <= 0b1111_1111_1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(22);
		}
		else if (n <= 0b1111_1111_1111_1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(17);
		}
		else if (n <= 0b1111_1111_1111_1111_1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(12);
		}
		else if (n <= 0b1111_1111_1111_1111_1111_1111_1111) {
			strBinNumber = "0b" + strBinNumber.substring(7);
		}
		else if (n <= 0b0111_1111_1111_1111_1111_1111_1111_1111) {
			//strBinNumber = strBinNumber;
		}
		else {
			strBinNumber = "The number you gave is too large to be hanled by a long numeric type!";
		}
		return strBinNumber;
	}
	public static void EnumeratedTypes() {
		Size s = Size.MEDIUM;
		System.out.println("Size s = Size.MEDIUM: " + s);
	}
	public static void FileInputAndOutput() {
		/**
		 * creates a Scanner object to read from a file
		 * If the file name contains backslashes escape them with an additional backslash
		 * Do not use C:\ as it is system specific use a File.separator instead of \
		 */
		try {
			String dir = File.separator + System.getProperty("user.dir").replace("C:\\","").replace("\\", File.separator);
			System.out.println("The user directory is: " + dir);
			String fileLocation = File.separator + "Users" +
                File.separator + "Paul" + File.separator + "Documents" +
                File.separator + "NetBeansProjects" + File.separator +
                "Vehicles" + File.separator + "src" + File.separator + 
                "inventory.txt";
			try {
				Scanner in = new Scanner(Paths.get(fileLocation));
				PrintWriter out = new PrintWriter(fileLocation.replace(".txt", "1.txt"));
				String strLine;
				do {
					strLine = in.nextLine();
					System.out.println(strLine);
					out.print(strLine);
					out.append("\r");
					out.append(strLine);
				}
				while(in.hasNextLine() == true);
				Threads.closeStream(in);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (AccessControlException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void FloatingPointTypes() {
		final int x = 0;
		System.out.println("final int x = 0; x == Double.NaN: " + (x == Double.NaN));  // is never true
		System.out.println("final int x = 0; Double.isNaN(x): " + Double.isNaN(x)); // check whether x is not a number 
		System.out.println("2.0 - 1.1: " + (2.0 - 1.1));
	}
	public static void FormattingDateOutput() {
		Date dateNow = new Date();
		System.out.println("Date dateNow = new Date();");
		System.out.println("System.out.printf(\"%tc\", dateNow);");
		System.out.printf("%tc", dateNow);
		System.out.println("");
		System.out.println("%tc Complete date and time: ");
		System.out.printf("%tc", dateNow);
		System.out.println("");
		System.out.println("%tF ISO 8601 date: ");
		System.out.printf("%tF", dateNow);
		System.out.println("");
		System.out.println("%tD US formatted date (month/day/year): ");
		System.out.printf("%tD", dateNow);
		System.out.println("");
		System.out.println("%tT 24-hour time: ");
		System.out.printf("%tT", dateNow);
		System.out.println("");
		System.out.println("%tr 12-hour time: ");
		System.out.printf("%tr",dateNow);
		System.out.println("");
		System.out.println("%tR 24-hour time, no seconds: ");
		System.out.printf("%tR", dateNow);
		System.out.println("");
		System.out.println("%tY Four-digit year (with leading zeroes): ");
		System.out.printf("%tY", dateNow);
		System.out.println("");
		System.out.println("%ty Last two digits of the year (with leading zeroes): ");
		System.out.printf("%ty", dateNow);
		System.out.println("");
		System.out.println("%tC First two digits of the year (with leading zeroes): ");
		System.out.printf("%tC", dateNow);
		System.out.println("");
		System.out.println("%tB Full month name: ");
		System.out.printf("%tB", dateNow);
		System.out.println("");
		System.out.println("%tb or %th Abbreviated month name: ");
		System.out.printf("%tb", dateNow);
		System.out.println("");
		System.out.printf("%th", dateNow);
		System.out.println("");
		System.out.println("%tm Two-digit month (with leading zeroes): ");
		System.out.printf("%tm", dateNow);
		System.out.println("");
		System.out.println("%td Two-digit day (with leading zeroes): ");
		System.out.printf("%td", dateNow);
		System.out.println("");
		System.out.println("%te Two-digit day (without leading zeroes): ");
		System.out.printf("%te", dateNow);
		System.out.println("");
		System.out.println("%tA Full weekday name: ");
		System.out.printf("%tA", dateNow);
		System.out.println("");
		System.out.println("%ta Abbreviated weekday name: ");
		System.out.printf("%ta", dateNow);
		System.out.println("");
		System.out.println("%tj Three-digit day of year (with leading zeroes), between 001 and 366: ");
		System.out.printf("%tj", dateNow);
		System.out.println("");
		System.out.println("%tH Two-digit hour (with leading zeroes), between 00 and 23: ");
		System.out.printf("%tH", dateNow);
		System.out.println("");
		System.out.println("%tk Two-digit hour (without leading zeroes), between 00 and 23: ");
		System.out.printf("%tk", dateNow);
		System.out.println("");
		System.out.println("%tI Two-digit hour (with leading zeroes), between 01 and 12: ");
		System.out.printf("%tI", dateNow);
		System.out.println("");
		System.out.println("%tl Two-digit hour (without leading zeroes), between 01 and 12: ");
		System.out.printf("%tl", dateNow);
		System.out.println("");
		System.out.println("%tM Tow-digit minutes (with leading zeroes): ");
		System.out.printf("%tM", dateNow);
		System.out.println("");
		System.out.println("%tS Two-digit seconds (with leading zeroes): ");
		System.out.printf("%tS", dateNow);
		System.out.println("");
		System.out.println("%tL Three-digit milliseconds (with leading zeroes): ");
		System.out.printf("%tL", dateNow);
		System.out.println("");
		System.out.println("%tN Nine-digit nanoseconds (with leading zeroes): ");
		System.out.printf("%tN", dateNow);
		System.out.println("");
		System.out.println("%tP Uppercase morning or afternoon marker: Does not work");
		//System.out.printf("%tP", dateNow);
		//System.out.println("");
		System.out.println("%tp Lowercase morning or afternoon marker: ");
		System.out.printf("%tp", dateNow);
		System.out.println("");
		System.out.println("%tz RFC 822 numeric offset from GMT: ");
		System.out.printf("%tz", dateNow);
		System.out.println("");
		System.out.println("%tZ Time zone: ");
		System.out.printf("%tZ", dateNow);
		System.out.println("");
		System.out.println("%ts Seconds since 1970-01-01 00:00:00 GMT: ");
		System.out.printf("%ts", dateNow);
		System.out.println("");
		System.out.println("$tQ Milliseconds since 1970-01-01 00:00:00 GMT: ");
		System.out.printf("%tQ", dateNow);
		System.out.println("");
		System.out.println("System.out.printf(\"%1$s %2$tB %2$te, %2$tY\", \"Due date: \", dateNow);");
		System.out.printf("%1$s %2$tB %2$te, %2$tY", "Due date: ", dateNow);
		System.out.println("");
		System.out.println("System.out.printf(\"%s %tB %<te, %<tY\", \"Due date: \", dateNow);");
		System.out.printf("%s %tB %<te, %<tY", "Due date: ", dateNow);
		System.out.println("");
	}
	public static void FormattingNumberOutput() {
		double x = 10000.0 / 3;
		System.out.println("");
		System.out.println("double x = 10000.0 / 3: " + x);
		System.out.print("System.out.printf(\"%8.2f\", x): ");
		System.out.printf("%8.2f", x);
		System.out.println("");
		Scanner readInput = new Scanner(System.in);
		try {
			System.out.println("What is your First name? ");
			/**
			 * String nextLine() reads the next line of input
			 */
			String firstName = readInput.nextLine();
			System.out.println("What is your Last Name? ");
			/**
			 * String next() reads the next word of input (delimited by whitespace)
			 */
			String lastName = readInput.next();
			System.out.println("How old are you? ");
			/**
			 * int nextInt() or double nextDouble() reads and converts the next character sequence that represents an
			 * integer or floating-point number
			 */
			int age = readInput.nextInt();
			System.out.println("System.out.printf(\"Hello, %s. Next year, you'll be %d\", firstName + \" \" + lastName, age);");
			System.out.printf("Hello, %s. Next year, you'll be %d", firstName + " " + lastName, age + 1);
			System.out.println("");
			/**
			 * Scanner object must be closed to avoid a memory leak
			 */
			Threads.closeStream(readInput);
			System.out.println("%d Decimal integer: ");
			System.out.printf("%d", age);
			System.out.println("");
			System.out.println("%x Hexadecimal integer: ");
			System.out.printf("%x", age);
			System.out.println("");
			System.out.println("%o Octal integer: ");
			System.out.printf("%o", age);
			System.out.println("");
			System.out.println("%f Fixed-point floating-point: ");
			System.out.printf("%f", x);
			System.out.println("");
			System.out.println("%e Exponential floating-point: ");
			System.out.printf("%e", x);
			System.out.println("");
			System.out.println("%g General floating-point: ");
			System.out.printf("%g", x);
			System.out.println("");
			System.out.println("%a Hexadecimal floating-point: ");
			System.out.printf("%a", x);
			System.out.println("");
			System.out.println("%s String: ");
			System.out.printf("%s", firstName);
			System.out.println("");
			System.out.println("%c Character: ");
			System.out.printf("%c", firstName.charAt(0));
			System.out.println("");
			System.out.println("%b boolean: ");
			System.out.printf("%b", age == 51);
			System.out.println("");
			System.out.println("%h Hash code: ");
			System.out.printf("%h", x);
			System.out.println("");
			System.out.println("%% The percent symbol: ");
			System.out.printf("%%.2f", x);
			System.out.println("");
			System.out.println("%n The platform-independant line separator: ");
			System.out.printf("%n.2f", x);
			System.out.println("");
			System.out.println("System.out.printf(\"%,.2f\", x);");
			System.out.printf("%,.2f", x);
			System.out.println("");
			System.out.println("%+.2f Prints sign for positive and negative numbers: ");
			System.out.printf("%+.2f", x);
			System.out.println("");
			System.out.printf("%+.2f", -x);
			System.out.println("");
			System.out.println("% .2f Adds a space before positive numbers: ");
			System.out.printf("% .2f", x);
			System.out.println("");
			System.out.println("%0.2f Adds leading zeroes: Does not work");
			//System.out.printf("%00000.2f", x);
			//System.out.println("");
			System.out.println("%-.2f Left-justifies field: Does not work");
			//System.out.printf("%-.2f", "|" + x + "|");
			//System.out.println("");
			System.out.println("%( Encloses negative numbers in parentheses: ");
			System.out.printf("%(.2f", -x);
			System.out.println("");
			System.out.println("%,.2f Adds group separators: ");
			System.out.printf("%,.2f", (x * 100));
			System.out.println("");
			System.out.println("%#(for f format) Always includes a decimal point: ");
			System.out.printf("%#f", x);
			System.out.println("");
			System.out.println("%#(for x or o format) Adds 0x or 0 prefix: ");
			System.out.printf("%#x", age);
			System.out.println("");
			System.out.printf("%#o", age);
			System.out.println("");
			System.out.println("%$ Specifies the index of the argument to be formatted (e.g %1$d Decimal, "
					+ "%1$x Hexadecimal: ");
			System.out.printf("%1$d", age);
			System.out.println("");
			System.out.printf("%1$x", age);
			System.out.println("");
			System.out.println("%< Formats the same value as the previous specification e.g %d Decimal "
					+ "%<x Hexadecimal: ");
			System.out.printf("%d", age);
			System.out.println("");
			System.out.printf("%d %<x", age);
			System.out.println("");
			System.out.println("String.format(\"Hello, %s. Next year, you'll be %d\", firstName, age + 1);");
			System.out.println(String.format("Hello, %s. Next year, you'll be %d", firstName, age + 1));
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"FormattingNumberOutput\"", "Chapter03", title);			
		}
	}
	public static void IncrementAndDecrementOperators() {
		int vacationDays = 27;
		System.out.println("vacationDays (" + vacationDays + ") += 4: " + (vacationDays += 4));
		System.out.println("2 * vacationDays++ (" + vacationDays + "): " + (2 * vacationDays++));
		System.out.println("2 * ++vacationDays (" + ++vacationDays + "): " + (2 * vacationDays));
	}
	/**
	 * The Math class contains an assortment of mathematical functions
	 * StrictMath implements algorithms from fdlibm that guarantee
	 * identical results on all platforms 
	 */
	public static void MathematicalFunctionsAndConstants() {
		int intConfirm;
		StringBuilder message = new StringBuilder();
		do {
			intConfirm = JOptionPane.NO_OPTION;
			try {
				message.append("The maximum Double possible is: +-1.79769313486231570E+308");
				message.append("\nWhat is the value for x: ");
				System.out.println(message);
				// JOptionPane.showInputDialog(message)
				double x = Double.parseDouble(JOptionPane.showInputDialog(message));
				double y = Math.sqrt(x);
				System.out.println("double x = " + String.format("%,(.2f",x));
				System.out.println("double y = Math.sqrt(x);");
				System.out.println("y = " + y);
				System.out.println("Or");
				int a = Integer.parseInt(JOptionPane.showInputDialog("What should x be raised to the power of?"));
				y = Math.pow(x, a);
				System.out.println("double x = " + String.format("%,(.2f",x));
				System.out.println("int a = " + a);
				System.out.println("double y = Math.pow(x, a);");
				System.out.println("y = " + y);
			}
			catch(Exception e) {
				intConfirm = JOptionPane.showConfirmDialog(null, "The value you entered is not an Integer. "
						+ "Do you want to try again?", "Value Type Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		while (intConfirm == JOptionPane.YES_OPTION);
	}
	public static void MultipleSelectionsSwitch() {
		Scanner in = new Scanner(System.in);
		try {
			System.out.println("Select an option (1, 2, 3, 4) ");
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				System.out.print("You chose: " + choice);
				break;
			case 2:
				System.out.print("You chose: " + choice);
				break;
			case 3:
				System.out.print("You chose: " + choice);
				break;
			case 4:
				System.out.print("You chose: " + choice);
				break;
			default:
				System.out.print("Bad input!");
				break;
					
			}
			Threads.closeStream(in);
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"MultipleSelectionsSwitch\"", "Chapter03", title);			
		}
	}
	/**
	 * boolean hasNext() tests whether there is another word in the input
	 * boolean hasNextIn() or hasNextDouble() tests whether the next character sequence
	 * represents an integer or floating-point number.
	 */
	public static void ReadingInput() {
		Scanner readInput = new Scanner(System.in);
		try {
			System.out.println("What is your First name? ");
			/**
			 * String nextLine() reads the next line of input
			 */
			String firstName = readInput.nextLine();
			System.out.println("What is your Last Name? ");
			/**
			 * String next() reads the next word of input (delimited by whitespace)
			 */
			String lastName = readInput.next();
			System.out.println("How old are you? ");
			/**
			 * int nextInt() or double nextDouble() reads and converts the next character sequence that represents an
			 * integer or floating-point number
			 */
			int age = readInput.nextInt();
			System.out.println("Hello " + firstName + " " + lastName + ";D\nYou will be " + ++age +" next year!");
			/**
			 * Scanner object must be closed to avoid a memory leak
			 */
			try {
				/**
				 * java.lang.System
				 * static Console console returns a console object and if none is available returns null
				 */
				Console cons = System.console();
				/**
				 * java.io.Console
				 * static char[] readPassword(String prompt, Object... args)
				 * static String readLine(String prompt, Object... args)
				 * displays the prompt and reads a console input
				 * The args parameters can be used to supply formatting arguments
				 */
				String userName = cons.readLine("User Name: ");
				char[] userPassword = cons.readPassword("User Password: ");
				System.out.println("Your User Name is: " + userName + " and Password is: " + new String(userPassword));
			}
			catch (Exception e) {
				Threads.runJarProcess("jar", "\"ReadingInput\"", "Chapter03", title + System.lineSeparator() +
						"There is no Virtual Machine Console Present!");			
			}
			finally {
				Threads.closeStream(readInput);
			}
		} catch (NoSuchElementException e) {
			Threads.runJarProcess("jar", "\"ReadingInput\"", "Chapter03", title);			
		}
	}
	public static void RelationalAndBooleanOperators() {
		System.out.println("3 == 7: " + (3 == 7));
		System.out.println("3 != 7: " + (3 != 7));
		/**
		 * System.out.println("1 < 2 ? 1 : 2 is: " + (1 < 2 ? 1 : 2));
		 * the above would produce a Dead Code error as 2 is never reached
		 * Java Language Specification (JLS) forbids the compiler to evaluate the value of variables
		 */
		int x = 1, y = 2;
		System.out.println("int x = 1, y = 2;");
		System.out.println("x < y ? x : y is: " + (x < y ? x : y));
	}
	/**
	 * Escape sequences for character types
	 */
	public static void SpecialCharacters( ) {
		System.out.println("\\b Backspace \\u0008: This will\binsert" + '\u0008' + "a Backspace");
		System.out.println("\\t Tab \\u0009: This will\tinsert" + '\u0009' + "a Tab");
		System.out.println("\\n Linefeed \\u000a: This will\ninsert a Linefeed");
		System.out.println("\\r Carraige return \\u000d: This will\rinsert a Carraige return");
		System.out.println("\\\" Double quote \\u0022: This \"will insert" + '\u0022' + " a Double quote");
		System.out.println("\\' Single quote \\u0027: This \'will insert a Single quote");
		System.out.println("\\\\ Backslash \\u005c: This \\will insert a Backslash");
		System.out.println("Open Square Bracket \\u005B: This will insert a " + '\u005B');
		System.out.println("Close Square Bracket \\u005D: This will insert a " + '\u005D');
	}
	/** 
	 * StringBuffer is slightly less efficient than StringBuilder but allows multiple threads
	 * to add and remove characters
	 */
	public static void StringBuilderExamples() {
		int intConfirm;
		do {
			intConfirm = JOptionPane.NO_OPTION;
			try {
				StringBuilder builder = new StringBuilder();
				System.out.println("StringBuilder builder = new StringBuilder();");
				System.out.println("builder.append(str) if str = Hello!: " + builder.append("Hello!"));
				System.out.println("builder.append(ch) if ch = builder.charAt(5):" + builder.append(builder.charAt(5)));
				System.out.println("builder.length(): " + builder.length());
				System.out.println("int cp = builder.codePointAt(2);");
				int cp = builder.codePointAt(2);
				System.out.println("builder.appendCodePoint(int cp) : " + builder.appendCodePoint(cp));
				System.out.println("int i = builder.length() - 1;");
				int i = builder.length() - 1;
				System.out.println("char c = builder.charAt(0);");
				char c = (char) builder.charAt(0);
				builder.setCharAt(i, c);
				System.out.println("builder.setCharAt(int i, char c): " + builder);
				System.out.println("String str = \"Paul\";");
				String str = "Paul";
				System.out.println("builder.insert(int offset, string str); let offset be 6");
				builder.insert(6, str);
				System.out.println("builder: " + builder);
				System.out.println("builder.insert(int offset, char c); let offset be 6");
				builder.insert(6, c);
				System.out.println("builder: " + builder);
				System.out.println("int startIndex = builder.lastIndexOf(\"!\");");
				int startIndex = builder.lastIndexOf("!");
				System.out.println("int endIndex = builder.lastIndexOf(\"H\");");
				int endIndex = builder.lastIndexOf("H");
				System.out.println("builder.delete(int startIndex, int endIndex);");
				builder.delete(startIndex, endIndex);
				System.out.println("builder: " + builder);
			}
			catch(Exception e) {
				intConfirm = JOptionPane.showConfirmDialog(null, "The value you entered is not an Integer. "
						+ "Do you want to try again?", "Value Type Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		while (intConfirm == JOptionPane.YES_OPTION);
	}
	public static void Strings () {
		int intConfirm;
		do {
			intConfirm = JOptionPane.NO_OPTION;
			try {
				String e = "";
				String greeting = "Hello";
				String s = greeting.substring(0,3);
				System.out.println("String e = \"\";");
				System.out.println("e.length(): " + e.length() + " e == null: " + (e == null));
				System.out.println("String greeting = \"Hello\"");
				System.out.println("greeting.equals(e): " + greeting.equals(e));
				System.out.println("greeting.equalsIgnoreCase(e): " + greeting.equalsIgnoreCase(e));
				System.out.println("String s = greeting.substring(0,3);");
				System.out.println("s: " + s);
				System.out.println("greeting.equals(s + \"lo\"): " + greeting.equals(s + "lo"));
				System.out.println("greeting.substring(0, 3) + \"p!\": " + greeting.substring(0, 3) + "p!");
				System.out.println("\"Hello\".eqauls(greeting): " + "Hello".equals(greeting));
				System.out.println("\"Hello\".equalsIgnoreCase(\"hello\"): " + "Hello".equalsIgnoreCase("hello"));
				System.out.println("greeting.compareTo(\"Hello\") == 0: " + (greeting.compareTo("Hello") == 0));
				System.out.println("int n = greeting.length;");
				System.out.println("n = " + greeting.length());
				System.out.println("int cpCount = greeting.codePointCount(0, greeting.length());");
				System.out.println("cpCount = " + greeting.codePointCount(0, greeting.length()));
				System.out.println("char first = greeting.charAt(0);");
				System.out.println("first = " + greeting.charAt(0));
				System.out.println("char last = greeting.charAt(4)");
				System.out.println("last = " + greeting.charAt(4));
				System.out.println("int index = greeting.offsetByCodePoints(0, 4);");
				int index = greeting.offsetByCodePoints(0, 4);
				System.out.println("index = " + greeting.offsetByCodePoints(0, 4));
				int cp = greeting.codePointAt(index);
				System.out.println("int cp = greeting.codePointAt(index);");
				System.out.println("cp = " + greeting.codePointAt(index));
				System.out.println("Character.isSupplementaryCodePoint(cp) = " + Character.isSupplementaryCodePoint(cp));
				System.out.println("Character.isSurrogate(greeting.charAt(4)) = " + Character.isSurrogate(greeting.charAt(4)));
				System.out.println("greeting.startsWith(\"Hel\"): " + greeting.startsWith("Hel"));
				System.out.println("greeting.endsWith(\"lo\"): " + greeting.endsWith("lo"));
				System.out.println("greeting.indexOf(\"lo\"): " + greeting.indexOf("lo"));
				System.out.println("greeting.indexof(String str, int fromIndex);");
				System.out.println("greeting.indexOf(cp): " + greeting.indexOf(cp));
				System.out.println("greeting.indexOf(int cp, int fromIndex)");
				System.out.println("greeting.lastIndexOf(String str): " + greeting.lastIndexOf("lo"));
				System.out.println("greeting.lastIndexOf(String str, int fromIndex)");
				System.out.println("greeting.lastIndexOf(int cp): " + greeting.lastIndexOf(cp));
				System.out.println("greeting.lastIndexOf(int cp, int fromIndex");
				/**
				 * the following will cause intermittent errors
				 */
				System.out.println("greeting == \"Hello\": " + (greeting == "Hello"));
				System.out.println("greeting.substring(0, 3) == \"Hel\": " + (greeting.substring(0, 3) == "Hel"));
			}
			catch(Exception e) {
				intConfirm = JOptionPane.showConfirmDialog(null, "The value you entered is not an Integer. "
						+ "Do you want to try again?", "Value Type Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		while (intConfirm == JOptionPane.YES_OPTION);
	}
	public static void VariableTypes() {
		double salary = 36000.0;
		int vacationDays = 27;
		long earthPopulation = 1234567890;
		boolean done = false;
		System.out.println("salary: " + salary);
		System.out.println("Vacation Days: " + vacationDays);
		System.out.println("Earth's Population: " + earthPopulation);
		System.out.println("Done: " + done);
	}
}
