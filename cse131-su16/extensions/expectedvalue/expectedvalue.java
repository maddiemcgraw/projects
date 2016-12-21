package expectedvalue;

import cse131.ArgsProcessor;

public class expectedvalue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);

		double p = ap.nextDouble("What is the probability of the start-up ventur succeeding?");
int monGamer = 0;
int monProgrammer = 0;
		if (p>0 && p<1){

			double Gamer = (p * 190000) + (1-p * 50000);
			monGamer = (int) Gamer;
			double Programmer = Math.random()*50000.0 + 110000.0;
			monProgrammer = (int) Programmer;
			boolean job = true;

			if (Gamer < Programmer) {
				job = false;
				
			}
			System.out.println("Gamer: $" + monGamer);
			System.out.println("Programmer: $" + monProgrammer);
			System.out.println("You should be a gamer and not a programmer? "+ job);
		}
		else {
			System.out.println("That is not a proper value for the probability.");

		}
System.out.println();
double EUgamer = 3*Math.sqrt(monGamer);
int EuGamer = (int) EUgamer;
double EUprogrammer = 2*Math.sqrt(monProgrammer);
int EuProgrammer = (int) EUprogrammer;
System.out.println("New utility for gamer: " + EuGamer);
System.out.println("New utility for programmer: " + EuProgrammer);

if (EUgamer > EUprogrammer) {
	System.out.println("Take job as gamer.");
}
else {
	System.out.println("Take job as programmer.");
}
		

	}

}

