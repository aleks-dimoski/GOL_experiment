package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import objects.Boid;

public class Window extends Canvas {
	private static final long serialVersionUID = 1L;
	Handler objects;
	public static JFrame frame;
	Graphics g;
	
    public Window() {
        frame = new JFrame("My Game of Life");
        
        frame.setFocusable(true);
        frame.requestFocus();
        
        this.setSize(400, 300);
        objects = new Handler();
        for(int i = 0; i < 500; i++) {
        	double sizeMult = Math.random()+.4f;
        	Boid boi = new Boid(Math.random()*600,Math.random()*600,sizeMult*10,sizeMult*10);
        	boi.setVelX(3*(Math.random()-0.5));
        	boi.setVelY(3*(Math.random()-0.5));
        	objects.addObject(boi);
        }
        
        frame.setFocusTraversalKeysEnabled(false);
        frame.addKeyListener(new Keyboard(objects));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        
    }
    public void tick() {
    	frame.requestFocus();
    	objects.tick();
    }
    public void render() {
    	BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.blue);
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());
    	objects.render(g);
    	g.dispose();
    	bs.show();
    }
    
}
