package co.uk.haydng.rpg.graphics;

public class Display {
	
	private int WIDTH,HEIGHT;
	public int[] pixels;
	public int[] blank;
	
	public Display(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		pixels = new int[WIDTH * HEIGHT];
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
				pixels[x+y*WIDTH] = 0xff00ff;
			}
		}
	}
}
