package BankInterestCalculator;

import cse131.ArgsProcessor;

public class bankinterestcalc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		double account = 4000;
		int Day;
		String sChoice = null;
		double Amount = 0;
		int Balance;
		
		System.out.println("Day     Type         Amount         Balance");
		for (Day = 1; Day <= 30; Day++){
			int choice = (int)(Math.random()*2 + 1);
			//withdraw
			if (choice == 1){
				account = account - 100;
				Amount = 100.00;
				sChoice ="WITHDRAW";
			}
			//deposit
			if (choice == 2) {
				account = account + 200.50;
				Amount = 200.50;
				sChoice = "DEPOSIT";

			}
			System.out.println(Day + "     " + sChoice + "     " + Amount + "     " + account);
		}
		double interest = account*.02;
		account = account + interest;

		System.out.println("Interest earned: " + interest);
		System.out.println("Money after one month's interest: " + account);
	}

}
