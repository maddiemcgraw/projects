package golf;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import sedgewick.StdDraw;

public class TestGreen {

	@Test
	public void test() {
		for (int i=0; i < 25; ++i) {
			StdDraw.clear();
			Green g = new Green();
			g.draw();
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0.5, 0.1, "You should see a randomly colored Green");
			StdDraw.text(0.5, 0.05, "Trial " + i + " out of " +25);
			StdDraw.pause(250);
		}
	}
	
}
