package golf;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import sedgewick.StdDraw;

public class TestHole {

	@Test
	public void test() {
		for (int i=0; i < 25; ++i) {
			StdDraw.clear();
			Hole h =new Hole();
			verifyHole(h);
			h.draw();
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0.5, 0.1, "You should see the hole and flag moving around randomly");
			StdDraw.text(0.5, 0.0, "Trial " + i + " out of " +25);
			StdDraw.pause(250);
		}
	}
	
	private void verifyHole(Hole h) {
		assertTrue("Horizontal coordinate too small " + h.getPoint().getX(), h.getPoint().getX() >= 0.3);
		assertTrue("Horizontal coordinate too large " + h.getPoint().getX(), h.getPoint().getX() <= 0.7);
		assertTrue("Vertical coordinate too small " + h.getPoint().getY(), h.getPoint().getY() >= 0.3);
		assertTrue("Vertical coordinate too large " + h.getPoint().getY(), h.getPoint().getY() <= 0.7);

	}

}
