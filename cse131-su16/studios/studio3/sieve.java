package studio3;

import cse131.ArgsProcessor;

public class sieve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);

		int n = ap.nextInt("Choose the length of n:");

		System.out.println("Prime numbers under " + n + ": ");
				
		boolean[] primeNum = new boolean [n + 1];

		for (int i= 2; i<=n; i++) {
			primeNum[i]=true;
		}
		int a = 2;
		while (true) {
			for (int i = 2; i<=n; i++) {
				int m = a * i;
				if (m > n){
					break;
				}
				else {
					primeNum[m] = false;
				}
			}
			boolean nextNumber = false;
			for (int i = a + 1; i < n+1; i++) {
			if(primeNum[i]) {
				a = i;
				nextNumber = true;
				break;
			}
			}
			if (!nextNumber){
				break;
			}
		}
		for (int i = 0; i < n; i++) {
			if (primeNum[i]) {
				System.out.print( i + " ");
			}
		}
		
	}
}

