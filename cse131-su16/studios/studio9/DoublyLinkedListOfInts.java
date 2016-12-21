package studio9;

import java.util.Iterator;

public class DoublyLinkedListOfInts implements IntList {

	ListItem head;
	ListItem tail;
	ListItem prev;
	ListItem next;
	int size;

	public DoublyLinkedListOfInts(){
		int i = 0;
		ListItem sentinel = new ListItem(i, null);

		this.head = sentinel;
		this.tail = tail;
		this.prev = prev;
		this.next = next;
	}



	@Override
	public void addFirst(int item) {
		// TODO Auto-generated method stub
		ListItem newbie = new ListItem(item, this.head.getNext());
		this.head.setNext(newbie);
	}

	@Override
	public void addLast(int item) {
		// TODO Auto-generated method stub
		ListItem tail = this.head;
		while (tail.getNext() != null) {
			tail = tail.getNext();
		}
		tail.setNext(new ListItem(item,null));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int ans = 0;
		for (ListItem p = this.head.getNext(); p != null; p = p.getNext()) {
			ans = ans + 1;
		}
		return ans;
	}

	@Override
	public int indexOf(int item) {
		// TODO Auto-generated method stub
		int ans = 0;
		for (ListItem next = this.head.getNext(); next != null; next = next.getNext()) {
			if (next.getValue() == item)
				return ans;
			++ans;
		}
		return -1;
	}

	@Override
	public boolean remove(int item) {
		// TODO Auto-generated method stub
		for (ListItem next = this.head; next.getNext() != null; next = next.getNext()) {
			if (next.getNext().getValue() == item) {
				// the thing we want to remove is one to the right of p
				//
				next.setNext(next.getNext().getNext());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size() == 0;
	}

	public String toString() {
		String ans = "[";
		for (ListItem p = this.head.getNext(); p != null; p = p.getNext()) {
			ans = ans + p.getValue() + " ";
		}
		ans = ans + "]";
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoublyLinkedListOfInts m1 = new DoublyLinkedListOfInts();
		m1.addLast(24); System.out.println(m1);
		m1.addFirst(3); System.out.println(m1);
		System.out.println(m1);

		DoublyLinkedListOfInts m2 = new DoublyLinkedListOfInts();
		for (int m = 0; m < 1000; m++) {
			if (Math.random() < 0.5)
				m2.addLast(m);
			else
				m2.addFirst(m);
		}

		System.out.println(m2);
	}

		@Override
		public Iterator<Integer> iterator() {
			return null;
		}
	
}