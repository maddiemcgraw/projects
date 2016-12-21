package loopinvariants;

public class Mpy {

	public static int mpy(int a, int b) {

		// 1. Find a useful loop invariant
		// 2. Use that, and the termination condition of the loop
		//    to prove the loop has the desired property

		int ans = 0;
		int i = 0;
		
		System.out.println("ans\ti\ta\tb");

		// Loop invariant:   ans = a * i   (here, = means math equals)
		// *  (check, yes it is true)
		while(i != b){
			System.out.println(ans+"\t"+i+"\t"+a+"\t"+b);
			// If the loop invariant holds here ....
			//  namely, that ans = a * i
			// *
			i = i + 1;      //  i'   =   i + 1
			ans = ans + a;  //  ans' =   ans + a
			// Proof:
			//  Given   ans  = a * i
			//          i'   = i + 1
			//          ans' = ans + a
			//  From that, we must show ans' = a * i'
			//  Proof:
			//    ans'  =  (a*i)  + a
			//    ans'  = a * (i+1)
			//    ans'  = a * i'
			// QED
			// ... then it also holds here
			//      namely, that ans' = a * i'
			//     when we get to the top of the loop, ans' becomes ans
			//                                         i'   becomes i
			//*
		}
		// *
		// For now suppose Loop Invariant is ans = a * i
		// Loop termination condition is always the complement (opposite)
		//   of the loop predicate
		// Loop termination is:   i = b
		//  Plug that into the Loop Invariant, substituting for i
		//  to obtain, that end of loop:
		//
		//  ans = a * b
		
		// Now let's go back and prove that the loop invariant holds
		//   within the loop at the (*) locations

		return ans;
	}

	public static void main(String[] args) {
		int ans = mpy(3,5);
		System.out.println("mpy of 3 and 5 is " + ans);
	}

}
