package studio5;

import javax.swing.Spring;

public class Methods {

	//Method 1: sum. [complete]
	public static int sum(int x, int y) {
		return (x+y);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	//Method 2: mpy.
	public static int mpy(int x, int y) {
		return (x*y);  // FIXME
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static double avg3(int x, int y, int z) {
		return((x*y*z)/3);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static double sumArray (double[] values) {
		double sum = 0;
		for (int i = 0; i < values.length; i++){
			sum = sum + values[i];
		}
		return sum;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static double average (double [] values) {
		double total = sumArray(values);
		double result = total / values.length;
		return result;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return 
	 * @return
	 */

	public static double SquareRoot (int x) {
		double total = Math.sqrt(x);
		return total;
	}

	public static String pig (String s) {
		int letter = 0;
		char ch;
		for (int i=0; i<s.length(); i++) {
			ch = s.charAt(i);

			if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
				letter = i;
				break;
			}
		}
		String piglatin = s.substring(letter) + s.substring(0, letter) + "ay";
		System.out.println(letter);
		System.out.println(piglatin);
		if(letter > 0){
			return piglatin;
		}
		else {
			return null;
		}


	}

}