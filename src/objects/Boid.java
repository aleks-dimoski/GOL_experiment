package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Window;

public class Boid extends Obj {
	public int rad = 5;
	Random rand;
	Color col;
	double wave;
	
	public Boid() {
		super();
		setId(ID.Boid);
		rand = new Random();
		col = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		wave = Math.random()*2*Math.PI;
	}
	public Boid(double x, double y, double w, double h) {
		super(x, y, w, h);
		setId(ID.Boid);
		rand = new Random();
		col = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		wave = Math.random()*2*Math.PI;
	}
	public void tick() {
		wave += Math.random()*.005+.005;
		velX += Math.random()*Math.cos(wave);
		velY += Math.random()*Math.sin(wave*1.3);
		
		if(velY > 10) {
			velY=10;
		} else if(velY < -10) {
			velY=-10;
		}
		if(velX > 10) {
			velX=10;
		} else if(velX < -10) {
			velX=-10;
		}
		
		this.x += this.velX;
		this.y += this.velY;
		dir = Math.atan2(velY, velX);
		
		if(x > Window.frame.getWidth()) {
			x = 5;
		} else if (x < 0) {
			x = Window.frame.getWidth()-5;
		}
		if(y > Window.frame.getHeight()) {
			y = 5;
		} else if (y < 0) {
			y = Window.frame.getHeight()-5;
		}
	}
	public void render(Graphics g) {
		g.setColor(col);
		g.fillOval((int)x, (int)y, (int)w, (int)h);
	}
}
