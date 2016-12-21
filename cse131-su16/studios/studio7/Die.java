package studio7;

public class Die {

	//n sides
	private int n, face;

	public Die(int n){
		this.n = n;
	}
	//random throw 1-n
	public int getFace() {
		face = (int)(Math.random()*n) + 1;
		return face;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Die rolled = new Die(7);
		rolled.getFace();
		System.out.println("The die has " + rolled.n + " sides and landed on " + rolled.getFace() + ".");
	}

}
