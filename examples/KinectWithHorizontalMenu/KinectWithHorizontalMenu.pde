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
int yPos, currentPosX, lastX, lastY, currentLengthGestureX;
boolean checked = false;
int activeNum = 0;


void setup() {
  size(1020, 768, P3D);
  TouchClient.init(this, TouchSource.MOUSE);
  TouchClient.setFastPicking(true);
  menu = new HorizontalMenu(this);
  menu.initDefaultContent(5);
  menu.setY(height/2);
  menu.setActiveScale(2);
  menu.setSpacing(20);
}

void draw() {
  background(125);
  checkTouches();
  menu.display();
}

void checkTouches() {
  if (TouchClient.getTouches().length == 1) {
    Touch t = TouchClient.getTouches()[0];
    lastX = t.x;
    lastY = t.y;
    currentLengthGestureX = t.x - t.getPointOnPath(0).x;
    checkActive();
  }
  else if (TouchClient.getTouches().length == 0) {
    checkPress(lastX, lastY);
  }
  else {
    checked = false;
  }
}

void checkPress(int x_, int y_) {
  if (!checked) {
    if (abs(currentLengthGestureX) < 10) {
      menu.touch(x_, y_);
      lastX = 0;
      lastY = 0;
      checked = true;
    }
  }
  else {
    checked = false;
  }
}

void checkActive() {
  if (!checked) {
    if (currentLengthGestureX < -40) {
      activeNum++;
      menu.setCurrentIndex(activeNum);
      currentLengthGestureX = 0;
      checked = true;
    }
    else if (currentLengthGestureX > 40) {
      activeNum--;
      menu.setCurrentIndex(activeNum);
      currentLengthGestureX = 0;
      checked = true;
    }
  }
}

void movieEvent(Movie m) {
  m.read();
}

