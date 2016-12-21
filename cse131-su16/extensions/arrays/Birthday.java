package arrays;
import cse131.ArgsProcessor;

public class Birthday {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int people = ap.nextInt("How many people will enter the room?");



		int[][] birthday = new int[12][31];
		for(int p = 0; p<people; p++){
			int month = (int) (Math.random() *12);
			int day = (int) (Math.random()*31);
			birthday[month][day] = birthday[month][day]+1;
		}

		double Count = 0.0;
		for(int m = 0; m< 12; m++){
			int numPeople = 0;
			for(int d = 0; d< 31; d++){
				numPeople = birthday[m][d] +numPeople;
			}
			double percent = (numPeople*1.0/people) *1000;
			percent = percent /10;
			System.out.println(percent + "% of people have a birthday in month " + (m+1) + ".");

			Count = percent + Count;
			Count = Count *10;
			Count = Math.round(Count)/10;

		}

		System.out.println(Count/12 + "% is the average number of people that share the same birth month.");
		System.out.println();

		double nextCount = 0.0;
		for(int d =0; d < 31; d++){
			int nextPeople = 0;
			for(int m = 0; m <12; m++){
				nextPeople = birthday[m][d] + nextPeople;
			}
			double nextPercent = (nextPeople *1.0/people) *1000;
			nextPercent = nextPercent/10;
			System.out.println(nextPercent + "% of people have a birthday on day " + (d+1) + " of any given month.");
			nextCount = nextPercent + nextCount;
			nextCount = nextCount *10;
			nextCount = Math.round(nextCount)/10;
		}
		

		System.out.println(nextCount/31 + "% is the average percent of people that share the same day of birthday.");
		System.out.println();
		
		int lastCount = 0;
		for(int m =0; m<12; m++){
			for(int d = 0; d<31; d++){
				if(birthday[m][d] > birthday[m][1] && birthday[m][d] > birthday [1][d]){
					double lastNumPeople = lastCount +1;
					double lastPercent = (lastNumPeople *1.0/people) *1000;
					lastPercent = lastPercent/10;
					lastCount = lastCount +1;
					lastCount = lastCount *10;
					lastCount = Math.round(lastCount)/10;
				}
			}
		}
		System.out.println(lastCount/31 + "% share the exact same birhtday.");
	}


}


