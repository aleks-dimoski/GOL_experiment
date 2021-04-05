package main;

import java.awt.Graphics;
import java.util.ArrayList;
import objects.Obj;

public class Handler {
	double wave = 0;
	private ArrayList<Obj> objects = new ArrayList<Obj>();
	
	public ArrayList<Obj> getObjects() {
		return objects;
	}
	public void addObject(Obj obj) {
		objects.add(obj);
	}
	public void clear() {
		objects.clear();
	}
	public void render(Graphics g) {
		for(Obj obj : objects) {
			obj.render(g);
		}
	}
	public void tick() {
		wave += 0.01f;
		for(Obj obj : objects) {
			double dY = 0;
			double dX = 0;
			double cY = 0;
			double cX = 0;
			double sY = 0;
			double sX = 0;
			int[] numNearby = new int[] {1,1,1};
			
			for(Obj obj2 : objects) {
				if(!obj.equals(obj2)) {
					double x_dist = obj.getX() - obj2.getX();
					double y_dist = obj.getY() - obj2.getY();
					double dist = Math.sqrt(Math.pow(x_dist, 2)+Math.pow(y_dist, 2));
					
					if(obj.dir < obj2.dir+120 && obj.dir > obj2.dir-120) {
						if (dist < 200) {
							// Cohesion 200, 0.05, 0.05
							cX -=  0.05*x_dist/10;
							cY -=  0.05*y_dist/10;
							numNearby[0]++;
						}
						if (dist < 50) {
							// Alignment
							dX += 0.5*(obj2.getVelX()-obj.getVelX());
							dY += 0.5*(obj2.getVelY()-obj.getVelY());
							numNearby[1]++;
						}
						if (dist < 30) {
							// Separation 30, 0.02, 0.02
							sX += 0.02*x_dist;
							sY += 0.02*y_dist;
							numNearby[2]++;
						}
					}
				}
			}
			
			if(Math.pow(obj.getVelX(), 2) + Math.pow(obj.getVelY(), 2) < 25) {
				obj.setVelY(1.2*obj.getVelY()+
						cY/numNearby[0]+dY/numNearby[1]+sY/numNearby[2]);
				obj.setVelX(1.2*obj.getVelX()+cX/numNearby[0]+dX/numNearby[1]+sX/numNearby[2]);
			} else {
				obj.setVelY(.9*obj.getVelY()+cY/numNearby[0]+dY/numNearby[1]+sY/numNearby[2]);
				obj.setVelX(.9*obj.getVelX()+cX/numNearby[0]+dX/numNearby[1]+sX/numNearby[2]);
			}
			obj.tick();
		}
	}
}
