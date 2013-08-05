package pgf.exHIB.circularmenu;

import processing.core.*;
import de.looksgood.ani.Ani;
import pgf.exHIB.contentobject.*;
import java.util.*;

public class CircularMenu {

	float innerRadius;
	int numOfItems;
	int currentIndex;
	int x, y;
	ArrayList<ContentObject> navArray;
	PApplet parent;

	public CircularMenu(PApplet _parent, float _innerRadius) 
	{
		this.parent = _parent;
		this.currentIndex = 0;
		this.navArray = new ArrayList<ContentObject>();
		this.innerRadius = _innerRadius;
		x = parent.width/2;
		y = parent.height/2;
		Ani.init(parent);
	}

	public void initDefaultContent(int _numOfItems) {
		this.numOfItems = _numOfItems;
		for (int i=0; i < this.numOfItems; i++) {
			ContentObject a = new ContentObject(parent);
			this.navArray.add(a);
		}
		positionItems();
	}

	public void addContentObject(ContentObject _contentObject) {
		this.navArray.add(_contentObject);
	}

	public int getCurrentIndex()
	{
		return this.currentIndex;
	}
	
	public int getSize()
	{
		return this.navArray.size();
	}

	public void setX(int x_)
	{
		this.x = x_;
	}

	public void setY(int y_)
	{
		this.y = y_;
	}

	public void setCurrentIndex(int index_)
	{
		if ( index_ < 0 ) {
			this.currentIndex = index_;
		}
		else if ( index_ > this.navArray.size()-1) {
			this.currentIndex = this.navArray.size()-1;
		}
		positionItems();
	}

	public float getInnerRadius()
	{
		return this.innerRadius;
	}

	public void next()
	{
		if (this.currentIndex < this.navArray.size()-1) {
			this.currentIndex++;
		}
		else {
			this.currentIndex = 0;
		}
		positionItems();
	}

	public void prev()
	{
		if (this.currentIndex > 0) {
			this.currentIndex--;
		}
		else {
			this.currentIndex = this.navArray.size()-1;
		}
		positionItems();
	}

	public void display() {
		parent.pushMatrix();
		parent.translate(x, y);
		parent.rotate(-PApplet.radians(this.navArray.get(this.currentIndex).getRotation())); 
		for (int i=0; i < this.navArray.size(); i++) 
		{
			if (!this.navArray.get(i).isContentActive()) {
				this.navArray.get(i).display();
			}
		}
		this.navArray.get(this.currentIndex).display();
		parent.popMatrix();
	}

	private void positionItems()
	{
		if (this.navArray.size() == 0) return;
		float angle;
		int offset;
		float tX;
		float tY;
		float tR;
		ContentObject item;
		float angleSpacing = 360/this.numOfItems;

		for (int i=0; i < this.navArray.size(); i++) 
		{
			offset = (i + 1) - this.currentIndex;
			angle = PApplet.radians(offset * angleSpacing);

			item = this.navArray.get(i);
			if (this.currentIndex == i) {
				item.setActive(true);
			}
			else {
				item.setActive(false);
			}
			tX = this.innerRadius * PApplet.cos( angle );
			tY = this.innerRadius * PApplet.sin( angle );
			PApplet.println(tX + " " + tY);
			tR = PApplet.degrees(angle-PApplet.PI/2);
			item.setRotation(tR);
			item.setPosition((int)tX, (int)tY);
			item.setSize(300, 100);
		}
	}
}
