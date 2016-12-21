/* stoplight
 *  
 *  CSE 132 - Studio 3
 *  
 *  Fill this out so we know whose studio this is.
 *  
 *  Name: Madeline McGraw
 *  WUSTL Key: 431546; memcgraw
 *  
 *  Name:
 *  WUSTL Key:
 *  
 *  Name:
 *  WUSTL Key:
 *  
 *  Name:
 *  WUSTL Key:
 */
#include <Arduino.h>
#include <Wire.h>

enum State {
  PED,
  NSG,
  NSY,
  EWG,
  EWY,
  
};

State counterLight = PED;

bool bit2;  //don't walk
bool bit3;  //NS Green
bool bit4;  //NS Yellow
bool bit5;  //NS Red
bool bit6;  //EW Green
bool bit7;  //EW Yellow
bool bit8;  //EW Red
bool bit9;  //walk

bool xWalk;

unsigned long previousMillis = 0;
const long interval = 1000;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
 

}

 

void loop() {
  unsigned long currentMillis = millis();
if (currentMillis - previousMillis>= interval){
    counterLight = nextLight(counterLight);

  previousMillis = currentMillis;
  digitalWrite(2, LOW);
  if (xWalk == 1) {
    digitalWrite(2, HIGH);
    delay(1000);
  }
  Serial.print("switch");
}
}

  
  // put your main code here, to run repeatedly:




State nextLight(State light) {

  switch(light){
    case PED:
   xWalk = 0;
    bit9 = 1;

    digitalWrite(2, LOW);
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    bit5 = 1;
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
    bit8 = 1;
    digitalWrite(8, HIGH);
    digitalWrite(9, HIGH);
    

    pprint(light);
    light = NSG;
    break;

    case NSG:
    xWalk = 1;
    bit2 = 1;
    bit5 = 0;
    bit3 = 1;
    bit9 = 0;
    
    digitalWrite(3, HIGH);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
    digitalWrite(8, HIGH);
    digitalWrite(9, LOW);

    pprint(light);
    light = NSY;
    break;

     case NSY:
     xWalk = 1;
    bit2 = 1;
    bit5 = 0;
    bit3 = 0;
    bit4 = 1;
   
    digitalWrite(3, LOW);
    digitalWrite(4, HIGH);
    digitalWrite(5, LOW);
    digitalWrite(6, LOW);
    digitalWrite(7, LOW);
    digitalWrite(8, HIGH);
    digitalWrite(9, LOW);

    pprint(light);
    light = EWG;
    break;

     case EWG:
     xWalk = 1;
     bit2 = 1;
    bit4 = 0;
    bit6 = 1;
    bit8 = 0;
    bit5 = 1;
 
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, HIGH);
    digitalWrite(7, LOW);
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);

    pprint(light);
    light = EWY;
    break;

    case EWY:
    xWalk = 1;
    bit2 = 1;
    bit4 = 0;
    bit6 = 0;
    bit7 = 1;
    bit8 = 0;
    bit5 = 1;
   
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, HIGH);
    digitalWrite(6, LOW);
    digitalWrite(7, HIGH);
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);

    pprint(light);
    light = PED;
    break;
  }
  return light;
}

void pprint(State light) {
  Serial.print(light);
  Serial.print(": ");
  Serial.print(bit2);
  Serial.print("  ");
  Serial.print(bit3);
  Serial.print("  ");
  Serial.print(bit4);
  Serial.print("  ");
  Serial.print(bit5);
  Serial.print("  ");
  Serial.print(bit6);
  Serial.print("  ");
  Serial.print(bit7);
  Serial.print("  ");
  Serial.print(bit8);
  Serial.print("  ");
  Serial.print(bit9);
}

