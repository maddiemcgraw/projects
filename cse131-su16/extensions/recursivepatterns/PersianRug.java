package recursivepatterns;

import java.awt.Color;
import sedgewick.*;

public class PersianRug {

/**
 * nextColor chooses a new color to be used
 * @param palette
 * @param north
 * @param east
 * @param south
 * @param west
 * @return
 */
	public static int nextColor(Color [] palette, int north, int east, int south, int west){
		int all = north+east+south+west;
		if((all+1)%palette.length<palette.length/2-1){
			return(all+4)%palette.length;
		}
		else if((all+2)%palette.length>=palette.length/2-1 && (all)%palette.length<palette.length-5){
			return palette.length-8;
		}
		else{
			return 0;
		}

	}
	/**
	 * method divy will use the lower left corner coordinates to draw a cross that divides the square
	 * @param size
	 * @param llx
	 * @param lly
	 */
	public static void divy (double size, double llx, double lly) {
		StdDraw.line(llx+size/2, lly, llx+size/2, lly+size);
		StdDraw.line(llx, lly+size/2, llx+size, lly+size/2);
	}
	
	/**
	 * 
	 * @param palette an array of Colors to choose from
	 * @param llx lower left x coordinate of this rug square
	 * @param lly lower left y coordinate of this rug square
	 * @param size width (and therefore also height) of this rug square
	 * @param north color index of the north side of this rug square
	 * @param east color index of the east side of this rug square
	 * @param south color index of the south side of this rug square
	 * @param west color index of the west side of this rug square
	 */
	private static void persianRug(
			Color[] palette, 
			double llx, double lly,
			double size, 
			int north, int east, int south, int west) {
		int m = nextColor(palette, north, east, south, west);
		StdDraw.setPenColor(palette[m]);
		divy(size, llx, lly);
		north = (north+1)%palette.length;
		east = (east+1)%palette.length;
		south = (south+1)%palette.length;
		west = (west+1)%palette.length;
		
		if(size>.005){
			lly = lly+size/2;
			persianRug(palette, llx, lly, size/2, north, m, m, west);
			llx = llx+size/2;
			persianRug(palette, llx, lly, size/2, north, east, m, m);
			llx = llx -size/2;
			lly = lly - size/2;
			persianRug(palette, llx, lly, size/2, m, m, south, west);
			llx = llx + (size/2);
			persianRug(palette, llx, lly, size/2, m, east, south, m);
		}
		else{
			return;
		}
	}

	
	public static void main(String args[]) {
		//
		// Leave the following line commented out, but once you
		//   have things working, uncomment it, and also uncomment
		//   the similar line at the end of this method.
		// Uncommenting those lines will run the graphics code
		//   in double-buffering mode, so that your image will appear
		//   almost instantaneously, instead of being drawn one line
		//   at a time.
		//
		//  Here is the line to uncomment:
		//
		StdDraw.show(10);   // don't forget to uncomment the other line at the end
		//


		//
		// Generate a palette of colors
		//
		Color[] palette = { StdDraw.BLUE, StdDraw.CYAN, StdDraw.DARK_GRAY,
				StdDraw.GRAY, StdDraw.GREEN, StdDraw.LIGHT_GRAY,
				StdDraw.MAGENTA, StdDraw.ORANGE, StdDraw.PINK, StdDraw.RED,
				StdDraw.WHITE, StdDraw.YELLOW };
		//
		// Draw the outermost square as a special case
		// Use color 0 for that
		//
		StdDraw.setPenColor(palette[0]);
		StdDraw.line(0, 0, 1, 0);
		StdDraw.line(1, 0, 1, 1);
		StdDraw.line(1, 1, 0, 1);
		StdDraw.line(0, 1, 0, 0);

		//
		// Kick off the recursion
		// Lower left is point (0,0)
		// Size of the square is 1
		// The color index of each surrounding side is 0
		//
		persianRug(palette, 0, 0, 1, 0, 0, 0, 0);
		//
		// Also uncomment this line when you have things working
		//   to speed up the drawing:
		//
		StdDraw.show(10);
		//
	}

}
