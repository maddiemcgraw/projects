package golf;

import java.awt.Color;
import sedgewick.StdDraw;

public class Game {
	
	/**
	 * Constructs a Game with an associated Ball, Hole, and Green, as well as with
	 * a way to keep track of how many times the player has hit the ball.
	 */
	public Game(){
		reset();
	}
	
	/**
	 * Set up for a new game, reverting everything 
	 * (as in the Game's instance variables) to factory settings.
	 */
	public void reset(){
		
	}
	
	/**
	 * It's game time baby 
	 * Sets up an animation loop and runs the game.
	 * Displays the number of attempts by the player.
	 * Ends only once the Ball reaches the Hole, then displays a message telling the 
	 * 	 player how they did. A click following the message restarts a new game.
	 */
	public void play(){
	}
	

	/**
	 * Don't change this stuff man, seriously don't change it man c'mon man don't do that
	 * Constructs a new Game and play()s it
	 * @param args
	 */
	public static void main(String[] args){
		Game g = new Game();
		g.play();
	}
}
