#include"MorseCodes.h"

const int led = 13;
char Upper;
// Argument: Any character
// Return Value: Either:
//                  1) If the character is a letter, the upper case equivalent.  
//                  2) If the character is not a letter, the original value.
char toUpper(char c) {
  Upper = toUpperCase(c);
  return Upper;
  // TODO
  // int cint = (int) c;
  // if(cint<=122 && cint >=97) {
  // c = cint - 32;
  // return c;
// }
// else {
  //return c;
// }
}

void setup() {
  Serial.begin(9600);
  pinMode(led, OUTPUT);
}


void convertIncomingCharsToMorseCode() {
  if(Serial.available() > 0) {
    char rx_byte = Serial.read();

    Serial.print("I received: ");
    Serial.println(rx_byte);
  }
  
  // TODO
  //This has to read a string piece by piece and determine how long the led blinks
  char buf[] = {symbol};
  // how to recognize the difference between '.' and '-'
  for (int i=0; i<encoded.length; i++) {
  if (buf[i] = " ") {
    delay(500);
  }
  else if (buf[i] = ".") {
    digitalWrite(led, HIGH);
    delay(500);     //will need to get rid of delay and use delta timing
    digitalWrite(led, LOW);
  }
  else if (buf[i] = "-") {
    digitalWrite(led, HIGH);
    delay(1500);   //will need to get rid of delay and use delta timing
    digitalWrite(led, LOW);
  }
  }
}




void loop() {
  // No Need to Modify this.  Put most of your code in "convertIncomingCharsToMorseCode()"
  convertIncomingCharsToMorseCode();
}
