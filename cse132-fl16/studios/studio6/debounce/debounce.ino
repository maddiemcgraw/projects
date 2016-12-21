const int buttonPin = 12;
const int ledPin = 4;
int count;
int ledState = LOW;
int buttonState;
int lastButtonState = LOW;

unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 50;

void setup() {
  Serial.begin(9600);
  // put your setup code here, to run once:
pinMode(buttonPin, INPUT_PULLUP);
pinMode(ledPin, OUTPUT);

digitalWrite(ledPin, ledState);
}

void loop() {
  // put your main code here, to run repeatedly:
int reading = digitalRead(buttonPin);

if (reading != lastButtonState) {
  lastDebounceTime = millis();
}
if ((millis() - lastDebounceTime) > debounceDelay) {
  if (reading != buttonState) {
    buttonState = reading;

  if (buttonState == LOW) {
    digitalWrite(ledPin, HIGH);
    count++;
    Serial.println("Press count = ");
    Serial.print(count);
  }
  if (buttonState == HIGH) {
    digitalWrite(ledPin, LOW);
  }
  }
  
}
lastButtonState = reading;
  }
  


