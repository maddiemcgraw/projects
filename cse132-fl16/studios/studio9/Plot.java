package studio9;

import sedgewick.StdDraw;
import java.util.*;
import java.awt.Font;
import java.io.*;
import java.nio.CharBuffer;


public class Plot {
	
	public static void main(String args[]) throws IOException {
		FileReader fr = new FileReader("/Users/madeline/git/cse132-fl16-memcgraw/studios/studio8/data.csv");
		List<char> data = new ArrayList<char>();
		fr.read(data);
//		
//		double ax = (char)data.get(0);
//		double ay = (char)data.get(1);
//		double az = (char)data.get(2);
//		
//		for (double c : a) {
//			System.out.print(c);
//		}
//		fr.close();
		
		//set canvas size
		StdDraw.setCanvasSize();

		//read the data
		//point size and color
		//data over time
		
		
		//print the data one dot at a time
		for(int m = 0; m<45; m++){
		double x = .1 + m;
		double y = (double)data.get(m);
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(0.5, 0.5);
		}
		//draw axis lines
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);
		
		//tick marks for 45 seconds
		
		
		//labels
		Font font = new Font("Arial", Font.BOLD, 60);
		StdDraw.setFont(font);
		//title
		StdDraw.text(.5,.5,"Peaks");
		//left axis
		StdDraw.text(0.5, 0.5, "Acceleration", 90);
		//right axis
		StdDraw.text(.75, .5, "Time");
		
	}
}

//need some way to move all the points over one to make room for the new one
//


