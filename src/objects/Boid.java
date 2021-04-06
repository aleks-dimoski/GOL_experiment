package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.Window;

public class Boid extends Obj {
	Random rand;
	Color col;
	double wave;
	double dY=0, dX=0, cY=0, cX=0, sY=0, sX=0;
	int[] numNearby = new int[] {1,1,1};
	
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
	public void changeSize() {
		double size;
		size = Math.sin(wave*5)*.5f+.1f;
		
		this.w = size*15;
		this.h = size*15;
	}
	public void tick() {
		dY = dX = cY = cX = sY = sX = 0;
		numNearby = new int[] {1,1,1};
		
		wave += Math.random()*0.001+.005;
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
			x = -5;
		} else if (x < -5) {
			x = Window.frame.getWidth()-5;
		}
		if(y > Window.frame.getHeight()) {
			y = -5;
		} else if (y < -5) {
			y = Window.frame.getHeight()-5;
		}
	}
	public void compare(ArrayList<Obj> objects) {
		for(Obj obj : objects) {
			if(!(this.equals(obj))) {
				double x_dist = getX() - obj.getX();
				double y_dist = getY() - obj.getY();
				double dist = Math.sqrt(Math.pow(x_dist, 2)+Math.pow(y_dist, 2));
				
				if(dir < obj.dir+120 && dir > obj.dir-120) {
					if (dist < 200) {
						// Cohesion
						cX -=  0.055*x_dist/10;
						cY -=  0.055*y_dist/10;
						numNearby[0]++;
					}
					if (dist < 50) {
						// Alignment
						dX += 0.5*(obj.getVelX()-getVelX());
						dY += 0.5*(obj.getVelY()-getVelY());
						numNearby[1]++;
					}
					if (dist < 30) {
						// Separation
						sX += 0.02*x_dist;
						sY += 0.02*y_dist;
						numNearby[2]++;
					}
				}
			}
		}
		if(Math.pow(getVelX(), 2) + Math.pow(getVelY(), 2) < 25) {
			setVelY(1.2*getVelY()+cY/numNearby[0]+dY/numNearby[1]+sY/numNearby[2]);
			setVelX(1.2*getVelX()+cX/numNearby[0]+dX/numNearby[1]+sX/numNearby[2]);
		} else {
			setVelY(.9*getVelY()+cY/numNearby[0]+dY/numNearby[1]+sY/numNearby[2]);
			setVelX(.9*getVelX()+cX/numNearby[0]+dX/numNearby[1]+sX/numNearby[2]);
		}
		
	}
	public void render(Graphics g) {
		// Include Math.random() in sinusoid for scaly effect
		g.setColor(new Color((int)(Math.cos(.5*wave)*127.5+127.5), (int)(Math.cos(.39*wave)*127.5+127.5), (int)(Math.cos(.3*wave)*127.5+127.5)));
		g.fillOval((int)x, (int)y, (int)w, (int)h);
	}
}
