package golf;

import static org.junit.Assert.*;

import java.awt.Color;

import lab7.Vector;

import org.junit.Test;

import sedgewick.StdDraw;

public class TestBall {

	@Test
	public void test() {
		Ball b =new Ball(0,0);
		Vector v = new Vector(1.0/25, 1.0/25);
		for (int i=0; i < 25; ++i) {
			StdDraw.clear();
			b.inc(v);
			verifyBall(b);
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledSquare(0.5, 0.5, 0.5);
			b.draw();
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0.5, 0.1, "You should see the ball moving diagonally");
			StdDraw.text(0.5, 0.05, "Trial " + i + " out of " +25);
			StdDraw.pause(250);
		}
	}
	
	private void verifyBall(Ball b) {
		assertTrue("Horizontal coordinate too small " + b.getPoint().getX(), b.getPoint().getX() >= 0.0);
		assertTrue("Horizontal coordinate too large " + b.getPoint().getX(), b.getPoint().getX() <= 1.01);
		assertTrue("Vertical coordinate too small " + b.getPoint().getY(), b.getPoint().getY() >= 0.0);
		assertTrue("Vertical coordinate too large " + b.getPoint().getY(), b.getPoint().getY() <= 1.01);
	}

}
