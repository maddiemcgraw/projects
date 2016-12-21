package arrays;

import java.util.Arrays;

import cse131.ArgsProcessor;

public class Sorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);


		int size = ap.nextInt("What is the size of the collection?");
		double[] data = new double[size];
		double[] ordered = new double[data.length];
		for(int m=0; m<data.length; m++){
			double val = ap.nextDouble("Enter data value.");
			data[m] = val;
		}


		double first =0;


		int sortCount = 0;
		double total = 0;
		
			for(int i = data.length-1; i>=0; i--){
				for(int m =1; m<= i; m++){
					if(data[m-1]> data[m]){
						double temp = data[m-1];
						data[m-1]=data[m];
						data[m]=temp;
						
					}
				}
			}
		
		
		

		for(int i = 0; i < data.length; i++){
			if(i>0){
				System.out.print(", ");
			}
			System.out.print(data[i]);
			total=total+data[i];
		}
		
		double mean = total/(size);
		double median = data[size/2];
		double min = data[0];
		double max = data[size-1];
		double range = max - min;
		
		System.out.println();
		System.out.println("Mean: " + mean);
		System.out.println("Meadian: " + median);
		System.out.println("Min: " + min);
		System.out.println("Max: " + max); 
		System.out.println("Range: " + range);
	}

}
