package lab7;

public class Vector {
	
	// 1.3 Create two instance variables in the Vector class named deltaX and deltaY
	private final double deltaX;
	private final double deltaY;
	
	// 1.4 Create a constructor in the Vector class that takes the parameters and assigns their values to deltaX and deltaY
	public Vector(double deltaX, double deltaY) {
		super();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	// 1.5 Create a string method that takes no parameters and returns a string value
	public String toString() {
		return "[" + deltaX + ", " + deltaY + "]";
	}

	// 1.6 Define two accessor methods named getDeltaX and getDeltaY
	// Neither accessor should take parameters
	// Return instance variables of deltaX and deltaY

	public double getDeltaX() {
		return deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	// 1.7 Use the pythagorean theorem to compute the length of the vector
	public double magnitude () {
		return Math.sqrt( (deltaX * deltaX) + (deltaY * deltaY));
	}
	
	// 1.9 Define a method called deflectX that returns a new vector
	public Vector deflectX() {
		return new Vector(-deltaX, deltaY);
	}
	
	// 1.10 Define a method called deflectY that returns a new vector
	public Vector deflectY() {
		return new Vector(deltaX, -deltaY);
	}
	
	// 1.11 Define a method called plus, parameter vector
	public Vector plus(Vector m) {
		return new Vector((deltaX + m.getDeltaX()), (deltaY + m.getDeltaY()));
	}
	
	// 1.12 Define a method called minus
	public Vector minus(Vector m) {
		return new Vector((deltaX - m.getDeltaX()), (deltaY - m.getDeltaY()));
	}
	
	// 1.13 Define a method called scale that takes a double named factor as its parameter
	public Vector scale(double factor) {
		return new Vector(deltaX * factor, deltaY * factor);
	}
	
	// 1.14 Define a method called rescale that takes a double named magnitude as its parameter
	public Vector rescale (double magnitude) {
		double magni = magnitude();
		
		if (magni == 0) {
			return new Vector(magnitude, 0);
		}
		else {
			return scale(magnitude/magni);
		}
	}
	
}
