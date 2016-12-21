package homeowning;

import cse131.ArgsProcessor;

public class HomeOwning {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		
		String AptComplex = ap.nextString("What is the name of the apartment complex?");
		String zipcode = ap.nextString("What is the zip code?");
		double MonthlyRent = ap.nextDouble("What is the monthly rent of the apartment?");
		double Interest = ap.nextDouble("What is the daily interest payment for your mortgage on the house?");

		int YearlyRent = (int) MonthlyRent * 12;
		double WeeklyRent = ((MonthlyRent / 4.333*100)/100);
		double IntPerYear = (Interest *365*100)/100;
		double IntPerWeek = (Interest *7*100)/100;
		
		boolean rent = true;
		if (YearlyRent > IntPerYear) {
			rent = false;
		}
		
		System.out.println(AptComplex + " is located in the Zip Code " + zipcode +".");
		System.out.println("Rent per year: " + YearlyRent);
		System.out.println("Rent per week: " + WeeklyRent);
		System.out.println("Interest paid per year: " + IntPerYear);
		System.out.println("Interest paid per week: " + IntPerWeek);
		
		if (rent == true) {
			System.out.println("I should rent.");
		}
		else {
			System.out.println("I should buy.");
		}
	}

}
