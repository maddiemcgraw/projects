package lab4;

import cse131.ArgsProcessor;
import sedgewick.*;

public class BumpingBalls {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int numBalls = ap.nextInt("How many balls will be used in the simulation?");
		int numIterations = ap.nextInt("How many iterations would you like the program to run?");

		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);

		class Ball {

			private double rx, ry;
			private double vx, vy;
			private final double radius;

			public Ball() {
				// initial values;
				rx = ((Math.random()-0.5)*2);
				ry = ((Math.random()-0.5)*2);    
				vx = Math.random()*0.015;
				vy = Math.random()*0.023;     
				radius = Math.random()*0.05;            
			}
			// main animation loop

			public void move() {
				// bounce off wall according to law of elastic collision
				if (Math.abs(rx + vx) >= 1.0) vx = -vx;
				if (Math.abs(ry + vy) >= 1.0) vy = -vy;

				// update position
				rx = rx + vx; 
				ry = ry + vy;

			}

			public void draw() {
				// clear the background
				// draw ball on the screen
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle(rx, ry, radius);
			}
		} 

		Ball[] balls = new Ball[numBalls];
		for (int m = 0; m < numBalls; m++)
			balls[m] = new Ball();
		for (int g = 0; g < numIterations; g++) {

			for (int n = 0; n < numBalls; n++) {
				balls[n].move();
				for (int a = n; a < numBalls; a++){
					double distance = Math.sqrt(((balls[n].rx)-(balls[a].rx))*((balls[n].rx)-(balls[a].rx)) + ((balls[n].ry)-(balls[a].ry))*((balls[n].ry)-(balls[a].ry)));
					if (distance < balls[n].radius + balls[a].radius) {
						//balls collided
						balls[n].vx = -(balls[n].vx);
						balls[n].vy = -(balls[n].vy);
						balls[a].vx = -(balls[a].vx);
						balls[a].vy = -(balls[a].vy);
					}
				}
			}
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.filledSquare(0, 0, 1.2);

			StdDraw.setPenColor(StdDraw.BLACK);
			for (int b = 0; b < numBalls; b++) {
				balls[b].draw();
			}
			StdDraw.show(100);
		} 
	}
}

