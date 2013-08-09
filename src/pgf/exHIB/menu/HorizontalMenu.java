package pgf.exHIB.menu;

import processing.core.*;
import pgf.exHIB.contentobject.*;
import java.util.*;

import de.looksgood.ani.Ani;

public class HorizontalMenu {

	int numOfItems;
	int currentIndex;
	ArrayList<ContentObject> navArray;
	PApplet parent;
	float activeScale = 1.2f;
	int y;
	int spacing;
	int normW;
	int normH;

	public HorizontalMenu(PApplet _parent) 
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

	public void initDefaultContent(int numOfItems_) {
		this.numOfItems = numOfItems_;
		int x = 0;
		for (int i=0; i < this.numOfItems; i++)
		{
			x  += normW/2 + spacing; 
			ContentObject a = new ContentObject(parent, x, 0, normW, normH);
			this.navArray.add(a);
		}
		positionItems();
	}

	public void addContentItem(ContentObject a_){
		this.navArray.add(a_);
		this.numOfItems = navArray.size();
	}

	public void setY(int y_)
	{
		y = y_;
	}

	public void setSpacing(int spacing_)
	{
		spacing = spacing_;
		positionItems();
	}

	public void setActiveScale(float activeScale_)
	{
		this.activeScale = activeScale_;
		positionItems();
	}

	public void setCurrentIndex(int index_)
	{
		if (index_ > this.navArray.size()-1) {
			this.currentIndex = this.navArray.size()-1;
		}
		else if (index_ >= 0 && index_ <= this.navArray.size()-1) {
			this.currentIndex = index_;
		}
		else{
			this.currentIndex = 0;
		}
		positionItems();
	}

	public int getSize()
	{
		return this.navArray.size();
	}

	public int getCurrentIndex()
	{
		return currentIndex;
	}

	public void next()
	{
		if (currentIndex < navArray.size()-1) {
			this.currentIndex++;
		}
		//		else {
		//			this.currentIndex = 0;
		//		}
		positionItems();
	}

	public void prev()
	{
		if (currentIndex > 0) {
			currentIndex--;
		}
		//		else {
		//			currentIndex=navArray.size()-1;
		//		}
		positionItems();
	}

	private void positionItems()
	{
		int xPos;
		if(currentIndex == 0){
			xPos = (int)(this.normW*this.activeScale/2);
		}
		else{
			xPos = (int)(this.normW/2);
		}
		int activeX = 0;
		int difX;


		for (int i=0; i < this.navArray.size(); i++) {
			ContentObject a  = this.navArray.get(i);
			if (i == this.currentIndex) {
				a.setActive(true);
			}
			else {
				a.setActive(false);
			}
			if (a.isContentActive()) {
				a.setSize((int)(this.normW*this.activeScale), (int)(this.normH*this.activeScale));
			}
			else {
				a.setSize(this.normW, this.normH);
			}
			if (i>0) {
				xPos += (int)(this.navArray.get(i-1).getWidth())/2;
			}
			if(i == currentIndex){
				xPos += (int)(this.normW*this.activeScale/2) + 2*this.spacing;
			}
			else{
				xPos += (int)(this.normW/2) + 2*this.spacing;
			}
			a.setX(xPos);
			if (a.isContentActive()) {
				activeX = a.getX();
			}
		}


		difX = activeX - this.parent.width/2;
		for (int i=0; i<navArray.size(); i++) {
			ContentObject a  = navArray.get(i);
			a.setX(a.getX()-difX);
		}
	}

	public void display() {
		parent.pushMatrix();
		parent.translate(0, y);
		parent.rotate(-PApplet.radians(this.navArray.get(this.currentIndex).getRotation())); 
		for (int i=0; i < this.navArray.size(); i++) 
		{
			if (i != currentIndex) {
				this.navArray.get(i).display();
			}
		}
		this.navArray.get(this.currentIndex).display();
		parent.popMatrix();
	}

	public void stopAllVideo() {
		for (int i=0; i < navArray.size(); i++) {
			ContentObject a = navArray.get(i);
			if (a.getType().equals("video")) {
				a.stopVideo();
			}
		}
	}

	public void playActiveVideo() {
		for (int i=0; i < navArray.size(); i++) {
			ContentObject a = navArray.get(i);
			if (a.getType().equals("video") && a.isContentActive()) {
				a.playVideo();
			}
		}
	}

	public void touch(int x_, int y_){
		if (x_ > navArray.get(currentIndex).getX() - navArray.get(currentIndex).getWidth()/2 && x_ < navArray.get(currentIndex).getX() + navArray.get(currentIndex).getWidth()/2
				&& y_ > navArray.get(currentIndex).getY() - navArray.get(currentIndex).getHeight()/2 && y_ < navArray.get(currentIndex).getY() + navArray.get(currentIndex).getHeight()/2){   
			for (int i=0; i < navArray.size(); i++) {
				ContentObject a = navArray.get(i);
				if (a.isContentActive() && a.getType().equals("video")) {
					if (a.isVideoPlaying()) {
						a.pauseVideo();
					}
					else if (!a.isVideoPlaying()) {
						a.playVideo();
					}
				}
			}
		}
	}

}
