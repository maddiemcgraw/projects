package mousefollower;

import sedgewick.*;

import java.awt.Color;

import cse131.ArgsProcessor;

public class FollowTheMouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		
		double[][] getMousePosition = new double[(int) StdDraw.mouseX()][(int) StdDraw.mouseY()];
		
		int p = 0;
		
		while (true) {
			StdDraw.clear();
			
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			
			
			p = p + 1;
			
			
			int last = p;
			
			
			StdDraw.show(1);
			
		}
	}

}
