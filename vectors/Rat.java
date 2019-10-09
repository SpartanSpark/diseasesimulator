package vectors;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.rat;

public class Rat extends Vector {

	public Rat(int x, int y) {
		super(x, y, 15);
		super.lifeCycles = 65000;
	}

	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		DrawQuadTex(rat,ax-size/2,ay-size/2,size,size);
	}
}
