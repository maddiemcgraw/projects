package assignment5.morse;
import java.io.*;
import assignment5.morse.ViewOutputStream;
import studio4.SerialComm;
public class SerialOutput {


	public static void main(String[] args) {
		String ugh = null;
		try {
			SerialComm m = new SerialComm();
			m.connect("/dev/cu.usbserial-DN01JD5K");
			OutputStream out = m.getOutputStream();
			ViewOutputStream vos = new ViewOutputStream(out);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				if (System.in.available() >0){
					String read = in.readLine();
					System.out.println("Read: " + read);
					for (int i=0;i<read.length();i++){
						vos.write(read.charAt(i));
					}}}}
		catch (Exception e) {
			e.printStackTrace();
		}}}


