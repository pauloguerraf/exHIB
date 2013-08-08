package pgf.exHIB.contentobject;

import processing.core.*;
import processing.video.*;
import de.looksgood.ani.Ani;

public class ContentObject {
	int w;
	int h;
	int x;
	int y;
	float tR;
	boolean active;
	int spacing; 
	boolean isPlaying;
	float aniDelay = 1.5f;
	String type;

	Movie video; 
	PApplet parent;

	public ContentObject(PApplet parent_) {
		parent = parent_;
		type = "default";
		isPlaying = false;
	}

	public ContentObject(PApplet parent_, int x_, int y_, int w_, int h_) {
		parent = parent_;
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		tR = 0;
		type = "default";
		isPlaying = false;
	}

	public ContentObject(PApplet parent_, int x_, int y_, int w_, int h_, String videoURL_) {
		parent = parent_;
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		video = new Movie(parent, videoURL_);
		type = "video";
	}
	
	public void setX(int x_)
	{
		this.x = x_;
	}
	
	public void setY(int y_)
	{
		this.y = y_;
	}

	public void setActive(boolean val) {
		this.active = val;
	}

	public void setPosition(int x_, int y_) {
//		Ani.to(this, aniDelay, "x", x_);
//		Ani.to(this, aniDelay, "y", y_);
		x = x_;
		y = y_;
	}

	public void setSize(int w_, int h_) {
//		Ani.to(this, 0.5f, "w", w_);
//		Ani.to(this, 0.5f, "h", h_);
		w = w_;
		h = h_;
	}
	
	public void setWidth(int w_) {
		w = w_;
	}
	
	public void setHeight(int h_) {
		h = h_;
	}

	public void setSpacing(int spacing_) {
		spacing = spacing_;
	}

	public void setRotation(float tR_) {
		tR = tR_;
	}
	
	public float getRotation() {
		return tR;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}


	public void display() {
		parent.pushMatrix();
		parent.translate(x, y);
		parent.rotate(PApplet.radians(tR));
		parent.rectMode(PApplet.CENTER);
		if (active) {
			parent.fill(100, 100, 120);
		}
		else {
			parent.fill(50, 150, 200);
		}
		parent.rect(0, 0, w, h);
		if (video != null) {
			parent.imageMode(PApplet.CENTER);
			parent.image(video, x, y, w-10, h-10);
		}
		parent.popMatrix();
	}

	public void playVideo() {
		if(video != null){
			video.play();
			this.isPlaying = true;
		}
	}

	public void pauseVideo() {
		if(video != null){
			if (video.time() == video.duration()) {
				video.stop();
				video.play();
				this.isPlaying = true;
			}
			else {
				video.pause();
				this.isPlaying = false;
			}
		}
	}

	public void stopVideo() {
		if(video != null){
			video.stop();
			this.isPlaying = false;
		}
	}

	public boolean isVideoPlaying() {
		return this.isPlaying;
	}

	public boolean isContentActive() {
		return this.active;
	}
	
	public String getType() {
		return this.type;
	}


}
