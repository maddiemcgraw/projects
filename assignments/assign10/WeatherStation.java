package assign10;

import studio4.SerialComm;
import assign4.ViewInputStream;

import sun.net.*;

import java.io.*;
import java.net.*;

public class WeatherStation {
	final private ViewInputStream vis;
	final private DataInputStream dis;
	public static void main(String[] args) throws Exception {
		// Create a new instance of Weather Station
		WeatherStation station = new WeatherStation();
		station.sendGet();
		// Based on the name of the instance created above, call xx.sendGet().
		// This will test to the function we'll be creating below.
	}
	// HTTP GET request
	private void sendGet() throws Exception {
		// Create a string that contains the URL you created for Lopata Hall in Studio 10
		// Use the URL that DOES NOT have the timestamp included.
		// Since we only need the current data (currently) you can use the API to exclude all of the excess blocks (REQUIRED).
		// Instructions to do that are here: https://darksky.net/dev/docs/forecast
		// Test this new URL by pasting it in your web browser. You should only see the information about the current weather.
		//Lopata Hall
		String L1 = "https://api.darksky.net/forecast/ac1d3612ef84894e0b94050c0be96a24/38.649196,-90.306099?exclude=minutely,hourly,daily,alerts,flags";
		//White House
		String L2 = "https://api.darksky.net/forecast/ac1d3612ef84894e0b94050c0be96a24/38.8977,-77.0365?exclude=minutely,hourly,daily,alerts,flags";;
		//Arch
		String L3 = "https://api.darksky.net/forecast/ac1d3612ef84894e0b94050c0be96a24/38.6247,-90.1848?exclude=minutely,hourly,daily,alerts,flags";;
		// Create a new URL object with the URL string you defined above. Reference: https://docs.oracle.com/javase/7/docs/api/java/net/URL.html
		URL L1URL = new URL(L1);
		URL L2URL = new URL(L2);
		URL L3URL = new URL(L3);
		// Follow the instructions in the URL API to open this connection.
		// Cast this to a HttpURLConnection and save it in a new HttpURLConnection object.
		HttpURLConnection L1HttpURLConnection = (HttpURLConnection)L1URL.openConnection();
		HttpURLConnection L2HttpURLConnection = (HttpURLConnection)L2URL.openConnection();
		HttpURLConnection L3HttpURLConnection = (HttpURLConnection)L3URL.openConnection();
		// Use the HttpURLConnection API to setup the HttpURLConnection you defined above.
		// Reference: https://docs.oracle.com/javase/7/docs/api/java/net/HttpURLConnection.html
		// Set the request method.
		L1HttpURLConnection.setRequestMethod("GET");
		L2HttpURLConnection.setRequestMethod("GET");
		L3HttpURLConnection.setRequestMethod("GET");
		// Set the request property "User-Agent" to the User-Agent you saw in Wireshark when you did the first exercise in studio.
		// Repeat the quick wireshark example if you've forgotten. It should be in the form "xxxxxxx/#.#"
		L1HttpURLConnection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
		L2HttpURLConnection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
		L3HttpURLConnection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
		// To debug, get and print out the response code.
		int L1ResponseCode = L1HttpURLConnection.getResponseCode();
		int L2ResponseCode = L2HttpURLConnection.getResponseCode();
		int L3ResponseCode = L3HttpURLConnection.getResponseCode();
		System.out.println("Test Response= "+ L1ResponseCode);
		System.out.println("Test Response= "+ L2ResponseCode);
		System.out.println("Test Response= "+ L3ResponseCode);
	}
		// The rest of the code should be much more familiar.
		// Create an InputStream that gets the input stream from our HttpURLConnection object.
		WeatherStation(InputStream in) {
			vis = new ViewInputStream(in);
			dis = new DataInputStream(vis);
		}
		// Wrap it in a DataInputStream	
		String received1;
		void ReadURL1(URL L1URL) throws Exception {
			BufferedReader msge1 = new BufferedReader(new InputStreamReader(L1URL.openStream()));
			while ((received1 = msge1.readLine()) != null)
				System.out.println(received1);
			msge1.close();
		}
		String received2;
		void ReadURL2(URL L2URL) throws Exception {
			BufferedReader msge2 = new BufferedReader(new InputStreamReader(L2URL.openStream()));
			while ((received2 = msge2.readLine()) != null)
				System.out.println(received2);
			msge2.close();
		}
		
		String received3;
		void ReadURL3(URL L3URL) throws Exception {
			BufferedReader msge3 = new BufferedReader(new InputStreamReader(L3URL.openStream()));
			while ((received3 = msge3.readLine()) != null)
				System.out.println(received3);
			msge3.close();
		}
		// Read a line and save it in a string



		// Close the InputStream



		// Using string manipulation tools, pull out the string between quotes after "icon:"
		// For example: "summary":"Clear","icon":"clear-day","nearestStormDistance":27
		// You should pull out JUST "clear-day"

		//private static final String report = "https://api.forecast.io/forecast";

		// You will set this char (in a switch statement) to one of the 5 types of weather. (Nothing TODO here)
		char weatherChar = '\0';

		// Create a switch statement based on the string that contains the description (ex. clear-day)
		// The switch statement should be able to handle all 10 of the icon values from the API: https://darksky.net/dev/docs/response
		// If the value is any of the cloudy ones, set weatherChar to C (
		// If the value is fog, set it to F
		// If the value if wind, set it to W
		// If the value is any of the clear ones, set it to S
		// If the value is any type of precipitation, set it to P
		enum Check {
			cloudy,
			fog,
			wind,
			clear,
			precipitation,
		}
		boolean C = false;
		boolean F = false;
		boolean W = false;
		boolean S = false;
		boolean P = false;
		CharSequence c = "cloudy";
		CharSequence f = "fog";
		CharSequence w = "wind";
		CharSequence s = "clear";
		CharSequence rain = "rain";
		CharSequence snow = "snow";
		CharSequence sleet = "sleet";
		Check now = Check.cloudy;
		public void weatherman() throws IOException {
			//will need to change this so we can read from the three locations
			int potread = 0;
			String report;
			int place1 = 0;
			int place2 = 0;
			int place3 = 0;
			
			if(potread == place1){
				report = received1;
			}
			else if(potread == place2){
				report = received2;
			}
			else if(potread == place3){
				report = received3;
			}
			
			while(true){
				while(dis.available()>0){
					Check future = now;
					switch(future){
					case cloudy:
						//String report = whatever is coming in from darksky
						if (report.contains(c) == true){
							C = true;
							weatherChar = 'C';
						} else {
							future = Check.fog;
							C = false;
						}
						break;
					case fog:
						//report = report.
						if (report.contains(f) == true){
							F = true;
							weatherChar = 'F';
						} else {
							future = Check.wind;
							F = false;
						}
						break;
					case wind:
						//String report = (char) dis.read();
						if (report.contains(w) == true){
							W = true;
							weatherChar = 'W';
						} else {
							future = Check.clear;
							W = false;
						}
						break;
					case clear:
						//String report = (char) dis.read();
						if (report.contains(s) == true){
							S = true;
							weatherChar = 'S';
						} else {
							S = false;
							future = Check.precipitation;
						}
						break;
					case precipitation:
						//String report = (char) dis.read();
						if (report.contains(rain) == true || report.contains(snow) == true || report.contains(sleet) == true){
							P = true;
							weatherChar = 'P';
						} else {
							P = false;
							future = Check.cloudy;
						}
						break;
					}
				}
			}
		}
		// Now you're ready to implement this into your past code to send it to the Arduino.
		// You also have to make a couple modifications to handle the switch location requests from Arduino.
		// Choose three locations or more, but make sure one is Lopata Hall.
		public WeatherStation() {
			// TODO Auto-generated constructor stub
		}
		enum Protocol {
			Key,  //State reading key
			Magic, //State waiting to read magic number
			Loc1, //State sending Location 1 update
			Loc2, //State sending Location 2 update
			Loc3, //State sending Location 3 update
			Error //State sending error icon
		};
		Protocol current = Protocol.Magic;
		public void run() throws IOException {
			while(true){
				while (dis.available()>0){
					Protocol next = current;
					//System.out.println("start switch");
					switch(next){
					case Magic:
						int read = dis.read();
						if (read == 0x21){
							next = Protocol.Key;
							//System.out.println("move to key");
						}
						else{
							next = Protocol.Magic;
							//System.out.println("stay at magic");
						}
						break;
					case Key:
						int type = dis.read();
						if (type == 0x31){
							next = Protocol.Loc1;
							//System.out.println("move to debug");
							//break;
						}
						else if (type == 0x32){
							next = Protocol.Loc2;
							//System.out.println("move to error");
							//break;
						}
						else if (type == 0x33){
							next = Protocol.Loc3;
							//System.out.println("move to time");
							//break;
						}
						else{
							next = Protocol.Error;
							//break;
						}
						break;
					case Loc1:
					}
					current = next;
					break;
				}
			}

		}
	}
