//ARDUINO CODE
/*int potentiometerPin = A0;  
int sensorValue = 0;
int lastSensorValue = 0;
long sum = 0;
long count = 0;

void setup(){
  Serial.begin(115200);
  sensorValue = analogRead(potentiometerPin);
  lastSensorValue = sensorValue;
}

void loop(){
  delay(25);
  sensorValue = analogRead(potentiometerPin); 
  Serial.print(sensorValue);
  Serial.println();  
}*/

import pgf.exHIB.contentobject.*;
import pgf.exHIB.menu.*;
import vialab.SMT.*;
import processing.serial.*;
import de.looksgood.ani.*;
import processing.video.*;

////////////////////////////////////////////
// setup variables /////////////////////////
////////////////////////////////////////////
boolean usingSerial = false;
////////////////////////////////////////////
HorizontalMenu menu;
int yPos, currentPosX;
Serial sPort;
int activeNum = 0;

void setup() {
  size(1020, 768, P3D);
  startSerial();
  menu = new HorizontalMenu(this);
  menu.initDefaultContent(5);
  menu.setY(height/2);
  menu.setActiveScale(2);
  menu.setSpacing(20);
}

void draw() {
  background(125);
  checkActive();
  menu.display();
}

void checkActive() {
  if (usingSerial) {
    activeNum = constrain((int)map(currentPosX, 0, 1024, 0, menu.getSize()), 0, menu.getSize());
  }
  else if (!usingSerial) {
    activeNum = constrain((int)map(mouseX, width/2-200, width/2+200, 0, menu.getSize()), 0, menu.getSize());
  }
  menu.setCurrentIndex(activeNum);
}

void movieEvent(Movie m) {
  m.read();
}