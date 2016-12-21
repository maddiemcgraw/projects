package assign4;

import studio4.SerialComm;
import java.io.*;

public class MsgReceiver {

	final private ViewInputStream vis;
	

		public MsgReceiver(InputStream in) {
			vis = new ViewInputStream(in);
		}
		public void run() throws IOException {
			while(true){
		
			if(vis.available()>0){
			if(vis.read() == 0x21){
				int key =vis.read();
				if(key == 0x31){
					System.out.println("Something is wrong.");
				}
				if(key == 0x32){
					int time = (vis.read()) + (vis.read()<< 8) + (vis.read()<<16) + (vis.read()<< 24);
					System.out.println("Time: " + time);
				}
				if(key == 0x33){
					int pot = (vis.read()) + (vis.read()<< 8);
					System.out.println("Potentiometer: " + pot);
				}
				if(key == 0x34){
					int unfilt = (vis.read()) + (vis.read()<< 8);
					System.out.println("A/D Counts: " + unfilt);
				}
				if(key == 0x35){
					int convert = (vis.read()) + (vis.read()<< 8) + (vis.read()<< 16) + (vis.read()<< 24);
					System.out.println("Converted: " + convert);
				}
				if(key == 0x36){
					int filt = (vis.read()) + (vis.read()<< 8) + (vis.read()<< 16) + (vis.read() << 24);
					System.out.println("Filtered: " + filt);
				}
		
				
			}
		}
		}
		}
		
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try
        {        	
            SerialComm s = new SerialComm();
            s.connect("COM4"); // Adjust this to be the right port for your machine
            InputStream in = s.getInputStream();
            MsgReceiver msgr = new MsgReceiver(in);
            msgr.run();
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}
