package studio2;

import cse131.ArgsProcessor;

public class Survivor {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);

		int allHits = ap.nextInt("Number of darts to throw: ");

		int inCircle = 0;
		int simulations = 0;

		double x, y, r;

		for (simulations = 0; simulations < allHits; simulations++) {
			x = Math.random() - .5;
			y = Math.random() - .5;
			r = Math.sqrt(x*x + y*y);
			if (r<=.5) {	
				inCircle++;
			};
		}
		double ans = ((double)inCircle/(double)allHits)*4;


		System.out.println("Our group shows Pi = " + ans);

	}
}





//  fill in to compute ans = Pi

