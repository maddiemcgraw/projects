package studio8;

public class Time {

	private final int hour;
	private final int minute;
	private boolean militaryTime;
	private boolean anteMeridiem;

	public Time(int hour, int minute, boolean militaryTime) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.militaryTime = militaryTime;
		this.anteMeridiem = anteMeridiem;
	}

	
	public String toString() {
		if (militaryTime = true) {
			return "Time: " + hour + ":" + minute + " (Military Time)";
		}
		else {
			if (anteMeridiem = true){
				return "Time: " + hour + ":" + minute + " AM";
			}
			else {
				return "Time: " + hour + ":" + minute + " PM";
			}
		}
	}


		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + minute;
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
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (minute != other.minute)
			return false;
		return true;
	}


		public static void main(String[] args) {
		}

	}
