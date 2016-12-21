#include "font.h"

/* Weather Station
 *  
 *  CSE 132 - Assignment 10
 *  
 *  Fill this out so we know whose assignment this is.
 *  
 *  Name: Anna Boerwinkle
 *  WUSTL Key: 429055
 *  
 *  Name: Maddie McGraw
 *  WUSTL Key: 431546
 *  
 */
 
#include <Arduino.h>


const int Row1 = 2;
const int Row2 = 3;
const int Row3 = 4;
const int Row4 = 5;
const int Row5 = 6;
const int Row6 = 7;
const int Row7 = 8;
const int Col1 = 9;
const int Col2 = 10;
const int Col3 = 11;
const int Col4 = 12;
const int Col5 = 13;

const int Pot = 0;
const int L1 = 1;
const int L2 = 2;
const int L3 = 3;

const int Magic = 0x21;
const int Loc1 = 0x31;
const int Loc2 = 0x32;
const int Loc3 = 0x33;

int PotRead = 0;
int SendLoc = 0;
int CurrLoc = 0;
unsigned long accum = 0;

char Icon;
int IconRow = 0;

float hexC = 0.0;

void setup () {
  Serial.begin(9600);
  pinMode(Row1, OUTPUT);
  pinMode(Row2, OUTPUT);
  pinMode(Row3, OUTPUT);
  pinMode(Row4, OUTPUT);
  pinMode(Row5, OUTPUT);
  pinMode(Row6, OUTPUT);
  pinMode(Row7, OUTPUT);
  pinMode(Col1, OUTPUT);
  pinMode(Col2, OUTPUT);
  pinMode(Col3, OUTPUT);
  pinMode(Col4, OUTPUT);
  pinMode(Col5, OUTPUT);
}

void loop () {
  ReadSetLoc();
  if (Serial.available()>0){
    Icon = Serial.read();
    if (Icon == 'o'){ //Clear-Day
      IconRow = 80;
    }
    else if (Icon == ')'){ //Clear-Night
      IconRow = 9;
    }
    else if (Icon == '"'){ //Rain
      IconRow = 2;
    }
    else if (Icon == '*'){ //Snow
      IconRow = 10;
    }
    else if (Icon == ';'){ //Sleet
      IconRow = 28;
    }
    else if (Icon == '~'){ //Wind
      IconRow = 95;
    }
    else if (Icon == '#'){ //Fog
      IconRow = 3;
    }
    else if (Icon == '%'){ //Cloudy
      IconRow = 5;
    }
    else if (Icon == '+'){ //Partly-Cloudy-Day
      IconRow = 11;
    }
    else if (Icon == '/'){ //Partly-Cloudy-Night
      IconRow = 15;
    }
    else { //ERROR
      IconRow = 38;
    }
  }
  displayChar(IconRow);
}

void ReadSetLoc (){
  PotRead = analogRead(Pot);
  if (PotRead>=0 && PotRead<=340){
    SendLoc = Loc1;
  }
  if (PotRead>=341 && PotRead<=682){
    SendLoc = Loc2;
  }
  if (PotRead>=683 && PotRead<=1023){
    SendLoc = Loc3;
  }
//  if (SendLoc != CurrLoc){
//    Serial.write(Magic);
//    Serial.write(SendLoc);
//    CurrLoc = SendLoc;
//  }
//  if (millis()-accum > 5000){
//    Serial.write(Magic);
//    Serial.write(SendLoc);
//    accum += 5000;
//  }
  Serial.write(Magic);
  Serial.write(SendLoc);
}

void displayChar (int c) {
  //Serial.println (c);
  for (int i=0; i<5; i++){
    hexC = font_5x7[c][i];
    int hexCol = (int)hexC;
    if (i==0){
      digitalWrite(Col1, LOW);
      digitalWrite(Col2, HIGH);
      digitalWrite(Col3, HIGH);
      digitalWrite(Col4, HIGH);
      digitalWrite(Col5, HIGH);
      setRows (hexC);
    }
    if (i==1){
      digitalWrite(Col1, HIGH);
      digitalWrite(Col2, LOW);
      digitalWrite(Col3, HIGH);
      digitalWrite(Col4, HIGH);
      digitalWrite(Col5, HIGH);
      setRows (hexC);
    }
    if (i==2){
      digitalWrite(Col1, HIGH);
      digitalWrite(Col2, HIGH);
      digitalWrite(Col3, LOW);
      digitalWrite(Col4, HIGH);
      digitalWrite(Col5, HIGH);
      setRows (hexC);
    }
    if (i==3){
      digitalWrite(Col1, HIGH);
      digitalWrite(Col2, HIGH);
      digitalWrite(Col3, HIGH);
      digitalWrite(Col4, LOW);
      digitalWrite(Col5, HIGH);
      setRows (hexC);
    }
    if (i==4){
      digitalWrite(Col1, HIGH);
      digitalWrite(Col2, HIGH);
      digitalWrite(Col3, HIGH);
      digitalWrite(Col4, HIGH);
      digitalWrite(Col5, LOW);
      setRows (hexC);
    }
    //Serial.print ("i= ");
    //Serial.println (i);
    delay(5);
    Default();
    //Serial.print (" ");   
  }
  //Serial.println ("----NEXT CHAR----");
}

void setRows (int col) {
  if (bitRead(col,7) == 1){ //If there is 1 in first position
    digitalWrite(Row1, HIGH);
    //Serial.println("Row1 On");
  }
  else if (bitRead(col,7) != 1){
    digitalWrite(Row1, LOW);
    //Serial.println("Row1 Off");
  }
  if (bitRead(col,6) == 1){
    digitalWrite(Row2, HIGH);
    //Serial.println("Row2 On");
  }
  else if (bitRead(col,6) != 1) {
    digitalWrite(Row2, LOW);
    //Serial.println("Row2 Off");
  }
  if (bitRead(col,5) == 1){
    digitalWrite(Row3, HIGH);
    //Serial.println("Row3 On");
  }
  else if(bitRead(col,5) != 1) {
    digitalWrite(Row3, LOW);
    //Serial.println("Row3 Off");
  }
  if (bitRead(col,4) == 1){
    digitalWrite(Row4, HIGH);
    //Serial.println("Row4 On");
  }
  else if (bitRead(col,4) != 1) {
    digitalWrite(Row4, LOW);
    //Serial.println("Row4 Off");
  }
  if (bitRead(col,3) == 1){
    digitalWrite(Row5, HIGH);
    //Serial.println("Row5 On");
  }
  else if (bitRead(col,3) != 1) {
    digitalWrite(Row5, LOW);
    //Serial.println("Row5 Off");
  }
  if (bitRead(col,2) == 1){
    digitalWrite(Row6, HIGH);
    //Serial.println("Row6 On");
  }
  else if (bitRead(col,2) != 1) {
    digitalWrite(Row6, LOW);
    //Serial.println("Row6 Off");
  }
  if (bitRead(col,1) == 1){
    digitalWrite(Row7, HIGH);
    //Serial.println("Row7 On");
  }
  else if (bitRead(col,1) != 1) {
    digitalWrite(Row7, LOW);
    //Serial.println("Row7 Off");
  }
}

void Default () {
  digitalWrite(Row1, LOW);
  digitalWrite(Row2, LOW);
  digitalWrite(Row3, LOW);
  digitalWrite(Row4, LOW);
  digitalWrite(Row5, LOW);
  digitalWrite(Row6, LOW);
  digitalWrite(Row7, LOW);
  digitalWrite(Col1, LOW);
  digitalWrite(Col2, LOW);
  digitalWrite(Col3, LOW);
  digitalWrite(Col4, LOW);
  digitalWrite(Col5, LOW);
}


