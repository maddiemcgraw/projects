const int buttonPin = 12;
int buttonPushCounter = 0;
int buttonState = 1;
int lastButtonState = 0;
int light = 4;

int ledState = LOW;

void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);
pinMode(4, OUTPUT);
pinMode(buttonPin, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:
buttonState = digitalRead(buttonPin);
digitalWrite(light, LOW);
    Serial.println(buttonPushCounter++);

   if (buttonState == 0){
    digitalWrite(light, HIGH);
    buttonPushCounter++;
    Serial.println(buttonPushCounter++);
  }
  else{
    digitalWrite(light, LOW);    
  }
}
