package data;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum Biome {

//	City(6000,2000,40,skyscraper, factory, restaurant),
//	Town(4500,1500,30,house, bank, grocer),
//	Country(1500,500,10,hut, farm, fishpond);

	City(600,2000,40,skyscraper, factory, restaurant),
	Town(450,1500,30,house, bank, grocer),
	Country(150,500,10,hut, farm, fishpond);
	
	public int buildSize, influence, capacity;
	public Texture residential, commercial, food;
	Biome(int buildSize, int influence, int capacity, Texture residential, Texture commercial, Texture food) {
		this.buildSize = buildSize;
		this.influence = influence;
		this.capacity = capacity;
		this.residential = residential;
		this.commercial = commercial;
		this.food = food;
	}
}
