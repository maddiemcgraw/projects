package studio8;

import java.util.HashSet;
import java.util.LinkedList;

public class Date {
	
	private final int month;
	private final int day;
	private final int year;
	private final boolean holiday;
	
	
	public Date(int month, int day, int year, boolean holiday) {
		super();
		this.month = month;
		this.day = day;
		this.year = year;
		this.holiday = holiday;
	}


	@Override
	public String toString() {
		if (holiday == true){
			return month + "/" + day + "/" + year + ". This is a holiday!";
		}
		else {
			return month + "/" + day + "/" + year + ". Sorry, not a holiday!";
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		return true;
	}


	public static void main(String[] args) {
		Date d1 = new Date(9, 12,1995, true);
		Date d2 = new Date(9, 12, 2016, true);

		d1.equals(d2);
		
		HashSet<Date> set = new HashSet<Date>();
		set.add(d1);
		set.add(d2);
		set.add(d1);
		System.out.println(set);
		
	}

}
