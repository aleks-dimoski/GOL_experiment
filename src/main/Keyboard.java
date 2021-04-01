package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.Boid;
import objects.ID;
import objects.Obj;

public class Keyboard implements KeyListener {
	Handler objects;
	
	public Keyboard(Handler objects) {
		this.objects = objects;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < objects.getObjects().size(); i++) {
			Obj obj = objects.getObjects().get(i);
			if(key == KeyEvent.VK_D) {
				if(obj.getId() == ID.Boid) {
					obj.setVelX(obj.getVelX()+0.5f);
					System.out.println("VelX+");
				}
			} else if (key == KeyEvent.VK_A) {
				if(obj.getId() == ID.Boid) {
					obj.setVelX(obj.getVelX()-0.5f);
					System.out.println("VelX-");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
