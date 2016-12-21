package biofindframe;

import java.io.File;

import org.biojava3.core.sequence.DNASequence;

import biojava.SequenceLoader;
import cse131.ArgsProcessor;

public class FindTheFrame {

	//
	// Do not change any code from here....
	//

	public static void main(String[] args) {
		File file = ArgsProcessor.chooseFile("genomes");
		System.out.println("For " + file + ", best reading frame is " + runsolution(file));
	}

	public static int runsolution(File file) {
		//
		// Load the sequence into a DNASequence object
		//
		SequenceLoader sequenceLoader = new SequenceLoader();
		DNASequence dnaSequence = sequenceLoader.loadDNASequence(file);

		//
		// Convert the sequence to a string and then a char array
		//
		String dnaAsString = dnaSequence.getSequenceAsString().toUpperCase();
		char[] dnaAsCharArray = dnaAsString.toCharArray();

		//
		// Call your solution to compute the result
		//
		int frame = bestReadingFrame(dnaAsCharArray);

		return frame;
	}

	//
	// ... to here, so we can unit test your solution
	//

	/**
	 * 
	 * @param dna an array of char.  Each element is a nucleotide:  one of A, T, C, or G.
	 * @return the index at which the best reading frame occurs.  This would be 0, 1, or 2.
	 */
	public static int bestReadingFrame(char[] dna) {

		// Below, define each of the three Stop Codons as a separate array of char,
		//     named ochre, amber, and opal
		//     See http://en.wikipedia.org/wiki/Genetic_code#Start.2Fstop_codons

		char[] ochre = {'T', 'A', 'A'};
		char[] amber = {'T', 'A', 'G'};
		char[] opal = {'T', 'G', 'A'};

		// Below, define the Start Codon (Methionine) as an array of char
		//Start Codon

		char[] methionine = {'A', 'T', 'G'};

		int ans = -1;  // returned if no appropriate sequences was found

		//
		// Follow the instructions in the extension write up
		//

		int codonIndex0 = 0;
		int codonIndex1 = 0;
		int codonIndex2 = 0;
		int nextCodonIndex0 = 0;
		int nextCodonIndex1 = 0;
		int nextCodonIndex2 = 0;

		boolean start = false;



		for(int i = 0; i < dna.length-2; i+=3){
			if(dna[i] == 'A' && dna[i+1] =='T' && dna[i+2] == 'G' && !start){
				start = true;
			}
			codonIndex0 += 3;
			
			if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex0 >= nextCodonIndex0){
					nextCodonIndex0 = codonIndex0;
				}

				codonIndex0 = 0;

			}
			if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'G' && start){
				start = false;
				if(codonIndex0 >= nextCodonIndex0){
					nextCodonIndex0 = codonIndex0;
				}

				codonIndex0 = 0;

			}
			if(dna[i] == 'T' && dna[i+1] =='G' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex0 >= nextCodonIndex0){
					nextCodonIndex0 = codonIndex0;
				}

				codonIndex0 = 0;

			}
			
			
			
		}

		start = false;

		for(int i = 1; i < dna.length-2; i+=3){
			if(dna[i] == 'A' && dna[i+1] =='T' && dna[i+2] == 'G' && !start){
				start = true;
			}
			codonIndex1 += 3;
			
			if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex1 >= nextCodonIndex1){
					nextCodonIndex1 = codonIndex1;				
				}
				codonIndex1 = 0;

			}
			else if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'G' && start){
				start = false;
				if(codonIndex1 >= nextCodonIndex1){
					nextCodonIndex1 = codonIndex1;				
				}
				codonIndex1 = 0;

			}
			if(dna[i] == 'T' && dna[i+1] =='G' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex1 >= nextCodonIndex1){
					nextCodonIndex1 = codonIndex1;
				}

				codonIndex1 = 0;

			}
		}

		start = false;

		for(int i = 2; i < dna.length-2; i+=3){
			if(dna[i] == 'A' && dna[i+1] =='T' && dna[i+2] == 'G' && !start){
				start = true;
			}
			codonIndex2 += 3;
			
			if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex2 >= nextCodonIndex2){
					nextCodonIndex2 = codonIndex2;				
				}
				codonIndex2 = 0;

			}
			if(dna[i] == 'T' && dna[i+1] =='A' && dna[i+2] == 'G' && start){
				start = false;
				if(codonIndex2 >= nextCodonIndex2){
					nextCodonIndex2 = codonIndex2;				
				}
				codonIndex2 = 0;

			}
			if(dna[i] == 'T' && dna[i+1] =='G' && dna[i+2] == 'A' && start){
				start = false;
				if(codonIndex2 >= nextCodonIndex2){
					nextCodonIndex2 = codonIndex2;
				}
				codonIndex2 = 0;
			}
		}


		if((nextCodonIndex0> nextCodonIndex1)&&(nextCodonIndex0 > nextCodonIndex2)){
			ans = 0;
		}
		if((nextCodonIndex1> nextCodonIndex0)&&(nextCodonIndex1 > nextCodonIndex2)){
			ans = 1;
		}
		if((nextCodonIndex2> nextCodonIndex0)&&(nextCodonIndex2 > nextCodonIndex1)){
			ans = 2;
		}

		return ans;
	}
}
