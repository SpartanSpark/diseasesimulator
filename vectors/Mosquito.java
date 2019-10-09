package vectors;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.mosquito;

public class Mosquito extends Vector {

	public Mosquito(int x, int y) {
		super(x, y, 5, 10f);
		super.lifeCycles = 6500;
	}

	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		DrawQuadTex(mosquito,ax-size/2,ay-size/2,size,size);
	}
}