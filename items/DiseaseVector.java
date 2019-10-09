package items;

import java.util.concurrent.CopyOnWriteArrayList;

import data.DiseaseEntity;
import data.Entity;
import data.Person;
import helpers.SmartAI;

public class DiseaseVector extends DiseaseEntity {
	
	public int range = 20, lifeCycles = 6500;
	public float size, speed;
	public float mx, my;
	public Entity t;
	public CopyOnWriteArrayList<Person> people;
	private SmartAI s;
	
	public DiseaseVector(int x, int y, float size) {
		super(x, y);
		this.size = size;
		this.speed = 1;
		s = new SmartAI(this);
		super.disease = true;
	}
	
	public DiseaseVector(int x, int y, float size, float speed) {
		super(x, y);
		this.size = size;
		this.speed = speed;
		s = new SmartAI(this);
		super.disease = true;
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm, size);
		if(super.alive) {
			//Move
			move();
			//Infect (comment when using gloves and stuff to be careful)
			if(super.sick && people != null) for(Person t : people) infect(t);
			//Death
			if(lifeCycles != 0 && cycles >= lifeCycles && Math.random() > 0.992) die();
		}
	}
	
	public DiseaseVector with(CopyOnWriteArrayList<Person> people) {
		this.people = people;
		return this;
	}
	
	public void move() {
		super.x += s.mx(null, t, null, (float) (Math.random() * speed));
		super.y += s.my();
	}
	
	public void infect(Person p) {
		if(t != null && !p.sick && Math.abs(p.x-x) + Math.abs(p.y-y) < Math.abs(t.x-x) + Math.abs(t.y-y)) t = p;
		if(!p.sick && this.doesCollide(p)) p.infect();
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		
	}

	@Override
	public boolean doesCollide(Entity t) {
		return Math.abs(super.x-t.x) <= size + t.size && Math.abs(super.y-t.y) <= size + t.size;
	}

}
