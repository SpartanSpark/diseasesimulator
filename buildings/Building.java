package buildings;

import data.Biome;
import data.Coordinate;
import data.Person;
import events.Event;

public abstract class Building extends Event {

	public int size = 5000, //just over 50m
			influence = 1000, //within ~10 meters of building
			lifeCycles = 0, //0 means you don't die, buildings don't die
			capacity = 25; //maximum of 25 people
	public Biome biome;
	
	public Building(int x, int y, Biome biome) {
		super(new Coordinate(x,y));
		super.affRange = this.size;
		super.attRange = this.size + this.influence;
		super.lifeCycles = this.lifeCycles;		
		super.building = true;
		this.biome = biome;
	}
	
	@Override
	public void affect(Person a) {
		a.building = true;
	}
	
	@Override
	public void unaffect(Person a) {
		a.building = false;
		a.buildingCycles = 0;
	}

	@Override
	public void draw(int ax, int ay, float zm) {
		
	}
}
