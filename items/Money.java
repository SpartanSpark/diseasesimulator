package items;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.money;

public class Money extends Item {
	
	public Money(int x, int y) {
		super(x, y, 8);
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm);
		draw((int) ((super.x-tx)/zm),(int) ((super.y-tx)/zm),super.size/zm);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		DrawQuadTex(money,ax-size/2,ay-size/2,size,size);
	}
}
