package studio4;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import cse131.ArgsProcessor;
import sedgewick.StdAudio;
import sedgewick.StdDraw;
import sedgewick.StdIn;

public class Flag {


	public static void main(String[] args) {
		//
		//  Add code for your studio below here.
		//
		StdDraw.setCanvasSize(500, 300);

		StdDraw.setPenRadius(.015);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.square(0.5, 0.5, 0.5);

		StdDraw.setPenColor(Color.cyan);
		StdDraw.filledSquare(0.5, 0.5, 0.5);

		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(.5, .5, .5, .3);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledRectangle(.5, .5, .5, .25);

		StdDraw.setPenRadius(.015);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledCircle(0.5, 0.5, 0.05);
		StdDraw.filledCircle(0.25, 0.5, 0.05);
		StdDraw.filledCircle(0.75, 0.5, 0.05);

		StdDraw.setFont();
		StdDraw.setPenColor(Color.MAGENTA);
		
		double m = 0;
		double n = 1;
		int w =0;
		
		for (w =0; w<=10; w++) {
			if (w==10){
				break;
			}
			else {
			StdDraw.show(200);
			StdDraw.textLeft(m, n, "Nationa");
			m += .1;
			n -= .1;
		}
		}
		
			
			try {
				System.setIn(new FileInputStream("music/Nationa_Studio4.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArgsProcessor.useStdInput("music");
	        // repeat as long as there are more integers to read in
	        while (!StdIn.isEmpty()) {

	            // read in the pitch, where 0 = Concert A (A4)
	            int pitch = StdIn.readInt();

	            // read in duration in seconds
	            double duration = StdIn.readDouble();

	            // build sine wave with desired frequency
	            double hz = 440 * Math.pow(2, pitch / 12.0);
	            int N = (int) (StdAudio.SAMPLE_RATE * duration);
	            double[] a = new double[N+1];
	            for (int i = 0; i <= N; i++) {
	                a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
	            }

	            // play it using standard audio
	            StdAudio.play(a);
	        }



	}

}
