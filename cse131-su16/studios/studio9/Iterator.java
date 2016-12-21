package studio9;

public class Iterator<Integer> {

	Integer M;
	Iterator<Integer> newbie;
	ListItem head;
	
	
	public Iterator(Integer M) {
		Iterator <Integer> p = null;
		this.M = M;
	}
	
	public boolean hasNext(int item) {
		if (this.head == null)
			return false;
		if (this.head.getValue() == item){
			this.head = this.head.getNext();
			return true;
		}
		return false;
	}
	public void next(int item) {
		ListItem p = this.head;
		while (p.getNext() != null) {
			p = p.getNext();
		}
	}
	
	public void remove() {
	}
	
	public void reverse() {
	}

		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

	}
