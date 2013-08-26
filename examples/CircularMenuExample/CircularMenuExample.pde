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

import processing.video.*;
import de.looksgood.ani.*;
import de.looksgood.ani.easing.*;
import pgf.exHIB.contentobject.*;
import pgf.exHIB.menu.*;

CircularMenu menu;

void setup() {
  size(1020, 768);
  menu = new CircularMenu(this, 400.0);
  menu.initDefaultContent(7);
  menu.setX(width/2);
  menu.setY(height);
}

void draw() {
  background(20);
  menu.display();
}


void keyPressed() {
  if (keyCode == UP) {
    menu.next();
  }
  else if (keyCode == DOWN) {
    menu.prev();
  }
}
