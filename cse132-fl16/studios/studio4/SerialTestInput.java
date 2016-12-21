package studio4;

import java.io.InputStream;
import studio4.SerialComm;

public class SerialTestInput {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SerialComm port = new SerialComm();
		port.connect("/dev/cu.usbserial-DN01JD5K");

		InputStream stream = port.getInputStream();
		stream.read();
		int streamRead = 0;
		while(true){
		if(stream.available() > 0){
			streamRead = (char) stream.read();
			System.out.println(streamRead);
		}
		}
		//String text = toString(streamRead);
	}
}
