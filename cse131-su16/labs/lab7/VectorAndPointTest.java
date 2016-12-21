package lab7;
import static org.junit.Assert.*;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * Official CSE131 Lab 7 Vector JUnit test.  
 *   Demo this to get credit DO NOT CHANGE THIS FILE!!! 
 *       (we are watching you) 
 * @author cytron
 *
 */
public class VectorAndPointTest extends TestCase {

	String s1 = "either your getDeltaX() method isn't doing its job or you're not " +
			"setting your instance variables properly"; 
	String s2 = "either your getDeltaY() method isn't doing its job or you're not " +
			"setting your instance variables properly";
	String s3 = "The plus method shouldn't change the original vector";
	String s4 = "scale isn't working properly";
	@Test
	public void testinit() {
		Vector v = new Vector(5, -3);
		assertEquals(5.0, v.getDeltaX());
		assertEquals(-3.0, v.getDeltaY());
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			Vector v2 = new Vector(a, b);
			assertEquals(s1, a, v2.getDeltaX());
			assertEquals(s2, b, v2.getDeltaY());
		}
	}

	@Test
	public void testarith() {
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			Vector v = new Vector(a, b);
			Vector vPlusV = v.plus(v);
			Vector vMinusV = v.minus(new Vector(a/2, b/2));
			//
			// Make sure the old vector did not change
			//
			compareVectors("your original vector changed when we ran the plus/minus methods", new Vector(a, b), v);
			//
			// Make sure the new vector is right
			//
			compareVectors("Subtracting vectors isn't mathematically correct", new Vector(a/2, b/2), vMinusV);
			compareVectors("The addition is incorrect for plus", new Vector(2*a, 2*b), vPlusV);
			compareDoubles("the x coordinate of the new plus vector is incorrect", 2*a, vPlusV.getDeltaX());
			compareDoubles("the y coordinate of the new plus vector is incorrect", 2*b, vPlusV.getDeltaY());
		}
	}

	/**
	 * Compare two Vectors JUnit-style, failing if they do not
	 * agree on their x and y deltas.
	 * @param one
	 * @param two
	 */
	private void compareVectors(String mes, Vector one, Vector two) {
		compareDoubles(mes, one.getDeltaX(), two.getDeltaX());
		compareDoubles(mes, one.getDeltaY(), two.getDeltaY());
	}


	/**
	 * Why did I write this method?
	 * @param one    one of two doubles to compare
	 * @param other  other of two doubles to compare
	 */
	private void compareDoubles(String message, double one, double other) {
		assertEquals(message, one, other, 0.01);
	}



	@Test
	public void testScaleAndDeflect() {
		for (int i = 0; i < 20; ++i) {
			double a = (Math.random() * 5000);
			double b = (Math.random() * 5000);
			Vector v = new Vector(a, b);
			Vector bigger = v.scale(1.5);
			Vector smaller = v.scale(0.75);
			compareDoubles(s4, a*1.5,   gx(bigger));
			compareDoubles(s4, b*1.5,   gy(bigger));
			compareDoubles(s4, a*.75,  gx(smaller));
			compareDoubles("the y value didn't deflect properly", -b*.75,  gy(smaller.deflectY()));
			compareDoubles("a deflectX() changed the y value!", b*.75,  gy(smaller.deflectX()));
			compareDoubles("deflectX isn't working", -a*.75, gx(smaller.deflectX()));
		}
	}
	
	
	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's x component
	 */
	public double gx(Vector v) {
		return v.getDeltaX();
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's y component
	 */
	public double gy(Vector v) {
		return v.getDeltaY();
	}
	
	@Test
	public void testrescale() {
		for (int i = 0; i < 20; ++i) {
			int a = (int) (Math.random() * 5000);
			int b = (int) (Math.random() * 5000);
			Vector v = new Vector(a, b);
			compareDoubles("Magnitude isn't working", Math.sqrt(a*a+b*b), v.magnitude());
			compareDoubles("Rescale didn't work", a*10/Math.sqrt(a*a+b*b), gx(v.rescale(10)));
			compareDoubles("check your rescale", b*10/Math.sqrt(a*a + b*b), gy(v.rescale(10)));
		}
	}

	@Test
	public void testspecialCases() {
		Vector v = new Vector(0, 0);	
		compareDoubles("the magnitude of a (0,0) vector should be zero", 0, v.magnitude());
		for (int i = 0; i < 20; ++i) {
			double a = (Math.random() * 5000);
			Vector r = v.rescale(a);	
			compareDoubles("rescaling a (0,0) vector should put the rescale in deltaX", a, r.magnitude());
			compareDoubles("rescaling a (0,0) vector should make deltaX == magnitude", a, gx(r));
			compareDoubles("rescaling a (0,0) vector should leave deltaY == 0", 0, gy(r));
		}
	}

	
	
	
	// TESTING POINT CLASS HERE . . . FEEL FREE TO WRITE YOUR OWN, BUT DON'T CHANGE THIS ONE
		// once again, we are watching you
	
	

	@Test
	public void testPointInit() {
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			Point p = new Point(a, b);
			assertEquals("setting and/or getting X of your points isn't working", a, p.getX());
			assertEquals("setting and/or getting Y of your points isn't working", b, p.getY());
		}
	}
	
	@Test
	public void testPointPlus() {
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			double c =  (Math.random() * 5000);
			double d =  (Math.random() * 5000);
			Point p = new Point(a, b);
			Vector v = new Vector(c, d);
			Point p2 = p.plus(v);
			assertEquals("X coordinate of point.plus is wrong", a + c, p2.getX());
			assertEquals("Y coordinate of point.plus is wrong", b + d, p2.getY());
		}
	}
	
	@Test
	public void testPointMinus() {
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			double c =  (Math.random() * 5000);
			double d =  (Math.random() * 5000);
			Point p = new Point(a, b);
			Point p2 = new Point(c, d);
			Vector v = p.minus(p2);
			assertEquals("X coordinate of point.plus is wrong", a - c, v.getDeltaX());
			assertEquals("Y coordinate of point.plus is wrong", b - d, v.getDeltaY());
		}
	}
	
	@Test
	public void testPointDistance() {
		for (int i = 0; i < 20; ++i) {
			double a =  (Math.random() * 5000);
			double b =  (Math.random() * 5000);
			double c =  (Math.random() * 5000);
			double d =  (Math.random() * 5000);
			Point p = new Point(a, b);
			Point p2 = new Point(c, d);
			assertEquals("distance is improperly calculated", Math.sqrt((a - c)*(a - c) + (b - d)*(b - d)), p.distance(p2));
		}
	}
	
}


