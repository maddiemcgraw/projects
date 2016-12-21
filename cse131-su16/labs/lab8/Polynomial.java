package lab8;

import java.util.Iterator;
import java.util.LinkedList;

public class Polynomial {

	final private LinkedList<Double> list;

	/**
	 * Constructs a Polynomial with no terms yet.
	 */
	public Polynomial() {
		//
		// Set the instance variable (list) to be a new linked list of Double type
		//
		list = new LinkedList<Double>();
	}

	public String toString() {
		String poly = "";
		for(int m = 0; m < list.size(); m++) {
		if (m < list.size()) {
			poly = poly + list.get(m) + "x^" + m + " + ";
		}
		else{
			poly = poly + list.get(m) + "x^" + m;
		}
		}
		return poly; // FIXME
	}

	public Polynomial addTerm(double coeff) {
		list.add(coeff);
		return this;  // required by lab spec
	}

	public double horner(double x, int index) {
		if(index ==0){
			return list.get(index);
		}
		else {
			return list.get(index)*Math.pow(x, index) + horner(x, index - 1);
		}
	}
	
	public double evaluate(double x) {
		if(list.size() == 0) {
			return 0;
		}
		else{
			return horner(x, list.size()-1);
		} // FIXME
	}
	
	public Polynomial derivative() {
		Polynomial derivative = new Polynomial();
		for(int m = 0; m < this.list.size()-1; m++){
			double coeff = this.list.get(m+1)*(m+1);
			derivative.list.add(m,coeff);
		}
		return derivative;   // FIXME
	}
	
	public Polynomial sum(Polynomial another) {
		Polynomial sum = new Polynomial();
		int sameDegree = Math.min(this.list.size(), another.list.size());
		
		for(int m = 0; m < sameDegree; m++){
			sum.list.add(m, this.list.get(m) + another.list.get(m));
		}
		if(this.list.size() > another.list.size()){
			for (int m = sameDegree; m < this.list.size(); m++){
				sum.list.add(m, this.list.get(m));
			}
		}
		else if (this.list.size() < another.list.size()){
			for (int m= sameDegree; m < another.list.size(); m++){
				sum.list.add(m, another.list.get(m));
			}
		}
		return sum;   // FIXME
	}

	/**
	 * This is the "equals" method that is called by
	 *    assertEquals(...) from your JUnit test code.
	 *    It must be prepared to compare this Polynomial
	 *    with any other kind of Object (obj).  Eclipse
	 *    automatically generated the code for this method,
	 *    after I told it to use the contained list as the basis
	 *    of equality testing.  I have annotated the code to show
	 *    what is going on.
	 */

	public boolean equals(Object obj) {
		// If the two objects are exactly the same object,
		//    we are required to return true.  The == operator
		//    tests whether they are exactly the same object.
		if (this == obj)
			return true;
		// "this" object cannot be null (or we would have
		//    experienced a null-pointer exception by now), but
		//    obj can be null.  We are required to say the two
		//    objects are not the same if obj is null.
		if (obj == null)
			return false;

		//  The two objects must be Polynomials (or better) to
		//     allow meaningful comparison.
		if (!(obj instanceof Polynomial))
			return false;

		// View the obj reference now as the Polynomial we know
		//   it to be.  This works even if obj is a BetterPolynomial.
		Polynomial other = (Polynomial) obj;

		//
		// If we get here, we have two Polynomial objects,
		//   this and other,
		//   and neither is null.  Eclipse generated code
		//   to make sure that the Polynomial's list references
		//   are non-null, but we can prove they are not null
		//   because the constructor sets them to some object.
		//   I cleaned up that code to make this read better.


		// A LinkedList object is programmed to compare itself
		//   against any other LinkedList object by checking
		//   that the elements in each list agree.

		return this.list.equals(other.list);
	}

}
