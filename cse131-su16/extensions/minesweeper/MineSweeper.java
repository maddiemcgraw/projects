package minesweeper;

import cse131.ArgsProcessor;

public class MineSweeper {

	public static void main (String[] args) {

		//
		// Do not change anything between here ...
		//
		ArgsProcessor ap = new ArgsProcessor(args);
		int cols = ap.nextInt("How many columns?");
		int rows = ap.nextInt("How many rows?");
		double percent = ap.nextDouble("What is the probability that there is a bomb?");
		//
		// ... and here
		//
		//  Your code goes below these comments
		//
		boolean [][] bombs = new boolean [cols+2][rows+2];
		for (int c = 1; c <= cols; c++){
			for (int r = 1; r <= rows; r++){
				bombs[c][r] = (Math.random() < percent);
			}
		}

		for (int c = 1; c <=cols; c++) {
			for(int r = 1; r<= rows; r++){
				if(bombs[c][r]) {
					System.out.print("* ");
				}
				else{
					System.out.print(". ");
				}
				
			}
			System.out.println();
		}

		int [][] sol = new int[cols+2][rows+2];
		for (int c = 1; c <= cols; c++){
			for (int r = 1; r <= rows; r++){
				for (int C = c-1; C <= c+1; C++){
					for (int R = r-1; R <=r+1; R++){
						if(bombs[C][R]){
							sol[c][r]++;
						}
					}
				}
			}
		}

		System.out.println();
		for(int c = 1; c <= cols; c++){
			for(int r = 1; r<= rows; r++){
				if (bombs[c][r]){
					System.out.print("* ");
				}
				else {
					System.out.print(sol[c][r] + " ");
				}
				
			}
			System.out.println();
		}
	}
}
