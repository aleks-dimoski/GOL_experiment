package objects;

import java.awt.Graphics;

public abstract class Obj {
	double x, y;
	double w, h;
	double velX, velY = 0.001f;
	public double dir;
	ID id;
	public static int prev = 0;
	public final int boidNum;
	
	public Obj() {
		x = 0;
		y = 0;
		w = 0;
		h = 0;
		boidNum = prev;
		prev++;
	}
	public Obj(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		boidNum = prev;
		prev++;
	}
	public boolean equals(Obj obj) {
		if(this.boidNum == obj.boidNum) {
			return true;
		}
		return false;
	}
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
	}
	public void render(Graphics g) {
		
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getW() {
		return w;
	}
	public double getH() {
		return h;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setW(double w) {
		this.w = w;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	
}
