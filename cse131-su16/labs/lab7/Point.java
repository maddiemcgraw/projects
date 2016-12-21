package lab7;

public class Point {
	//2.2 set private doubles for x and y
	private final double x;
	private final double y;
	
	//2.3 Write a constructor that takes x and y as parameters
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	//2.4
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	//2.5 Create toString
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	//2.6 Add a vector to a point, parameter Vector, createas a new point
	public Point plus(Vector m) {
		return new Point (x + m.getDeltaX(), y + m.getDeltaY());
	}
	
	//2.7 Create a minus method, parameter Point, returns new vector
	public Vector minus(Point Q){
		return new Vector(x - Q.getX(), y - Q.getY());
	}
	
	//2.8 Write a method distance that takes another point as a parameter and returns the distance
	public double distance(Point P) {
		return Math.sqrt(((x-P.getX())*(x-P.getX())) + ((y-P.getY())*(y-P.getY())));
	}
}
