package lab5;

public class Lab5Methods {

	
	/**
	 * 
	 * @param n
	 * @return n+(n-2)+...
	 */
	public static int sumDownBy2(int n) {
		if (n >=0) {
			int sum = 0;
			for (int m = n; m > 0; m = m-2) {
				sum = sum + m;
			} 
			return sum;
		}
		else {
			return 0;
		}
	}
	/**
	 * 
	 * @param n
	 * @return 1+(1/2)+...+(1/n)
	 */
	public static double harmonicSum(int n){
		double sum = 0;
		if (n == 1){
			return 1;
		}
		if (n > 1){
			for (int b = 1; b <= n; b++) {
				sum += (double) 1/b;
			}
		}
		return sum*1.000;
	}
/**
 * 
 * @param k
 * @return 1+(1/2)+(1/4)+...+(1/n)
 */
	public static double geometricSum(int k){
		double ans = 0;
			if (k==0){
			ans = 1;
		}
		if (k > 0) {
			for (int g = k; g>=0; g--) {
				ans += 1/(Math.pow(2, g));
			}
		}
		
		//ans = Math.round(ans*100000)/100000;
		return ans*1.00;
	}

/**
 * 
 * @param j
 * @param k
 * @return j*k
 */
	public static int multPos(int j, int k) {
		int ans = 0;
		if (j>=0 && k>=0){
			for (int h = 0; h <k; h++) {
				ans = ans +j;
			}
		}
		return ans;

	}
/**
 * 
 * @param j
 * @param k
 * @return j^k
 */
	public static int mult(int j, int k) {

		int ans;
		boolean Posj = true;
		boolean Posk = true;
		if (j<0 || k<0){
			if (j<0){
				j = j*-1;
				Posj = false;
			}
			if (k<0){
				k = k*-1;
				Posk = false;
			}
			ans = multPos(j,k);
			if (Posk == false){
				ans = ans*(-1);
			}
			if (Posj == false){
				ans = ans*(-1);
			}
		}
		else {
			ans = multPos(j,k);
		}
		return ans;

	}
/**
 * 
 * @param n
 * @param k
 * @return n^k
 */
	public static int expt(int n, int k) {
		
		boolean Posn = true;
		if (n < 0) {
			n = n*(-1);
			Posn = false;
		}
		int ans = n;
		if (k>=0){
			if (k == 0){
				return 1;
			}
				for (int y = 1; y < k; y++) {
					ans = ans * n;
				}
				if (Posn != true && k%2!=0) {
					ans = ans *(-1);
				}
			}
		
			return ans;
	}
}
