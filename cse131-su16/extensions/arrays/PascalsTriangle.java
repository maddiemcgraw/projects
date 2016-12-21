package arrays;
import cse131.ArgsProcessor;

public class PascalsTriangle {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int N = ap.nextInt("What is the number of rows you would like to compute of the triangle?");

		int[][] pascal = new int[N+1][];   // intersections visited 

		pascal[1] = new int[1+2];
		pascal[1][1] = 1;

		for (int m = 2; m <= N; m++){
			pascal[m]= new int[m+2];
			for (int b = 1; b < pascal[m].length-1; b++){
				pascal[m][b] = pascal[m-1][b-1] + pascal [m-1][b];
			}
		}
		for (int m = 1; m <= N; m++){
			for (int b = 1; b < pascal[m].length-1; b++){

				System.out.print(pascal[m][b]+" ");


			}

			System.out.println();
		}
	}
}


