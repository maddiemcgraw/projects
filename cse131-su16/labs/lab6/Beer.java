package lab6;

public class Beer {

	public static String bottlesOfBeer(int n) {
		if (n <= 1){
			return "1 bottle of beer on the wall, 1 bottle of beer. Take one down and pass it around, no more bottles of beer on the wall.";
		}
		else {
			return n + "bottles of beer on the wall, " + n + " bottles of beer. You take one down and pass it around, " + bottlesOfBeer(n-1) + " on the wall.";
		}
	}
	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		System.out.print(bottlesOfBeer(99));
}
}
