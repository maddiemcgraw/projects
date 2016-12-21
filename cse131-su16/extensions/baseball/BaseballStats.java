package baseball;

import cse131.ArgsProcessor;

public class BaseballStats {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		
		String name = ap.nextString("The name of the player:");
		int atBats = ap.nextInt("The number of at-bats the player has had:");
		int hits = ap.nextInt("The number of hits the player had:");
		
		double batAvg = (double)hits/atBats;
		String batAvgs = String.format("%.3f", batAvg);
		
		boolean worthy = false;
		if(atBats > 200 && batAvg > 0.270) {
			worthy = true;
		}
		
		System.out.println(name + " " + batAvgs);
		System.out.println("All-Star Worthy? " + worthy);
		
	}

}
