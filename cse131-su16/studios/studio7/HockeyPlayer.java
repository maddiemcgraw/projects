package studio7;

import cse131.ArgsProcessor;

public class HockeyPlayer {

		// parameters = name, jersey number, shooting side (right/left/both), number of games the player has played, how many points they scored in the game, how many assists they attributed during the game
		// record how many goals and assists the player made during the game
		// compile all goals and assists from all games
		// return number of points = the sum of the goals and assists each player earned
	private String Name, Jersey, side;
	private int games, goals, assists, points;
	/**
	 * 
	 * @param Name
	 * @param Jersey
	 * @param side
	 * @param games
	 * @param goals
	 * @param assists
	 */
		public HockeyPlayer(String Name, String Jersey, String side, int games, int goals, int assists) { 
			this.Name = Name;
			this.Jersey = Jersey;
			this.side = side;
			this.games = games;
			this.goals = goals;
			this.assists = assists;
		}
		/**
		 * 
		 * @param lastGoals
		 * @param lastAssists
		 * @return updated number of games
		 */
		public int lastGame(int lastGoals, int lastAssists) {
			games++;
			goals = goals + lastGoals;
			assists = assists + lastAssists;
			return games;
		}
		
		/**
		 * 
		 * @return sum of goals and assists
		 */
		public int getPoints() {
			points = goals + assists;
			return points;
	}
		public String toString(){
			return Name + " , " + Jersey + ", " + "(" + side + ") " + "\n" + "Games: " + games + "\n" + "Total goals: " + goals + "\n" + "Total assists: " + assists + "\n" + "Points: " + getPoints();
		}

		public static void main(String[] args) {
			HockeyPlayer p1 = new HockeyPlayer("Tarasenko", "91", "Right", 80, 40, 34);
			p1.lastGame(9,7);
			p1.getPoints();
			System.out.println("Hockey Player " + p1 );
		}
}
