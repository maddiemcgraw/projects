package lab6;

import java.awt.Color;

import sedgewick.StdDraw;

public class Triangles {

		public static void triangles(double x, double y, double z) {

			double[] b = {0, 0.5, 1};
			double[] v = {0, 1, 0};
			StdDraw.polygon(b, v);
			
			if (z < .05) {
				return;
			}
			else {

				StdDraw.setPenColor(Color.BLACK);
				double [] xX = {  (x + z/4),  (x + z/2),  (x+(3*z)/4)};
				double [] yY = {(y + z/2), (y), (y+ z/2)};
				
				StdDraw.filledPolygon(xX,yY);

				triangles(x+z/4, y + z/2, z*.5);
				triangles(x, y, z*.5);
				triangles(x+z/2, y, z*.5);
			}


		}

		public static void main(String[] args) {
			// FIXME Auto-generated method stub

		StdDraw.show(10);
		triangles((double) 0,(double) 0,(double) 1);
		StdDraw.show(10);
	}

}