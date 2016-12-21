package lab9;

public class ListItem {
	//
	// Important:  Do NOT make these instance variables private
	// Our testing needs to be able to access them, so leave their
	//   declarations as you see them below.
	//
	final int number;
	ListItem next;

	/**
	 * Creates a single list item.
	 * @param number the value to be held in the item
	 * @param next a reference to the next item in the list
	 */
	ListItem(int number, ListItem next) {
		this.number = number;
		this.next   = next;
	}

	/**
	 * Return a copy of this list using recursion.  No
	 * credit if you use any iteration!  All existing lists should remain
	 * intact -- this method must not mutate anything.
	 * @return
	 */
	public ListItem duplicate() {
		ListItem dup = new ListItem(this.number, this.next);
		if(this.next != null) {
			dup.next = this.next.duplicate();
		}
		return dup;
	}

	/**
	 * Recursively compute the number of elements in the list. No
	 * credit if you use any iteration!  All existing lists should remain
	 * intact.
	 * @return
	 */
	public int length() {
		if(this.next == null){
			return 1;
		}
		return 1 + this.next.length(); // FIXME
	}

	/**
	 * Return a new list, like duplicate(), but every element
	 * appears n times instead of once.  See the web page for details.
	 * You must do this method iteratively.  No credit
	 * if you use any recursion!
	 * @param n a positive (never 0) number specifying how many times to copy each element
	 * @return
	 */

	public ListItem stretch(int n) {
		ListItem move = this.duplicate();
		ListItem first = new ListItem(this.number, this.next);
		ListItem last = first;
		for (int m = 0; m < this.length(); m++){
			for (int b = 0; b < n; b++){
				last.next = new ListItem(move.number, null);
				last = last.next;
			}
			move = move.next;
		}
		return first.next;
	}

	/**
	 * Return the first ListItem, looking from "this" forward,
	 * that contains the specified number.  No lists should be
	 * modified as a result of this call.  You may do this recursively
	 * or iteratively, as you like.
	 * @param n
	 * @return
	 */

	public ListItem find(int n) {
		ListItem found = this.duplicate();
		for(int m = 0; m < this.length(); m++){
			if(found.number ==n){
				return found;// FIXME
			}
			found = found.next;
		}
		return null;
	}

	/**
	 * Return the maximum number contained in the list
	 * from this point forward.  No lists should be modified
	 * as a result of this call.  You may do this method recursively
	 * or iteratively,as you like.
	 * @return
	 */

	public int max() {
		ListItem maximum = this.duplicate();
		int large = maximum.number;
		for(int m = 0; m < this.length()-1; m++){
			if(maximum.next.number > large){
				large = maximum.next.number;
			}
			maximum = maximum.next;
		}
		return large;
	}

	/**
	 * Returns a copy of the list beginning at ls, but containing
	 * only elements that are even.
	 * @param ls
	 * @return
	 */
	public static ListItem evenElements(ListItem ls) {
		if(ls == null){
			return null;
		}
		if (ls.next == null){
			if (ls.number%2 == 0){
				return new ListItem(ls.number, null);
			}
			return null;
		}
		else {
			if(ls.number%2 == 0){
				return new ListItem(ls.number, evenElements(ls.next));
			}
			else{
				return evenElements(ls.next);
			}
		}
	}	


	/**
	 * Returns a string representation of the values reachable from
	 * this list item.  Values appear in the same order as the occur in
	 * the linked structure.  Leave this method alone so our testing will work
	 * properly.
	 */
	public String toString() {
		if (next == null)
			return ("" + number);
		else
			return (number + " " + next); // calls next.toString() implicitly
	}

}
