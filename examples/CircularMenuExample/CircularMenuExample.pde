import processing.video.*;
import de.looksgood.ani.*;
import de.looksgood.ani.easing.*;
import pgf.exHIB.contentobject.*;
import pgf.exHIB.verticalmenu.*;
import pgf.exHIB.horizontalmenu.*;
import pgf.exHIB.circularmenu.*;

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
