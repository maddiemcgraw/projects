package studio8;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSteps {

	public static void main (String args[]) throws IOException {
		List<Double> xVals = new ArrayList<Double>();
		List<Double> yVals = new ArrayList<Double>();
		List<Double> zVals = new ArrayList<Double>();
		int xPeaks = 0;
		int yPeaks = 0;
		int zPeaks = 0;
		//FileReader fr = new FileReader("/Users/AnnaBoerwinkle/git/cse132-fl16-anna.boerwinkle/studios/studio8/steps/data/Studio8.csv");
		String line = null;
		List<String> data = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/madeline/git/cse132-fl16-memcgraw/studios/studio8/data.csv"));
		while ((line = bufferedReader.readLine()) != null){
			Collections.addAll(data, line.split(","));
		}
		bufferedReader.close();
		for (int i=0; i<data.size(); i+=3){
			double xVal = Double.parseDouble(data.get(i));
			xVals.add(xVal);
		}
		for (int j=1; j<data.size(); j+=3){
			double yVal = Double.parseDouble(data.get(j));
			yVals.add(yVal);
		}
		for (int k=2; k<data.size(); k+=3){
			double zVal = Double.parseDouble(data.get(k));
			zVals.add(zVal);
		}
//		System.out.println("xVals= "+xVals);
//		System.out.println("yVals= "+yVals);
//		System.out.println("zVals= "+zVals);
		for (int a=1; a<(xVals.size()-1); a++){
			if (xVals.get(a)>xVals.get(a-1) && xVals.get(a)>xVals.get(a+1)){
				xPeaks++;
			}
		}
		for (int b=1; b<(yVals.size()-1); b++){
			if (yVals.get(b)>yVals.get(b-1) && yVals.get(b)>yVals.get(b+1)){
				yPeaks++;
			}
		}
		for (int c=1; c<(zVals.size()-1); c++){
			if (zVals.get(c)>zVals.get(c-1) && zVals.get(c)>zVals.get(c+1)){
				zPeaks++;
			}
		}
		System.out.println("xPeaks= "+xPeaks);
		System.out.println("yPeaks= "+yPeaks);
		System.out.println("zPeaks= "+zPeaks);
	}
	
	//public Separate ()
}

