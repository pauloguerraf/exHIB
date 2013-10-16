import pgf.exHIB.contentobject.*;
import pgf.exHIB.menu.*;
import vialab.SMT.*;
import processing.serial.*;
import de.looksgood.ani.*;
import processing.video.*;


HorizontalMenu menu;
int yPos, currentPosX, lastX, lastY, currentLengthGestureX;
Serial sPort;
boolean checked = false;
int activeNum = 0;
int normW = 300;
int normH = 300;
int spacing = 10;

void setup() {
  size(1020, 768, P3D);
  //multitouch init
  TouchClient.init(this, TouchSource.MOUSE);
  TouchClient.setFastPicking(true);
  
  //menu init
  menu = new HorizontalMenu(this);
  menu.setSpacing(spacing);
  int x = 0;
  for (int i=0; i < 5; i++)
  {
    x  += normW/2 + spacing; 
    if (i!=3) {
      ContentObject a = new ContentObject(this, x, 0, normW, normH);
      menu.addContentItem(a);
    }
    else {
      //video element
      ContentObject a = new ContentObject(this, x, 0, normW, normH, "sample_sorenson.mov");
      menu.addContentItem(a);
    }
  }  
  menu.setY(height/2);       //set position of the menu
  menu.setActiveScale(2);    //scale for active item
  menu.setSpacing(20);		 //increase spacing
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
      menu.next();
      currentLengthGestureX = 0;
      checked = true;
    }
    else if (currentLengthGestureX > 40) {
      menu.prev();
      currentLengthGestureX = 0;
      checked = true;
    }
  }
}

void keyPressed() {
  if (keyCode == UP) {
    menu.next();
  }
  else if (keyCode == DOWN) {
    menu.prev();
  }
}

void movieEvent(Movie m) {
  m.read();
}

