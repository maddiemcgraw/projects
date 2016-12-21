package assign9.FitBit;
import assign9.FitBit.ViewInputStream;

import sedgewick.StdDraw;

import java.awt.Font;
import java.io.*;
import java.util.*;

import studio4.SerialComm;

public class GraphValues{
	final private ViewInputStream vis;
	final private DataInputStream dis;

	public GraphValues(InputStream in) {
		vis = new ViewInputStream(in);
		dis = new DataInputStream(vis);
	}

	enum Protocol {
		Key,  //State reading key
		Magic, //State waiting to read magic number
		Debug, //State sending debug message
		Error, //State sending error message
		uTemp, //State sending unfiltered temperature stamp
		fTemp, //State sending filtered temperature data
		zVal, //State sending step data
		SleepTime, //State sending time spent asleep data
		TimeRun, //State sending total time data
		StepMode,
		SleepMode,
		ResetStep
	};
	
	List<Double> zVals = new ArrayList<Double>();
	List<Double> timeVals = new ArrayList<Double>();
	List<Double> peakVals = new ArrayList<Double>();
	List<Double> peakTimes = new ArrayList<Double>();
	double uTempPrint = 0.0;
	double fTempPrint = 0.0;
	double zValGraph = 0.0;
	int SleepTimePrint = 0;
	double TimeRunGraph = 0.0;
	int Steps = 0;
	int mode;
	
	Protocol current = Protocol.Magic; //Initial case set to wait for magic number
	
	public void read() throws IOException {
		while (true){
			while(dis.available()>0){
				Protocol next = current;
				//System.out.println("start switch");
				switch(next){
				case Magic:
					int read = dis.read();
					if (read == 0x21){
						next = Protocol.Key;
					}
					else{
						next = Protocol.Magic;
					}
					break;
				case Key:
					int type = dis.read();
					if (type == 0x30){
						next = Protocol.Debug;
					}
					else if (type == 0x31){
						next = Protocol.Error;
					}
					else if (type == 0x32){
						next = Protocol.uTemp;
					}
					else if (type == 0x33){
						next = Protocol.fTemp;
					}
					else if (type == 0x34){
						next = Protocol.zVal;
					}
					else if (type == 0x35){
						next = Protocol.SleepTime;
					}
					else if (type == 0x36){
						next = Protocol.TimeRun;
					}
					else if (type == 0x37){
						next = Protocol.StepMode;
					}
					else if (type == 0x38){
						next = Protocol.SleepMode;
					}
					else if (type == 0x39){
						next = Protocol.ResetStep;
					}
					else{
						next = Protocol.Error;
					}
					break;
				case Debug:
					String DebugMess = dis.readUTF();
					System.out.println(DebugMess);
					next = Protocol.Magic;
					break;
				case Error:
					System.out.println("ERROR");
					next = Protocol.Magic;
					break;
				case uTemp:
					float uTemp_read = dis.readFloat();
					uTempPrint = (double)uTemp_read;
					//System.out.println("Unfiltered Temperature= " + uTemp_read);
					next = Protocol.Magic;
					break;
				case fTemp:
					float fTemp_read = dis.readFloat();
					fTempPrint = (double)fTemp_read;
					//System.out.println("Filtered Temperature= " + fTemp_read);
					next = Protocol.Magic;
					break;
				case SleepTime:
					int TimeAsleep = dis.readInt();
					SleepTimePrint = TimeAsleep;
					//System.out.println("Time Asleep= " + TimeAsleep);
					next = Protocol.Magic;
					break;
				case TimeRun:
					int TotalTime = dis.readInt();
					TimeRunGraph = (double)TotalTime;
					timeVals.add(TimeRunGraph);
					//System.out.println("timeVals= {" + timeVals + "};      timeVals length= "+timeVals.size());
					next = Protocol.Magic;
					break;
				case zVal:
					float zVal_read = dis.readFloat();
					zValGraph = (double)zVal_read;
					zVals.add(zValGraph);
					//System.out.println("zVals= {" + zVals + "};      zVals length= "+zVals.size());
					next = Protocol.Magic;
					break;
				case StepMode:
					mode = 0;
					this.graphStepMode();
					next = Protocol.Magic;
					break;
				case SleepMode:
					mode = 1;
					graphSleepMode();
					next = Protocol.Magic;
					break;
				case ResetStep:
					Steps = 0;
					next = Protocol.Magic;
					break;
				}
				current = next;
				break;
			}
		}
	}
	
	public void graphStepMode(){
		int Length = timeVals.size();
		StdDraw.clear();
		Font font = new Font ("Ariel", Font.PLAIN, 12);
		if (Length<180){
			double Timemax = Math.round(timeVals.get(Length-1)/1000.0);
			double TimeHour = Timemax/360.0;
			double StepRate = Steps/TimeHour;
			StdDraw.setXscale(-10, 65);
			StdDraw.setYscale(-3.0, 3.0);
			StdDraw.enableDoubleBuffering();
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(font);
			StdDraw.line(0.0, 0.0, 45, 0.0);
			StdDraw.line(0.0, -2.5, 0.0, 2.5);
			StdDraw.text(-5.0, 0.0, "Acceleration (g)", 90.0);
			StdDraw.text(53.0, 0.0, "Time (sec)");
			StdDraw.text(45.0, -1.0, "Steps Taken= "+ Integer.toString(Steps));
			StdDraw.text(45.0, -1.5, "Step Rate= " + Double.toString(StepRate) + " steps/hr");
			StdDraw.text(45.0, -2.0, "Temperature= " + Double.toString(fTempPrint) + " celsius");
			StdDraw.text(45.0, -2.5, "Total Time Alseep= " + Double.toString(SleepTimePrint/1000.0) +" sec");
			StdDraw.line(5, 0.1, 5, -0.1);
			StdDraw.text(5, -0.2, "5");
			StdDraw.line(10, 0.1, 10, -0.1);
			StdDraw.text(10, -0.2, "10");
			StdDraw.line(15, 0.1, 15, -0.1);
			StdDraw.text(15, -0.2, "15");
			StdDraw.line(20, 0.1, 20, -0.1);
			StdDraw.text(20, -0.2, "20");
			StdDraw.line(25, 0.1, 25, -0.1);
			StdDraw.text(25, -0.2, "25");
			StdDraw.line(30, 0.1, 30, -0.1);
			StdDraw.text(30, -0.2, "30");
			StdDraw.line(35, 0.1, 35, -0.1);
			StdDraw.text(35, -0.2, "35");
			StdDraw.line(40, 0.1, 40, -0.1);
			StdDraw.text(40, -0.2, "40");
			StdDraw.line(45, 0.1, 45, -0.1);
			StdDraw.text(45, -0.2, "45");
			StdDraw.line(-0.5, -2.0, 0.5, -2.0);
			StdDraw.text(-2.5, -2.0, "-2.0");
			StdDraw.line(-0.5, -1.0, 0.5, -1.0);
			StdDraw.text(-2.5, -1.0, "-1.0");
			StdDraw.line(-0.5, 1.0, 0.5, 1.0);
			StdDraw.text(-2.5, 1.0, "1.0");
			StdDraw.line(-0.5, 2.0, 0.5, 2.0);
			StdDraw.text(-2.5, 2.0, "2.0");
			for (int i=0; i<(Length-1); i++){
				double timeStart = timeVals.get(i)/1000.0;
				double timeEnd = timeVals.get(i+1)/1000.0;
				double zStart = zVals.get(i);
				double zEnd = zVals.get(i+1);
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(timeStart, zStart, timeEnd, zEnd);
				//StdDraw.pause (500);
			}
			if (Length>=3){
				double zDiffa = zVals.get(Length-2)-zVals.get(Length-3);
				double zDiffb = zVals.get(Length-2)-zVals.get(Length-1);
				if ((zDiffa>0.17) && (zDiffb>0.17)){
					Steps++;
					peakVals.add(zVals.get(Length-2));
					peakTimes.add(timeVals.get(Length-2));
					System.out.println("Steps: " +Steps);
				}
			}
			int peakLength = peakVals.size();
			for (int i=0; i<peakLength; i++){
				double peakTime = peakTimes.get(i)/1000.0;
				double peakVal = peakVals.get(i);
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(StdDraw.MAGENTA);
				StdDraw.point(peakTime, peakVal);
			}
			StdDraw.show();
		}
		if (Length>=180){
			double xmax = Math.round(timeVals.get(Length-1)/1000.0);
			double xmin = xmax - 45.0;
			double Timemax = Math.round(timeVals.get(Length-1)/1000.0);
			double TimeHour = Timemax/360.0;
			double StepRate = Steps/TimeHour;
			StdDraw.setXscale((xmin-10), (xmax+20));
			StdDraw.setYscale(-3.0, 3.0);
			StdDraw.enableDoubleBuffering();
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(font);
			StdDraw.line(xmin, 0.0, xmax, 0.0);
			StdDraw.line(xmin, -2.5, xmin, 2.5);
			StdDraw.text((xmin-5.0), 0.0, "Acceleration (g)", 90.0);
			StdDraw.text((xmax+8.0), 0.0, "Time (sec)");
			StdDraw.text(xmax, -1.0, "Steps Taken= "+ Integer.toString(Steps));
			StdDraw.text(xmax, -1.5, "Step Rate= " + Double.toString(StepRate) + " steps/hr");
			StdDraw.text(xmax, -2.0, "Temperature= " + Double.toString(fTempPrint) + " celsius");
			StdDraw.text(xmax, -2.5, "Total Time Alseep= " + Double.toString(SleepTimePrint/1000.0) +" sec");
			StdDraw.line((xmin+5), 0.1, (xmin+5), -0.1);
			StdDraw.text((xmin+5), -0.2, Double.toString(xmin+5));
			StdDraw.line((xmin+10), 0.1, (xmin+10), -0.1);
			StdDraw.text((xmin+10), -0.2, Double.toString(xmin+10));
			StdDraw.line((xmin+15), 0.1, (xmin+15), -0.1);
			StdDraw.text((xmin+15), -0.2, Double.toString(xmin+15));
			StdDraw.line((xmin+20), 0.1, (xmin+20), -0.1);
			StdDraw.text((xmin+20), -0.2, Double.toString(xmin+20));
			StdDraw.line((xmin+25), 0.1, (xmin+25), -0.1);
			StdDraw.text((xmin+25), -0.2, Double.toString(xmin+25));
			StdDraw.line((xmin+30), 0.1, (xmin+30), -0.1);
			StdDraw.text((xmin+30), -0.2, Double.toString(xmin+30));
			StdDraw.line((xmin+35), 0.1, (xmin+35), -0.1);
			StdDraw.text((xmin+35), -0.2, Double.toString(xmin+35));
			StdDraw.line((xmin+40), 0.1, (xmin+40), -0.1);
			StdDraw.text((xmin+40), -0.2, Double.toString(xmin+40));
			StdDraw.line((xmin+45), 0.1, (xmin+45), -0.1);
			StdDraw.text((xmin+45), -0.2, Double.toString(xmin+45));
			StdDraw.line((xmin-0.5), -2.0, (xmin+0.5), -2.0);
			StdDraw.text((xmin-2.5), -2.0, "-2.0");
			StdDraw.line((xmin-0.5), -1.0, (xmin+0.5), -1.0);
			StdDraw.text((xmin-2.5), -1.0, "-1.0");
			StdDraw.line((xmin-0.5), 1.0, (xmin+0.5), 1.0);
			StdDraw.text((xmin-2.5), 1.0, "1.0");
			StdDraw.line((xmin-0.5), 2.0, (xmin+0.5), 2.0);
			StdDraw.text((xmin-2.5), 2.0, "2.0");
			for (int i=(Length-180); i<(Length-1); i++){
				double timeStart = timeVals.get(i)/1000.0;
				double timeEnd = timeVals.get(i+1)/1000.0;
				double zStart = zVals.get(i);
				double zEnd = zVals.get(i+1);
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(timeStart, zStart, timeEnd, zEnd);
				//StdDraw.pause (500);
			}
			if (Length>=3){
				double zDiffa = zVals.get(Length-2)-zVals.get(Length-3);
				double zDiffb = zVals.get(Length-2)-zVals.get(Length-1);
				if ((zDiffa>0.17) && (zDiffb>0.17)){
					Steps++;
					peakVals.add(zVals.get(Length-2));
					peakTimes.add(timeVals.get(Length-2));
					System.out.println("Steps: " +Steps);
				}
			}
			int peakLength = peakVals.size();
			for (int i=0; i<peakLength; i++){
				double peakTime = peakTimes.get(i)/1000.0;
				double peakVal = peakVals.get(i);
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(StdDraw.MAGENTA);
				StdDraw.point(peakTime, peakVal);
			}
			StdDraw.show();
		}
		//StdDraw.clear();
		//System.out.println("Steps: " +Steps);
	}
	
	public void graphSleepMode(){
		int Length = timeVals.size();
		StdDraw.clear();
		Font font = new Font ("Ariel", Font.PLAIN, 12);
		if (Length<180){
			double Timemax = Math.round(timeVals.get(Length-1)/1000.0);
			double TimeHour = Timemax/360.0;
			double StepRate = Steps/TimeHour;
			StdDraw.setXscale(-10, 65);
			StdDraw.setYscale(-3.0, 3.0);
			StdDraw.enableDoubleBuffering();
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(font);
			StdDraw.line(0.0, 0.0, 45, 0.0);
			StdDraw.line(0.0, -2.5, 0.0, 2.5);
			StdDraw.text(-5.0, 0.0, "Acceleration (g)", 90.0);
			StdDraw.text(53.0, 0.0, "Time (sec)");
			StdDraw.text(45.0, -1.0, "Steps Taken= "+ Integer.toString(Steps));
			StdDraw.text(45.0, -1.5, "Step Rate= " + Double.toString(StepRate) + " steps/hr");
			StdDraw.text(45.0, -2.0, "Temperature= " + Double.toString(fTempPrint) + " celsius");
			StdDraw.text(45.0, -2.5, "Total Time Alseep= " + Double.toString(SleepTimePrint/1000.0) +" sec");
			StdDraw.line(5, 0.1, 5, -0.1);
			StdDraw.text(5, -0.2, "5");
			StdDraw.line(10, 0.1, 10, -0.1);
			StdDraw.text(10, -0.2, "10");
			StdDraw.line(15, 0.1, 15, -0.1);
			StdDraw.text(15, -0.2, "15");
			StdDraw.line(20, 0.1, 20, -0.1);
			StdDraw.text(20, -0.2, "20");
			StdDraw.line(25, 0.1, 25, -0.1);
			StdDraw.text(25, -0.2, "25");
			StdDraw.line(30, 0.1, 30, -0.1);
			StdDraw.text(30, -0.2, "30");
			StdDraw.line(35, 0.1, 35, -0.1);
			StdDraw.text(35, -0.2, "35");
			StdDraw.line(40, 0.1, 40, -0.1);
			StdDraw.text(40, -0.2, "40");
			StdDraw.line(45, 0.1, 45, -0.1);
			StdDraw.text(45, -0.2, "45");
			StdDraw.line(-0.5, -2.0, 0.5, -2.0);
			StdDraw.text(-2.5, -2.0, "-2.0");
			StdDraw.line(-0.5, -1.0, 0.5, -1.0);
			StdDraw.text(-2.5, -1.0, "-1.0");
			StdDraw.line(-0.5, 1.0, 0.5, 1.0);
			StdDraw.text(-2.5, 1.0, "1.0");
			StdDraw.line(-0.5, 2.0, 0.5, 2.0);
			StdDraw.text(-2.5, 2.0, "2.0");
			for (int i=0; i<(Length-1); i++){
				double timeStart = timeVals.get(i)/1000.0;
				double timeEnd = timeVals.get(i+1)/1000.0;
				double zStart = zVals.get(i);
				double zEnd = zVals.get(i+1);
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(timeStart, zStart, timeEnd, zEnd);
				//StdDraw.pause (500);
			}
			int peakLength = peakVals.size();
			for (int i=0; i<peakLength; i++){
				double peakTime = peakTimes.get(i)/1000.0;
				double peakVal = peakVals.get(i);
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(StdDraw.MAGENTA);
				StdDraw.point(peakTime, peakVal);
			}
			StdDraw.show();
		}
		if (Length>=180){
			double xmax = Math.round(timeVals.get(Length-1)/1000.0);
			double xmin = xmax - 45.0;
			double Timemax = Math.round(timeVals.get(Length-1)/1000.0);
			double TimeHour = Timemax/360.0;
			double StepRate = Steps/TimeHour;
			StdDraw.setXscale((xmin-10), (xmax+20));
			StdDraw.setYscale(-3.0, 3.0);
			StdDraw.enableDoubleBuffering();
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(font);
			StdDraw.line(xmin, 0.0, xmax, 0.0);
			StdDraw.line(xmin, -2.5, xmin, 2.5);
			StdDraw.text((xmin-5.0), 0.0, "Acceleration (g)", 90.0);
			StdDraw.text((xmax+8.0), 0.0, "Time (sec)");
			StdDraw.text(xmax, -1.0, "Steps Taken= "+ Integer.toString(Steps));
			StdDraw.text(xmax, -1.5, "Step Rate= " + Double.toString(StepRate) + " steps/hr");
			StdDraw.text(xmax, -2.0, "Temperature= " + Double.toString(fTempPrint) + " celsius");
			StdDraw.text(xmax, -2.5, "Total Time Alseep= " + Double.toString(SleepTimePrint/1000.0) +" sec");
			StdDraw.line((xmin+5), 0.1, (xmin+5), -0.1);
			StdDraw.text((xmin+5), -0.2, Double.toString(xmin+5));
			StdDraw.line((xmin+10), 0.1, (xmin+10), -0.1);
			StdDraw.text((xmin+10), -0.2, Double.toString(xmin+10));
			StdDraw.line((xmin+15), 0.1, (xmin+15), -0.1);
			StdDraw.text((xmin+15), -0.2, Double.toString(xmin+15));
			StdDraw.line((xmin+20), 0.1, (xmin+20), -0.1);
			StdDraw.text((xmin+20), -0.2, Double.toString(xmin+20));
			StdDraw.line((xmin+25), 0.1, (xmin+25), -0.1);
			StdDraw.text((xmin+25), -0.2, Double.toString(xmin+25));
			StdDraw.line((xmin+30), 0.1, (xmin+30), -0.1);
			StdDraw.text((xmin+30), -0.2, Double.toString(xmin+30));
			StdDraw.line((xmin+35), 0.1, (xmin+35), -0.1);
			StdDraw.text((xmin+35), -0.2, Double.toString(xmin+35));
			StdDraw.line((xmin+40), 0.1, (xmin+40), -0.1);
			StdDraw.text((xmin+40), -0.2, Double.toString(xmin+40));
			StdDraw.line((xmin+45), 0.1, (xmin+45), -0.1);
			StdDraw.text((xmin+45), -0.2, Double.toString(xmin+45));
			StdDraw.line((xmin-0.5), -2.0, (xmin+0.5), -2.0);
			StdDraw.text((xmin-2.5), -2.0, "-2.0");
			StdDraw.line((xmin-0.5), -1.0, (xmin+0.5), -1.0);
			StdDraw.text((xmin-2.5), -1.0, "-1.0");
			StdDraw.line((xmin-0.5), 1.0, (xmin+0.5), 1.0);
			StdDraw.text((xmin-2.5), 1.0, "1.0");
			StdDraw.line((xmin-0.5), 2.0, (xmin+0.5), 2.0);
			StdDraw.text((xmin-2.5), 2.0, "2.0");
			for (int i=(Length-180); i<(Length-1); i++){
				double timeStart = timeVals.get(i)/1000.0;
				double timeEnd = timeVals.get(i+1)/1000.0;
				double zStart = zVals.get(i);
				double zEnd = zVals.get(i+1);
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(timeStart, zStart, timeEnd, zEnd);
				//StdDraw.pause (500);
			}
			int peakLength = peakVals.size();
			for (int i=0; i<peakLength; i++){
				double peakTime = peakTimes.get(i)/1000.0;
				double peakVal = peakVals.get(i);
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(StdDraw.MAGENTA);
				StdDraw.point(peakTime, peakVal);
			}
			StdDraw.show();
		}
		//StdDraw.clear();
		//System.out.println("Steps: " +Steps);
	}
	
	public static void main(String args[]) throws IOException {
		try
		{        	
			SerialComm s = new SerialComm();
			s.connect("/dev/cu.usbserial-DN01JD5K"); // Adjust this to be the right port for your machine
			InputStream in = s.getInputStream();
			GraphValues m = new GraphValues(in);
			m.read();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}