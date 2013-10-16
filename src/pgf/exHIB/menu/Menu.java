package pgf.exHIB.menu;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import pgf.exHIB.contentobject.ContentObject;
import processing.core.PApplet;

public class Menu {

	PApplet parent;
	int numOfItems;
	int currentIndex;
	ArrayList<ContentObject> navArray;
	float activeScale = 1.2f;
	int y;
	int x;
	int spacing;
	int normW;
	int normH;
	
	public Menu(PApplet _parent) 
	{
		this.parent = _parent;
		this.currentIndex = 0;
		this.navArray = new ArrayList<ContentObject>();
		this.y = 0;
		this.spacing = 10;
		this.normW = 300;
		this.normH = 300;
		Ani.init(parent);
	}
	
	
	
	
}
