package lab2;

import cse131.ArgsProcessor;

public class lab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);

		String player = ap.nextString("Would you like to play first?");

		int pile = 7;
		String lastPlayer = null;
		int comp;
		
		if (player.equals("yes")) {
			for (int i = 8; i > 0; i--) {
				if (pile <= 0) {
					System.out.println("There are no more pieces in the pile. " + lastPlayer + " has won.");
					break;
				}
				else {
					int choose = ap.nextInt("Select 1 or 2 pieces to remove from the pile.");
					if (choose == 1) {
						pile--;
					}
					else {
						pile--;
						pile--;
					}
					System.out.println("Player 1 has removed " + choose + " from the pile. Now, " + pile + " remain.");
					lastPlayer = "Player 1";
					if (pile <= 0) {
						System.out.println("There are no more pieces in the pile. " + lastPlayer + " has won.");
						break;
					}
					else {
						comp = (int) Math.ceil(Math.random() * 2);
						if (comp == 1) {
							pile--;
						}
						else {
							pile--;
							pile--;
						}
						System.out.println("Player 2 has removed " + comp + " from the pile. Now, " + pile + " remain.");
						lastPlayer = "Player 2";
					}}}}
		else {
			for (int i = 8; i > 0; i--) {
				if (pile <= 0) {
					System.out.println("There are no more pieces in the pile. " + lastPlayer + " has won.");
					break;
				}
				else {
					if (pile ==1) {
						comp = 1;
					}
					else{ 
						comp = (int) Math.ceil(Math.random() * 2);
					}
					if (comp == 1) {
						pile--;
					}
					else {
						pile--;
						pile--;
					}
					System.out.println("Player 1 has removed " + comp + " from the pile. Now, " + pile + " remain.");
					lastPlayer = "Player 1";
					if (pile <= 0) {
						System.out.println("There are no more pieces in the pile. " + lastPlayer + " has won.");
						break;
					}
					else {
						int choose = ap.nextInt("Select 1 or 2 to remove from the pile.");

						if (choose == 1) {
							pile--;
						}
						else {
							pile--;
							pile--;
						}
						System.out.println("Player 2 has removed " + choose + " from the pile. Now, " + pile + " remain.");
						lastPlayer = "Player 2";
					}
				}
			}

			
		}
	}
}







