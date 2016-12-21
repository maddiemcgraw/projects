package cards;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import cse131.ArgsProcessor;

public class Blackjack {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		// TODO Auto-generated method stub
		int players = ap.nextInt("How many players?");

		int[] total = new int[players+2];
		int m=0;
		int max=0;
		int h;
		int winner=0;

		//deck of cards
		String [] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
		String [] value = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

		boolean[][] cards = new boolean[value.length][suit.length];
		boolean[] skip = new boolean[players+2];

		int skips = 0;
		for(int i = 0; i<=players; i++){
			skip[i]=false;
		}

		for(int i = 0; i<value.length; i++){
			for(int g=0; g<suit.length; g++){
				cards[i][g]=true;
			}
		}

		for(int p =0; p<=52; p++){
			for (int i = 0; i<players; i++){

				if(skip[i] == false && total[i]<21){
					String next = ap.nextString("Player " + (i+1)+ " , you are at " + total[i] + ". Hit?");
					next.toLowerCase();
					if(next.equals("yes")){
						int v = (int) (Math.random()*13);
						int s = (int) (Math.random()*4);
						if(cards[v][s]==false) {
							v = (int) (Math.random()*13);
							s = (int) (Math.random()*4);
						}
						else{

							if(v+1>=10 && v+1!=13){
								m=10;
							}
							else if(v+1==13){
								m=11;
							}
							else if(v+1<10){
								m=v+2;
							}

							total[i]= (total[i]+m);
							if(total[i] > 21){
								for(h =0; h<players; h++){
									if(total[h]>max && total[h]<=21){
										max = total[h];
									}
								}
								for(int x =0; x<players; x++){
									if(total[x]==max && total[x]<=21){
										winner = x+1;
									}
								}
								System.out.println("Player " + (i+1) + ": "+ value[v] + " of " + suit[s]+ ". You have lost.");
								System.out.println("Player " + winner + " has won with " + max + ".");
								break;
							}
							else{
								System.out.println("Player " + (i+1) +": "+  value[v] + " of "+ suit[s] +". You are now at " + total[i] + ".");
								System.out.println(skip[i]);
							}
							if(total[i]==21){
								System.out.println("Player " + (i+1) + " has won!");
								break;
							}
							cards[v][s]=false;
						}
					}
					if(next.equals("no")){
						//if player skips they must be removed from the list
						//if last two players, the other player wins
						System.out.println("Player " + (i+1) + " has decided to skip.");
						skip[i]=true;

						for(int j=0; j<players; j++){
							if(skip[j]=true){
								skips++;
							}
							if(skips==players-1){
								break;
							}
						}
					}
				}
				if(total[i]>=21){
					break;
				}
			}
			
		}
	}


}