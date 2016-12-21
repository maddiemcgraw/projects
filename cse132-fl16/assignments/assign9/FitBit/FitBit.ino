/* Fitbit- Part 2
 *  
 *  CSE 132 - Assignment 9
 *  
 *  Fill this out so we know whose assignment this is.
 *  
 *  Name: Anna Boerwinkle
 *  WUStL Key: 429055
 *  
 *  and if two are partnered together
 *  
 *  Name: Maddie McGraw
 *  WUStL Key: 431546
 */

#include <Wire.h>
#include <SparkFun_MMA8452Q.h>

MMA8452Q accel;

const int ModeButton = 3; //Dig
const int ResetButton = 4; //Dig
const int stepLED = 7; //Dig
const int sleepLED = 13; //Dig
const int tempSens = 0; //Analog

const int Magic = 0x21;
const int Debug = 0x30;
const int Error = 0x31;
const int utemp = 0x32;
const int ftemp = 0x33;
const int upVal = 0x34;
const int Sleeptime = 0x35;
const int timeRun = 0x36;
const int StepMode = 0x37;
const int SleepMode = 0x38;
const int ResetStep = 0x39;

float upRead[3] = {};

unsigned long secAsleep = 0;
unsigned long accum = 0;
unsigned long lapse = 0;
bool mode = 0; //0=Steps, 1=Sleep
int reset = 0;

const int FILTER_COUNTS = 5;
float temps[FILTER_COUNTS];
int count = 0;
int raw = 0;
float tempMeas = 0.0;
float unfilttemp = 0.0;
float filttemp = 0.0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  analogReference(DEFAULT);
  accel.init();
  pinMode(ModeButton, INPUT_PULLUP);
  pinMode(ResetButton, INPUT_PULLUP);
  pinMode(stepLED, OUTPUT);
  pinMode(sleepLED, OUTPUT);
  if(accel.available()){
    accel.read();
    upRead[1] = accel.cz;
  }

}

void loop() {
  // put your main code here, to run repeatedly:
  raw = analogRead(tempSens);
  tempMeas = (float)raw * 0.005;
  unfilttemp = 100.0 * tempMeas - 50.0;
  temps[count%FILTER_COUNTS] = unfilttemp;
  filttemp = (temps[0]+temps[1]+temps[2]+temps[3]+temps[4])/5.0;
  count += 1;
  reset = digitalRead(ResetButton);

  //read button
  if (digitalRead(ModeButton)==0){
    mode = !mode;
  }
  
  if (reset==0){
    digitalWrite(stepLED, HIGH);
    digitalWrite(sleepLED, LOW);
    Serial.write(Magic);
    Serial.write(ResetStep);
  }
  
  unsigned long sec = millis();
  if(accel.available()){
    accel.read();
    upRead[2] = accel.cz;
  }
  
  Sendtime(sec);
  SendupVals(upRead[2]);
  
  if (mode==0){
    digitalWrite(stepLED, HIGH);
    digitalWrite(sleepLED, LOW);
    Serial.write(Magic);
    Serial.write(StepMode);
  }
  
  if (mode==1){
    digitalWrite(stepLED, LOW);
    digitalWrite(sleepLED, HIGH);
    float upDiff1 = upRead[1]-upRead[0];
    float upDiff2 = upRead[2]-upRead[1];
    if (abs(upDiff1)<=0.05 && abs(upDiff2)<=0.05){
      secAsleep = secAsleep + (millis()-lapse);
    }
    
    else{
      secAsleep = secAsleep;
    }
    
    Serial.write(Magic);
    Serial.write(SleepMode);
  }
  
  if (sec-accum >= 500){
    Sendtemp(unfilttemp, filttemp);
    SendSleeptime(secAsleep);
    accum += 500;
  }
  
  upRead[0]=upRead[1];
  upRead[1]=upRead[2];
  lapse = millis();
  delay(250);
}

void SendupVals (float up) {
  unsigned long Z;
  Z = *(unsigned long*) &up;
  Serial.write(Magic);
  Serial.write(upVal);
  Serial.write(Z >> 24);
  Serial.write(Z >> 16);
  Serial.write(Z >> 8);
  Serial.write(Z);
}

void Sendtime (unsigned long t){
  //Send total time running
  Serial.write(Magic);
  Serial.write(timeRun);
  Serial.write(t >> 24);
  Serial.write(t >> 16);
  Serial.write(t >> 8);
  Serial.write(t);
}

void SendSleeptime (unsigned long sleep){
  Serial.write(Magic);
  Serial.write(Sleeptime);
  Serial.write(sleep >> 24);
  Serial.write(sleep >> 16);
  Serial.write(sleep >> 8);
  Serial.write(sleep);
}

void Sendtemp(float ut, float ft){
  //Send unfiltered temperature
    unsigned long unfiltBits;
    unfiltBits = *(unsigned long*) &ut;
    Serial.write(Magic);
    Serial.write(utemp);
    Serial.write(unfiltBits >> 24);
    Serial.write(unfiltBits >> 16);
    Serial.write(unfiltBits >> 8);
    Serial.write(unfiltBits);
  //Send filtered temperature
    unsigned long filtBits;
    filtBits = *(unsigned long*) &ft;
    Serial.write(Magic);
    Serial.write(ftemp);
    Serial.write(filtBits >> 24);
    Serial.write(filtBits >> 16);
    Serial.write(filtBits >> 8);
    Serial.write(filtBits);
}

void SendDebug(char* debugMess){
  Serial.write(Magic);
  Serial.write(Debug);
  int m = strlen(debugMess);
  Serial.write(m);
  Serial.write(debugMess);
}

void SendError(char* errorMess){
  Serial.write(Magic);
  Serial.write(Error);
  int m = strlen(errorMess);
  Serial.write(m);
  Serial.write(errorMess);
}
