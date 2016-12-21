
int ledPin = 13;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
for(int n = 0; n < 20; n++){
  digitalWrite(ledPin, HIGH);
  delay(500);
  digitalWrite(ledPin, LOW);
Serial.print(n);
Serial.println(" seconds have elapsed");
 delay(500);
}
}
void loop() {
  
 
}

