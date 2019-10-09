package items;

import java.util.concurrent.CopyOnWriteArrayList;

import data.Person;

public class Item extends DiseaseVector {

	public int hostCycles;
	public boolean hostDropped;
	
	public Item(int x, int y, float size) {
		super(x, y, size, 0);
		super.lifeCycles = 0;
		super.sick = false;
	}

	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm);
		if(hostDropped) hostCycles++;
		if(hostCycles > 60) {
			hostCycles = 0;
			hostDropped = false;
			super.host = null;
		}
		if(super.hosting && Math.random() > 0.9992) {
			super.x = host.x;
			super.y = host.y;
			super.hosting = false;
			hostCycles = 0;
			hostDropped = true;
		}
	}
	
	@Override
	public Item with(CopyOnWriteArrayList<Person> people) {
		super.people = people;
		return this;
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		
	}
	
	@Override
	public void infect(Person p) {
		if(super.sick && super.host == null || super.host != p) {
			super.infect(p);
			if(!p.sick && this.doesCollide(p)) {
				super.hosting = true;
				super.host = p;
			}
		}
	}

}
