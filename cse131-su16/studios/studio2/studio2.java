package studio2;

import cse131.ArgsProcessor;

public class studio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			ArgsProcessor ap = new ArgsProcessor(args);
			int startAmount = ap.nextInt("The amount of money that you start with:");
			double winChance = ap.nextDouble("The probability that you win a gamble:");
			int winAmount = ap.nextInt("If you reach this amount of money, then you won:");
			int totalPlays = ap.nextInt("The number of times you simulate the problem:");
			
			int rounds = 0;
			int wins = 0;
			int simulations = 0;
			
			double Ruin;
			double lossChance = (1-winChance);
			
			if (lossChance != winChance) {
				Ruin = (Math.pow(lossChance/winChance,startAmount)-Math.pow(lossChance/winChance,winAmount))/(1-(Math.pow((lossChance/winChance),winAmount)));
			}
			else {
				Ruin = (1-(startAmount/winAmount));
			}
			
		
			
			for (simulations = 0; simulations < totalPlays; simulations++)	{ 
				int cash = startAmount;
			do  {
				rounds++;
				if (Math.random() < winChance)	cash++;
				else						cash--;
			}	while (cash > 0 && cash < winAmount);
			if (cash >= winAmount){
				wins++;
				System.out.println("Simulation " + simulations + ": "+ rounds + " rounds          " + "WIN");
				}
			else {
				System.out.println("Simulation " + simulations + ": "+ rounds + " rounds          " + "LOSE");
			}
			
			}
			int losses = totalPlays - wins;

			double actRuin = ((double)losses/(double)totalPlays);
			
			System.out.println("Losses: " + losses +"     Simulations: " + totalPlays);
			System.out.println("Actual Ruin Rate: " + actRuin + "     Expected Ruin Rate: " + Ruin);
			System.out.println(losses + " " + totalPlays + " "+ wins);
			}
	}

			
			
	

