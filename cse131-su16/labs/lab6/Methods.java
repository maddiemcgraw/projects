package lab6;


public class Methods {

		public static int f(int x) {
			if (x > 100) {
				return x-10;
			}
			if (x <= 100) {
				return f(f(x+11));
			}
			return 0;
		}
		public static int g(int x, int y) {
			if (x == 0){
				return y+1;
			}
			if (x>0 && y == 0) {
				return (int) g(x-1, 1);
			}
			if (x > 0 && y > 0) {
				return (int) g(x-1, g(x, y-1));
			}
			return 0;
		}
		public static void main(String[] args){
			System.out.println(f(99));
			System.out.println(f(87));
			System.out.println(g(1,0));
			System.out.println(g(1,2));
			System.out.println(g(2,2));
			System.out.println(g(4, 5));
		}
	}


