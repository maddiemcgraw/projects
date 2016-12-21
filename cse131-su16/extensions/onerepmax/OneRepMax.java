package onerepmax;

import cse131.ArgsProcessor;

public class OneRepMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		int weight = ap.nextInt("Successful weight:");
		int reps = ap.nextInt("Successful reps:");
		int desired = ap.nextInt("Desired reps:");

		double repmax = Math.floor(weight*(1 + ((double)reps/30))/5)*5;
		double DesiredWeight = Math.floor((repmax/(1+((double)desired/30)))/5)*5;
int REPmax = (int) repmax;
int DESIREDWeight = (int) DesiredWeight;
		System.out.println("One-rep max: " + REPmax);
		System.out.println("Weight for " + desired + " reps: " + DESIREDWeight);


		for (int x = 95; x > 0; x= x - 5){
			int RM = (int)(Math.floor((double) (x*repmax)/100/5)*5);
			
			System.out.println(x + "% 1 RM:  " + RM);
		}

	}
}
