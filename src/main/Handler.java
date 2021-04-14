package main;

import java.awt.Graphics;

import java.util.ArrayList;
import objects.Obj;

public class Handler {
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
		for(Obj obj : objects) {
			obj.compare(objects);
			obj.tick();
		}
	}
}
