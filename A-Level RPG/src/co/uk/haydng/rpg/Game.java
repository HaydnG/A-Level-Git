package co.uk.haydng.rpg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import co.uk.haydng.rpg.graphics.Display;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1;
	
	private Thread THREAD;
	private boolean RUNNING = false;
	private JFrame FRAME;
	
	public static int SCALE = 3;
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 10;
	
	private Display display;
	
	private BufferedImage img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension SIZE = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(SIZE);
		
		display = new Display(WIDTH,HEIGHT);
		
		FRAME = new JFrame();
	}

	public void run() { //Main game loop 
		while (RUNNING) {
			tick();
			render();		
		}
	}
	
	public void tick(){
		
	}
	
	public void render(){
		BufferStrategy buffer = getBufferStrategy();
		if(buffer == null){
			createBufferStrategy(3); //Tripple buffering - frame render queue
			return;
		}
		display.clear();
		display.render();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = display.pixels[i];
		}
		
		draw(buffer);
	}
		
	
	public void draw(BufferStrategy buffer){
		Graphics g = buffer.getDrawGraphics();
		//g.setColor(Color.black);
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(img,0,0,getWidth(),getHeight(),null);
		g.dispose();
		buffer.show();
	
	}
	
	public static void main(String[] args){ //Main method  - Called on program start
		Game game = new Game(); //Creates game object
		game.FRAME.setResizable(false);
		game.FRAME.setTitle("A-Level RPG");
		game.FRAME.add(game);
		game.FRAME.pack();
		game.FRAME.setLocationRelativeTo(null);
		game.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.FRAME.setVisible(true);
		
		game.start(); //Calls start method
	}
	
	//START/STOP
	public synchronized void start(){
		RUNNING= true; //Allows the loop to run
		THREAD = new Thread(this,"Display"); //Creates game thread - Starts run loop as Game.java is runnable
		THREAD.start(); //Starts the thread begins thread - run() loop
	}
	
	public synchronized void stop() {
		RUNNING = false; //Stops the loop from being able to be ran
		try {
			THREAD.join(); //Waits for thread to die, catches interrupt errors
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
