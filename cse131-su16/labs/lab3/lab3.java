package lab3;

import java.util.Arrays;

import cse131.ArgsProcessor;

public class lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		int faces = ap.nextInt("How many faces per dice?");
		int NumOfDice = ap.nextInt("How many dice will be used in this simulation?");
		int NumOfThrows = ap.nextInt("How many times will the dice be thrown?");
		
		int[] diceThrown = new int[NumOfDice];
		String[] timeThrown = new String[NumOfThrows];
		int[] Sum = new int[NumOfThrows];
		int d = 0;
		int count = 0;
		
		
		for (int i=0; i<=NumOfThrows; i++) {
			int sum = 0;
			int streak = 0;
			if (i==NumOfThrows) {
				break;
			}
			else {
			for (d = 0; d<=NumOfDice; d++){
				if (d == NumOfDice) {
					break;
				}
				else {
					int m = (int)(Math.random()*faces +1);
					diceThrown[d]= m;
					
					sum += diceThrown[d];
					if (m == diceThrown[0]) {
						streak++;
					}
				}
				timeThrown[i]=Arrays.toString(diceThrown) + ", ";
				if (streak==NumOfDice){
					count++;
				}
			}
			Sum[i]=sum;
			System.out.println("Toss " + (i+1) + ": " + timeThrown[i] + ": Sum: " + Sum[i]);
			
		}
	
	}
		
		double frequency = ((double)count/(double)NumOfThrows)*100000/1000;
		System.out.println("Frequency of same number rolled in one throw: " + frequency + "%." );
		
	
}
}
