int MyVar;


unsigned long previousMillis = 0;
const long interval = 1000;

void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);
pinMode(3, OUTPUT);
pinMode(4, OUTPUT);
pinMode(5, OUTPUT);
pinMode(6, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
unsigned long currentMillis = millis();
if (currentMillis - previousMillis>= interval){
   previousMillis = currentMillis;
if(Serial.available() > 0) {
  MyVar = Serial.read();
  Serial.println(MyVar, BIN);
}
else {
  return;
}
}
}
