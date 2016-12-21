package julia;

/**
 * Complex number, an immutable class
 * @author cytron
 *
 */
public class Complex {
	
	final private double real, imaginary;

	/**
	 * A complex number
	 * @param real part
	 * @param imaginary part
	 */
	public Complex(double real, double imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Return the real part of this complex number.
	 * @return the real part
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Return the imaginary part of this complex number.
	 * @return the imaginary part
	 */
	public double getImaginary() {
		return imaginary;
	}
	
	/**
	 * Return a new Complex number whose real and imaginary parts are scaled by the supplied factor.
	 * @param factor
	 * @return scaled-by-factor version of this Complex number
	 */
	public Complex scale(double factor) {
		return new Complex(factor*this.real, factor*this.imaginary);
	}
	
	/**
	 * Return a new Complex number that is the sum of this
	 *   and the other one
	 * @param other
	 * @return sum of this and the other Complex numbers
	 */
	public Complex plus (Complex other) {
		return new Complex(this.real + other.real, this.imaginary + other.imaginary);
	}

	/**
	 * Return the difference of this and the other Complex numbers
	 * @param other
	 * @return this - other
	 */
	public Complex minus(Complex other) {
		return new Complex(this.real-other.real, this.imaginary-other.imaginary);
	}
	
	/**
	 * Return a new Complex number that is the product of this
	 *   and the other number.
	 * @param other
	 * @return this * other
	 */
	public Complex times(Complex other) {
		double r = this.real*other.real - this.imaginary*other.imaginary;
		double i = this.real*other.imaginary + this.imaginary*other.real;
		return new Complex(r, i);
	}
	
	/**
	 * Return the distance between (0,0) and this Complex number
	 * @return distance from (0,0)
	 */
	public double abs() { 
		return Math.sqrt(real*real + imaginary*imaginary);
	}
	
	public String toString() {
		return "("+ real + " + " + imaginary + "i)";
	}
}
