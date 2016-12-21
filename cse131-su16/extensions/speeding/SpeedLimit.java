package speeding;

import cse131.ArgsProcessor;

public class SpeedLimit {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);

		int speed = ap.nextInt("What was the speed of the driver?");
		int limit = ap.nextInt("What is the speed limit in the area?");
		int x = (speed >= limit + 10) ? 2 : 1;
		int fine = 50;
		int diff = speed - limit;

		while (x == 1){
			System.out.println("Driver did not exceed the speed limit by 10 or more miles per hour.");
		}

		for (int y = limit + 10; y < speed; y++) {
			fine = fine + 10;
		}
		System.out.println("You reported that the driver was going a speed of " + speed + " for a speed limit of " + limit + " MPH.");
		System.out.println("The driver was going " + diff + " MPH over the speed limit.");
		System.out.println("The fine is $" + fine +".");
	}


}
