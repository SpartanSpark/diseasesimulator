package buildings;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.hospital;

import data.Biome;
import data.Person;

public class Hospital extends Building {

	public Hospital(int x, int y) {
		super(x, y, Biome.City);
		super.size = 8000;
		super.influence = 4000;
		super.capacity = 800;
	}
	
	@Override
	public void affect(Person p) {
		p.sick = false;
	}
	
	@Override
	public void draw(int ax, int ay, float zm) {
		DrawQuadTex(hospital,ax-super.size/2,ay-super.size/2,super.size*zm,super.size*zm);
	}
}
