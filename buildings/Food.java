package buildings;

import static helpers.Artist.DrawQuadTex;

import data.Biome;

public class Food extends Building {

	public Food(int x, int y, Biome biome) {
		super(x, y, biome);
		super.size = biome.buildSize;
		super.influence = biome.influence;
		super.capacity = biome.capacity;
	}
 
	@Override
	public void draw(int ax, int ay, float zm) {
		DrawQuadTex(biome.food,ax-super.size/2,ay-super.size/2,super.size*zm,super.size*zm);
	}

}
