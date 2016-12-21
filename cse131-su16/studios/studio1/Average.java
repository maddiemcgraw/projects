package studio1;

import cse131.ArgsProcessor;

public class Average {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		
		int N = 2;
		double n1 = ap.nextInt("The first of two integers to be averaged?");
		double n2 = ap.nextInt("The second of two integers to be averaged?");
		
		System.out.println(n1);
		System.out.println(n2);
		
		double average = ((n1 + n2) / N);
		
		System.out.println("Average = " + average);
	}

}
