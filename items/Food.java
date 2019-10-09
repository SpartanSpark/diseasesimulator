package items;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.food;

public class Food extends Item {
	
	public Food(int x, int y) {
		super(x, y, 8);
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		DrawQuadTex(food,ax-size/2,ay-size/2,size,size);
	}
}
