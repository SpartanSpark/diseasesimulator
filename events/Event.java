package events;

import java.util.concurrent.CopyOnWriteArrayList;

import data.Entity;
import data.Person;

public abstract class Event extends Entity {

	public boolean ready, building = false;
	public CopyOnWriteArrayList<Person> people;
	public int capacity = 0, affRange = 50, attRange = 100, lifeCycles = 20;
	
	public Event(Entity t) {
		super(t);
		ready = false;
	}
	
	public Event with(CopyOnWriteArrayList<Person> people) {
		this.people = people;
		return this;
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update();
		if(super.alive) {
			draw((int) ((super.host.x - tx)*zm), (int) ((super.host.y - ty)*zm), zm);
			int curPeople = 0;
			for(Person a : people) if(attract(a, curPeople)) curPeople++;
			if(lifeCycles > 0 && cycles > Math.random()*40+lifeCycles) super.die();
		}
		else for(Person a : people) detract(a);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		
	}
	
	public void affect(Person a) {
		
	}
	
	public void unaffect(Person a) {
		
	}
	
	public boolean attract(Person a, int curPeople) {
		boolean applied = false;
		if((available(curPeople) && (a.t == null || a.t == super.host || (super.host.x - a.t.x) + (super.host.y - a.t.y) >= 0) && 
				Math.abs(super.host.x - a.x) <= attRange && Math.abs(super.host.y - a.y) <= attRange)) {
			if (a != super.host) {
				a.t = super.host;
				applied = true;
			}
			if (a == super.host) a.t = null;
			if(Math.abs(super.host.x - a.x) <= affRange && Math.abs(super.host.y - a.y) <= affRange) {
				a.t = null;
				affect(a);
			} else {
				unaffect(a);
			}
		}
		return applied;
	}
	
	public void detract(Person a) {
		if(Math.abs(super.host.x - a.x) <= affRange && Math.abs(super.host.y - a.y) <= affRange) unaffect(a);
		if(a.t == super.host) a.t = null;
		ready = true;
	}
	
	public boolean available(int people) {
		if(capacity == 0) return true;
		else return capacity > people;
	}
}
