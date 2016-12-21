#include <Arduino.h>
#include <Wire.h>

//Fill in the values below with the pins that you chose to use
const int potPin = A0;     //turn to switch cycles
const int buttonPin = 7;   //push to start

const int HOT_PIN   =  4;
const int COLD_PIN  =  3;
const int DRY_PIN   =  2;
const int LOCK_PIN  =  13;

enum State {
  IDL,
  ECO,      //cold 5 sec, and dryer 2 sec
  DEL,      //hot 7 sec, dryer 7 sec
  SUP,      //hot 7 sec, hot and cold 7 sec, dryer 7 sec
};

State counterCycle = IDL;

bool bit2;
bool bit3;
bool bit4;
bool bit7;

unsigned long previousMillis = 0;
const long interval = 100;

int buttonPushCounter = 0;
int buttonState = 0;
int lastButtonState = 0;


void setup() {
  Serial.begin(9600);

  pinMode(7, INPUT_PULLUP);
  pinMode(4, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(2, OUTPUT);
  pinMode(13, OUTPUT);



}

int sensorValue = analogRead(A0);
int oldSensorValue = 0;

const int ledPin = 13;

void loop() {
counterCycle = nextCycle(counterCycle);
  
}

int locked = 0;


State nextCycle(State cycle) {
//   locked = digitalRead(buttonPin);
//   sensorValue = analogRead(potPin);
  switch (cycle) {
      case IDL:
      locked = 0;
   
      bit4 = 0;
      bit3 = 0;
      bit2 = 0;

      digitalWrite(4, LOW);
      digitalWrite(3, LOW);
      digitalWrite(2, LOW);
      digitalWrite(13, LOW);
      
      locked = digitalRead(buttonPin);
      sensorValue = analogRead(potPin);
      
       if(locked == LOW) {
      if(sensorValue >= 0 && sensorValue < 341) {
        cycle = ECO;
      }
      else if(sensorValue >= 341 && sensorValue < 682) {
        cycle = DEL;
      }
      else if(sensorValue >= 682 && sensorValue <=1023) {
        cycle = SUP;    
      }
      }
      else{
        cycle = IDL;
      }
     
      break;
      


      case ECO:
      digitalWrite(13, HIGH);
      bit4 = 0;
      bit3 = 1;
      bit2 = 1;

      digitalWrite(4, LOW);
      digitalWrite(3, HIGH);
      digitalWrite(2, LOW);
      delay(5000); 
     
      digitalWrite(3, LOW);
      digitalWrite(2, HIGH);
      delay(2000);
      cycle = IDL;
      break;

      case DEL:
      digitalWrite(13, HIGH);
      bit4 = 1;
      bit3 = 0;
      bit2 = 1;

      digitalWrite(4, HIGH);
      digitalWrite(3, LOW);
      digitalWrite(2, LOW);
      delay(7000);
      locked = digitalRead(buttonPin);
      sensorValue = analogRead(potPin);
       
      digitalWrite(4, LOW);
      digitalWrite(2, HIGH);
      delay(7000);

      cycle = IDL;
      break;

      case SUP:
      digitalWrite(13, HIGH);
      bit4 = 1;
      bit3 = 1;
      bit2 = 1;

      digitalWrite(4, HIGH);
      digitalWrite(3, LOW);
      digitalWrite(2, LOW);
      delay(7000);
        locked = digitalRead(buttonPin);
      sensorValue = analogRead(potPin);
       
      digitalWrite(3, HIGH);
      digitalWrite(2, LOW);
      delay(7000);
      
      locked = digitalRead(buttonPin);
      sensorValue = analogRead(potPin);
      digitalWrite(4, LOW);
      digitalWrite(3, LOW);
      digitalWrite(2, HIGH);
      delay(7000);

      //pprint(cycle);
      locked = digitalRead(buttonPin);
      sensorValue = analogRead(potPin);
      cycle = IDL;
      break;
  }
  return cycle;

    }



