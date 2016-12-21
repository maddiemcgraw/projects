/* cricket
 *  
 *  CSE 132 - Assignment 3
 *  
 *  Fill this out so we know whose assignment this is.
 *  
 *  Name: Madeline McGraw
 *  WUSTL Key: 431546 // memcgraw
 *  
 *  and if two are partnered together
 *  
 *  Name:
 *  WUSTL Key:
 */

#include <Arduino.h>
#include <Wire.h>


int cricket = 4;
const int FILTER_COUNTS = 4;
int N = FILTER_COUNTS;
float temperatures[FILTER_COUNTS];
int count = 0;
bool chirp;
float voltage;
float tempC;
int period;
int numChirps;


long OnTime = 250;
long OffTime = period + 750;

unsigned long previousMillis = 0;
const long interval = 1000;
unsigned long oldMillis = 0;

int ledState = LOW;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  pinMode(cricket, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:

readTemp();
Chirp();
  
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis>= interval){
    
    previousMillis = currentMillis;
  }
  
  if ((ledState == HIGH) && (currentMillis - previousMillis>= OnTime)){
    ledState = LOW;
    previousMillis = currentMillis;
    digitalWrite(cricket, ledState);
  }
  else if ((ledState == LOW) && (currentMillis - previousMillis >= OffTime)) {
    ledState = HIGH;
    previousMillis = currentMillis;
    digitalWrite(cricket, ledState);    
  }

 
}
 
void readTemp() {
float avg;
float sum;
int temp = analogRead(A0);
voltage = temp *5.0;
voltage /= 1024.0;
 //Serial.print(voltage);
 //Serial.println(" volts");

 tempC = (voltage - 0.5) *100;
 Serial.println();
 //Serial.println(tempC);
 //Serial.print(",  ");
 temperatures[count % FILTER_COUNTS]  = tempC;
 count++;
 for(int m =0; m < FILTER_COUNTS; m++){
  sum +=temperatures[m];
 
 }
 avg = sum/FILTER_COUNTS;
 Serial.println(avg);
  
  
}

 //Serial.println(temperatures[count]);


void Chirp() {
  int numChirps = (7*(tempC-10))+40;
  int period = 1/numChirps;
  //Serial.print(period);
}



