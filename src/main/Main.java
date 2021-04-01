package main;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Main {
	static boolean running = true;
	static Window window;
	static double tickDur = 1/80.0;
	
	public static void main(String args[]) {
        run();
	}
	
	public static void run(){
		window = new Window();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				window.tick();
				delta--;
			}
			if(running)
				window.render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
	}
}
