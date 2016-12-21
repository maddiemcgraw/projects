package RockPaperScissors;

import cse131.ArgsProcessor;

public class RPS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		String name = ap.nextString("What is your name?");
		int rounds = ap.nextInt("How many rounds would you like to play?");
		String choice = null;
		String Computer = null;
		int ComputerWins = 0;
		int PlayerWins = 0;
		int Ties = 0;
		String Oponent = null;
		String Address = null;
		String CellPhone = null;
		int ComputerOp = (int) (Math.random()*2 + 1);
		if (ComputerOp == 1){
			Oponent = "Josh";
			Address = "Florida";
			CellPhone = "3943442544";
		}
		else {
			Oponent = "Rachel";
			Address = "Michigan";
			CellPhone = "4505607899";
		}
		System.out.println("Player 2's name is " + Oponent + " from " + Address + ". Their cellphone number is " + CellPhone + ".");
		for (int m = 1; m <= rounds; m++){
			choice = ap.nextString("Please, choose rock, paper, or scissors?");
			choice.toLowerCase();
			System.out.println("Round " + m + ":  ");
			if (choice.equals("rock") || choice.equals("paper") || choice.equals("scissors")){
				int comp = (int)(Math.random()*3 + 1);

				if (comp == 1){
					Computer = "rock";
					if (choice.equals("rock")){
						System.out.println("Players tie.");
						Ties++;
					}
					if (choice.equals("paper")){
						System.out.println(name +" wins.");
						PlayerWins++;
					}
					if (choice.equals("scissors")){
						System.out.println(Oponent + " wins.");
						ComputerWins++;
					}
					System.out.println(Oponent + " chose rock.");
				}
				if (comp == 2){
					Computer = "paper";
					if (choice.equals("rock")){
						System.out.println(Oponent + " wins.");
						ComputerWins++;
					}
					if (choice.equals("paper")){
						System.out.println("Players tie.");
						Ties++;
					}
					if (choice.equals("scissors")){
						System.out.println(name + " wins.");
						PlayerWins++;
					}
					System.out.println(Oponent + " chose paper.");
				}
				if (comp == 3){
					Computer = "scissors";
					if (choice.equals("rock")){
						System.out.println(name +" wins.");
						PlayerWins++;
					}
					if (choice.equals("paper")){
						System.out.println(Oponent +" wins.");
						ComputerWins++;
					}
					if (choice.equals("scissors")){
						System.out.println("Players tie.");
						Ties++;
					}
					System.out.println(Oponent + " chose scissors.");

				}
			}
			else {
				System.out.println("I'm sorry but that is an incorrect choice. You have forfeited the round.");
				ComputerWins++;
			}
		}
		String winner = null;
		if (ComputerWins>PlayerWins){
			winner = Oponent + " wins!";
		}
		if (ComputerWins==PlayerWins){
			winner = "Players tie!";
		}
		if (PlayerWins>ComputerWins){
			winner = name + " wins!";
		}

		System.out.println("Total Games: " + rounds + "     " + name + " wins: " + PlayerWins + "     " + Oponent + " wins: " + ComputerWins);
		System.out.println(winner);
	}

}
