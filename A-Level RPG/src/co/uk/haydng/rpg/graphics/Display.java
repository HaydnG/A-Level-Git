package co.uk.haydng.rpg.graphics;

import java.util.Random;

public class Display {
	
	private int WIDTH,HEIGHT;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	
	private Random random = new Random();
	
	public Display(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		pixels = new int[WIDTH * HEIGHT];
		
		for (int i =0 ; i< MAP_SIZE  * MAP_SIZE ; i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear(){
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}

	}
	//renderer
	public void render(int XOffset, int YOffset) {
		for (int y = 0; y < HEIGHT; y++){
			int yy = y + YOffset;
			//if (yy < 0 || yy >= HEIGHT) break;
			for (int x = 0; x < WIDTH; x++){
				int xx = x + XOffset;
				//if (xx< 0 || xx >= WIDTH) break;
				int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
				pixels[x+y*WIDTH] = tiles[tileIndex];//Points to a tile on the map
			}
		}
	}
}
