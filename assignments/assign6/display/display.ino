/* display

    CSE 132 - Assignment 7

    Fill this out so we know whose assignment this is.

    Name:Madeline McGraw
    WUSTL Key: 431546

    Name: Anna Boerwinkle
    WUSTL Key: 429055

*/

#include "font.h"

const int up = 0;
const int down = 1;

int goUp = 0;
int goDown = 0;
int upState;
int downState;

const int r1 = 2;
const int r2 = 3;
const int r3 = 4;
const int r4 = 5;
const int r5 = 6;
const int r6 = 7;
const int r7 = 8;

const int c1 = 9;
const int c2 = 10;
const int c3 = 11;
const int c4 = 12;
const int c5 = 13;

float c = 0;
int cC = 0;
int count = 10;

int row[7] = {r1, r2, r3, r4, r5, r6, r7};
int col[5] = {c1, c2, c3, c4, c5};

unsigned long previousMillis = 0;
const long interval = 1000;
unsigned long oldMillis = 0;

unsigned long lastUp = 0;
unsigned long lastDown = 0;
unsigned long lastUpDelay = 50;
unsigned long lastDownDelay = 50;

//to get an individual LED to light the col needs to LOW and the row needs to be HIGH

void setup ()
{
  Serial.begin(9600);
  // insert code here as needed
  pinMode(up, INPUT_PULLUP);
  pinMode(down, INPUT_PULLUP);

  pinMode(r1, OUTPUT);
  pinMode(r2, OUTPUT);
  pinMode(r3, OUTPUT);
  pinMode(r4, OUTPUT);
  pinMode(r5, OUTPUT);
  pinMode(r6, OUTPUT);
  pinMode(r7, OUTPUT);

  pinMode(c1, OUTPUT);
  pinMode(c2, OUTPUT);
  pinMode(c3, OUTPUT);
  pinMode(c4, OUTPUT);
  pinMode(c5, OUTPUT);
}

void loop ()
{
  // insert code here as needed

  //testLights();
  goUp = analogRead(up);
  goDown = analogRead(down);

  Serial.print(goUp);
  Serial.print(",   ");
  Serial.println(goDown);
    
  if ((millis() - lastUp) > lastUpDelay) {
    if(goUp != upState) {
      upState = goUp;
        if (count == 95 && goUp == 0) {
          count = 0;

        } else if (goUp == 0 && goDown > 0) {
          count++;
        }
    }
    dispChar();
  }
  
  if ((millis() - lastDown) > lastDownDelay) {
    if(goDown != downState) {
      downState = goDown;
      if (count == 0 && goDown == 0) {
          count = 95;
        } 
        else if (goUp > 0 && goDown == 0) {
          count--;
        }
    }
    dispChar();
  }

}

void dispChar() {
//  testLights();
//  Serial.print("letter is ");
//  Serial.println("    ");
//  Serial.println(count);
//  low();
  
  for (int m = 0; m < 5; m++) {
//    Serial.println(font_5x7[count][m], BIN);
    c = font_5x7[count][m];
    cC = (int)c;
  
    if(m == 0) {
        digitalWrite(c5, LOW);
        digitalWrite(c4, HIGH);
        digitalWrite(c3, HIGH);
        digitalWrite(c2, HIGH);
        digitalWrite(c1, HIGH);
        rowLights(cC);
        delay(2);
    }
    if(m == 1) {
        digitalWrite(c5, HIGH);
        digitalWrite(c4, LOW);
        digitalWrite(c3, HIGH);
        digitalWrite(c2, HIGH);
        digitalWrite(c1, HIGH);
        rowLights(cC);
        delay(2);
    }
    if(m == 2) {
        digitalWrite(c5, HIGH);
        digitalWrite(c4, HIGH);
        digitalWrite(c3, LOW);
        digitalWrite(c2, HIGH);
        digitalWrite(c1, HIGH);
        rowLights(cC);
        delay(2);
    }
    if(m == 3) {
        digitalWrite(c5, HIGH);
        digitalWrite(c4, HIGH);
        digitalWrite(c3, HIGH);
        digitalWrite(c2, LOW);
        digitalWrite(c1, HIGH);
        rowLights(cC);
        delay(2);
    }
    if(m == 4) {
        digitalWrite(c5, HIGH);
        digitalWrite(c4, HIGH);
        digitalWrite(c3, HIGH);
        digitalWrite(c2, HIGH);
        digitalWrite(c1, LOW);
        rowLights(cC);
        delay(2);
    }
  //delay(5);
        high();

        //Serial.println(cC);  
}

}

int rowLights(int cC){
  if (bitRead(cC,1)==1) {
  digitalWrite(r1, HIGH);
  Serial.print(cC);
  }
  else if (bitRead(cC,1)!=1){
    digitalWrite(r1, LOW);
  }
  if (bitRead(cC,2)==1) {
    digitalWrite(r2, HIGH);
    Serial.print(cC);
  }
  else if (bitRead(cC,2)!=1){
    digitalWrite(r2, LOW);
  }
  if (bitRead(cC,3)==1) {
    digitalWrite(r3, HIGH);
    Serial.print(cC);
  }
  else if (bitRead(cC,3)!=1){
    digitalWrite(r3, LOW);
  }
  if (bitRead(cC,4)==1) {
    digitalWrite(r4, HIGH);
    Serial.print(cC);
  }
  else if (bitRead(cC,4)!=1){
    digitalWrite(r4, LOW);
  }
  if (bitRead(cC,5)==1) {
    digitalWrite(r5, HIGH);
    Serial.print(cC);
  }
  else if (bitRead(cC,5)!=1){
    digitalWrite(r5, LOW);
  }
  if (bitRead(cC,6)==1) {
    digitalWrite(r6, HIGH);
    Serial.print(cC);
  }else if (bitRead(cC,6)!=1){
    digitalWrite(r6, LOW);
  }
  if (bitRead(cC,7)==1) {
    digitalWrite(r7, HIGH);
    Serial.print(cC);
  }
  else if (bitRead(cC,7)!=1){
    digitalWrite(r7, LOW);
  }
  

  return cC;
}


void low() {
  digitalWrite(c1, LOW);
  digitalWrite(c2, LOW);
  digitalWrite(c3, LOW);
  digitalWrite(c4, LOW);
  digitalWrite(c5, LOW);

  digitalWrite(r1, LOW);
  digitalWrite(r2, LOW);
  digitalWrite(r3, LOW);
  digitalWrite(r4, LOW);
  digitalWrite(r5, LOW);
  digitalWrite(r6, LOW);
  digitalWrite(r7, LOW);
  delay(1000);
}
void high() {
  digitalWrite(c1, HIGH);
  digitalWrite(c2, HIGH);
  digitalWrite(c3, HIGH);
  digitalWrite(c4, HIGH);
  digitalWrite(c5, HIGH);

  digitalWrite(r1, HIGH);
  digitalWrite(r2, HIGH);
  digitalWrite(r3, HIGH);
  digitalWrite(r4, HIGH);
  digitalWrite(r5, HIGH);
  digitalWrite(r6, HIGH);
  digitalWrite(r7, HIGH);
  delay(5);
}

void testLights() {
  digitalWrite(c1, LOW);
  digitalWrite(c2, LOW);
  digitalWrite(c3, LOW);
  digitalWrite(c4, LOW);
  digitalWrite(c5, LOW);

  digitalWrite(r1, HIGH);
  digitalWrite(r2, HIGH);
  digitalWrite(r3, HIGH);
  digitalWrite(r4, HIGH);
  digitalWrite(r5, HIGH);
  digitalWrite(r6, HIGH);
  digitalWrite(r7, HIGH);
  delay(1000);
}

void half() {
  digitalWrite(c1, LOW);
  digitalWrite(c2, LOW);
  digitalWrite(c3, LOW);
  digitalWrite(c4, LOW);
  digitalWrite(c5, LOW);

  digitalWrite(r1, LOW);
  digitalWrite(r2, HIGH);
  digitalWrite(r3, LOW);
  digitalWrite(r4, HIGH);
  digitalWrite(r5, LOW);
  digitalWrite(r6, HIGH);
  digitalWrite(r7, LOW);
  delay(1000);
}
