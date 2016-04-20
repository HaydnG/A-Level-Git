package co.uk.haydng.rpg.graphics;

import java.util.Random;

public class Display {
	
	private int WIDTH,HEIGHT;
	public int[] pixels;
	
	public int[] tiles = new int[64*64];
	
	private Random random = new Random();
	
	public Display(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		pixels = new int[WIDTH * HEIGHT];
		
		for (int i =0;i<64*64;i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear(){
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}

	}
	
	public void render() {
		for (int y = 0; y < HEIGHT; y++){
			if (y < 0 || y >= HEIGHT) break;
			for (int x = 0; x < WIDTH; x++){
				if (x< 0 || x >= WIDTH) break;
				int tileIndex = (x >> 4) + (y >> 4) * 64;
				pixels[x+y*WIDTH] = tiles[tileIndex];
			}
		}
	}
}
