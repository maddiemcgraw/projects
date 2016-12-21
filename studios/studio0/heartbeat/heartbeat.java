package studio0.heartbeat;

public class heartbeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numBeats = 20;
for(int n = 0; n < numBeats; n++){
	System.out.println(n + "sec have elapsed");
	Thread.sleep(1000);
}
	}

}
