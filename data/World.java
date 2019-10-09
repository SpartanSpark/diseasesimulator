package data;

import static helpers.Artist.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import buildings.Residential;
import buildings.Building;
import buildings.Commercial;
import buildings.Food;
import events.Birthday;
import events.Concert;
import events.Event;
import items.Item;
import items.Money;
import vectors.Mosquito;
import vectors.Rat;
import vectors.Vector;

public class World {

	private int size, tx, ty, startPop, scrollSpeed = 20;
	private float zoom;
	public static int curPop;
	public static int infected;
	private CopyOnWriteArrayList<Person> people;
	private CopyOnWriteArrayList<Sneeze> sneezes;
	private CopyOnWriteArrayList<Event> events;
	private CopyOnWriteArrayList<Vector> vectors;
	private CopyOnWriteArrayList<Item> items;
	private Entity target;
	
	public World(int size, int startPop) {
		this.size = size;
		this.startPop = startPop;
		tx = size/2;
		ty = size/2;
		zoom = 1f;
		people = new CopyOnWriteArrayList<Person>();
		sneezes = new CopyOnWriteArrayList<Sneeze>();
		events = new CopyOnWriteArrayList<Event>();
		vectors = new CopyOnWriteArrayList<Vector>();
		items = new CopyOnWriteArrayList<Item>();
		generate();
	}
	
	public void generate() {
		int i = 0;
		while (i < startPop) {
			int clustAmount = (int) Math.round(Math.sqrt(Math.random()) * 1);
//			int clustAmount = (int) Math.round(Math.sqrt(Math.random()) * 15);
			int cx = (int) (Math.sqrt(Math.random()) * size);
			int cy = (int) (Math.sqrt(Math.random()) * size);
			for(int k = 0; k < clustAmount; k++) {
				int rx = (int) (Math.sqrt(Math.random()) * scrollSpeed * 100);
				int ry = (int) (Math.sqrt(Math.random()) * scrollSpeed * 100);
				int amount = (int) Math.round(Math.sqrt(Math.random()) * 10);
				i+=amount;
				for(int j = 0; j < amount; j++) generatePerson(cx + rx + (int) (Math.random() * scrollSpeed), cy + ry + (int) (Math.random() * scrollSpeed), (float) (Math.random() * 35));
				if(Math.random() < 0.1) {
					amount = (int) Math.round(Math.sqrt(Math.random()) * 3);
					for(int j = 0; j < amount; j++) generateMoney(cx + rx + (int) (Math.random() * scrollSpeed), cy + ry + (int) (Math.random() * scrollSpeed));
				}
				if(Math.random() < 0.1) {
					amount = (int) Math.round(Math.sqrt(Math.random()) * 8);
					for(int j = 0; j < amount; j++) generateFoodI(cx + rx + (int) (Math.random() * scrollSpeed), cy + ry + (int) (Math.random() * scrollSpeed));
				}
			}
			if(Math.random() > 0.3) {
				generateResidential(cx,cy);
			}
			else if(Math.random() > 0.3) {
				generateFoodB(cx,cy);
				if(Math.random() > 0.1) {
					int amount = (int) Math.round(Math.sqrt(Math.random()) * 5 * size/(cx+cy));
					for(int j = 0; j < amount; j++) generateFoodI(cx + (int) (Math.random() * scrollSpeed), cy + (int) (Math.random() * scrollSpeed));
				}
			}
			else if(Math.random() > 0.3) {
				generateCommercial(cx,cy);
				if(Math.random() > 0.1) {
					int amount = (int) Math.round(Math.sqrt(Math.random()) * 2 * size/(cx+cy));
					for(int j = 0; j < amount; j++) generateMoney(cx + (int) (Math.random() * scrollSpeed), cy + (int) (Math.random() * scrollSpeed));
				}
			}
		}
		i = 0;
		while (i < startPop / 2) {
			int amount = (int) Math.round((Math.sqrt(Math.random() + 1) - 1) * 100);
			int rx = (int) (Math.sqrt(Math.random()) * size);
			int ry = (int) (Math.sqrt(Math.random()) * size);
			i+=amount;
			for(int j = 0; j < amount; j++) generateMosquito(rx + (int) (Math.random() * scrollSpeed), ry + (int) (Math.random() * scrollSpeed));
		}
		for(i = 0; i < startPop/100; i++) {
			Person target = people.get((int) (Math.round(Math.random() * (people.size() - 1))));
			target.infect();
		}
		for(i = 0; i < startPop/3; i++) {
			generateRat((int) (Math.random()*size), (int) (Math.random()*size));
		}
		
	}
	
	private void generateBirthday(Person p) {
		events.add(create(new Birthday(p)));
	}
	
	private void generateConcert(Person p) {
		events.add(create(new Concert(p)));
	}
	
	private void generateResidential(int x, int y) {
		if(x < size/2 && y < size/2) events.add(create(new Residential(x, y, Biome.Country)));
		if(x >= size/2 && y >= size/2) events.add(create(new Residential(x, y, Biome.City)));
		else events.add(create(new Residential(x, y, Biome.Town)));
	}
	
	private void generateCommercial(int x, int y) {
		if(x < size/2 && y < size/2) events.add(create(new Commercial(x, y, Biome.Country)));
		if(x >= size/2 && y >= size/2) events.add(create(new Commercial(x, y, Biome.City)));
		else events.add(create(new Commercial(x, y, Biome.Town)));
	}
	
	private void generateFoodB(int x, int y) {
		if(x < size/2 && y < size/2) events.add(create(new Food(x, y, Biome.Country)));
		if(x >= size/2 && y >= size/2) events.add(create(new Food(x, y, Biome.City)));
		else events.add(create(new Food(x, y, Biome.Town)));
	}
	
	private void generateRat(int x, int y) {
		vectors.add(create(new Rat(x,y)));
	}
	
	private void generateMosquito(int x, int y) {
		vectors.add(create(new Mosquito(x,y)));
	}
	
	private void generateMoney(int x, int y) {
		items.add(create(new Money(x,y)));
	}
	
	private void generateFoodI(int x, int y) {
		items.add(create(new items.Food(x,y)));
	}
	
	private void generatePerson(int x, int y, float age) {
		people.add(create(new Person(x,y,age)));
	}
	
	public void update() {
		System.out.println("Healthy: " + (curPop - infected) + ". Sick: " + infected);
		DrawQuadTex(country,-tx*zoom,-ty*zoom,(int)(zoom*size/2),(int)(zoom*size/2));
		DrawQuadTex(town,(size/2-tx)*zoom,-ty*zoom,(int)(zoom*size/2),(int)(zoom*size/2));
		DrawQuadTex(town,-tx*zoom,(size/2-ty)*zoom,(int)(zoom*size/2),(int)(zoom*size/2));
		DrawQuadTex(city,(size/2-tx)*zoom,(size/2-ty)*zoom,(int)(zoom*size/2),(int)(zoom*size/2));
		if(events != null) for(Event e : events) updateEvent(e);
		if(people != null) for(Person p : people) updatePerson(p);
		if(sneezes != null) for(Sneeze s : sneezes) updateSneeze(s);
		if(vectors != null) for(Vector v : vectors) updateVector(v);
		if(items != null) for(Item i : items) updateItem(i);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) tx += scrollSpeed / zoom;
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) tx -= scrollSpeed / zoom;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ty += scrollSpeed / zoom;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) ty -= scrollSpeed / zoom;
		if (Keyboard.isKeyDown(Keyboard.KEY_E) && Keyboard.next() && events.size() > 0) {
			target = events.get((int) (Math.round(Math.random() * (events.size() - 1)))).host;
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if (Keyboard.isKeyDown(Keyboard.KEY_B) && Keyboard.next()) {
			target = null;
			while (target == null || !(((Event) (target)).building)) target = events.get((int) (Math.round(Math.random() * (events.size() - 1))));
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if (Keyboard.isKeyDown(Keyboard.KEY_P) && Keyboard.next() && people.size() > 0) {
			target = people.get((int) (Math.round(Math.random() * (people.size() - 1))));
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.next() && infected > 0) {
			target = null;
			while (target == null || !(((Person) (target)).sick)) target = people.get((int) (Math.round(Math.random() * (people.size() - 1))));
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if ((Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.next()) || infected == 0) {
			Person target = people.get((int) (Math.round(Math.random() * (people.size() - 1))));
			target.infect();
		};
		if ((Keyboard.isKeyDown(Keyboard.KEY_V) && Keyboard.next())) {
			target = vectors.get((int) (Math.round(Math.random() * (vectors.size() - 1))));
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if ((Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.next())) {
			target = items.get((int) (Math.round(Math.random() * (items.size() - 1))));
			tx = target.x - Width/2;
			ty = target.y - Height/2;
		};
		if(tx < 0) tx = 0;
		if(ty < 0) ty = 0;
		if(tx >= size) tx = size - 1;
		if(ty >= size) ty = size - 1;
	}
	
	private void updateItem(Item i) {
		i.with(people).update(tx, ty, zoom);
	}
	
	private void updateEvent(Event e) {
		if(e.ready) events.remove(e);
		else e.with(people).update(tx, ty, zoom);
	}
	
	private void updateSneeze(Sneeze s) {
		if(!s.alive) sneezes.remove(s);
		else {
			for(Person t : people) if(!t.sick) if(s.infect(t));
			s.update(tx, ty, zoom);
		}
	}
	
	private void updatePerson(Person p) {
		if(!p.alive) people.remove(p);
		else {
			//contact
			if(p.sick) for(Person t : people) if(!t.sick) if (p.doesCollide(t)) t.infect();
			//sneeze (comment when covering nose)
			if(p.sick && (Math.random() > 0.995 / p.getSusceptibility())) sneezes.add(new Sneeze(p));
			//breed
			if(p.age >= 18) for(Person t : people) if(t.age >= 18) if(Math.random() > 0.9999) if (p.gender != t.gender && p.doesCollide(t)) create(p.breed(t));
			//wrap
			wrap(p);
			//normal update
			p.with(items).update(tx, ty, zoom);
			//birthday
			if(p.age <= 35) if(Math.random() > 0.9995) generateBirthday(p);
			//concert
			if(p.age <= 70) if(Math.random() > 0.9998) generateConcert(p);
		}
	}
	
	private void wrap(Entity e) {
		if(e.x < 0) e.x = size - 1;
		if(e.y < 0) e.y = size - 1;
		if(e.x >= size) e.x = 0;
		if(e.y >= size) e.y = 0;
	}
	
	private void updateVector(Vector v) {
		if(!v.alive) vectors.remove(v);
		else {
			v.with(people).update(tx,ty,zoom);
			wrap(v);
		}
	}
	
	private Person create(Person p) {
		curPop++;
		return p;
	}
	
	private Event create(Event e) {
		return e;
	}
	
	private Building create(Building b) {
		return b;
	}
	
	private Vector create(Vector v) {
		return v;
	}
	
	private Item create(Item i) {
		return i;
	}
}
