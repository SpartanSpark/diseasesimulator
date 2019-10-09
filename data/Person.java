package data;

import static helpers.Artist.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

import helpers.SmartAI;
import items.Item;
import helpers.Artist;

public class Person extends DiseaseEntity {

	public boolean bdParty, concert, building;
	public float age, size, mx, my;
	public int buildingCycles;
	public Person child;
	public Entity t;
	public String gender;
	private SmartAI s;
	private CopyOnWriteArrayList<Item> items;
	
	public Person(int x, int y, float age) {
		super(x, y);
		if(Math.random() > 0.5) gender = "male";
		else gender = "female";
		super.cycles = (int) (age * 100);
		this.age = age;
		grow();
		s = new SmartAI(this);
	}
	
	@Override
	public boolean infect() {
		if(!super.sick) {
			World.infected++;
			return super.infect();
		}
		return false;
	}
	
	public float getSusceptibility() {
		if(age < 4 || age > 65) return 2;
		else return 1;
	}
	
	public void infectthis(DiseaseEntity d) {
		d.infect();
	}
	
	public Person with(CopyOnWriteArrayList<Item> items) {
		this.items = items;
		return this;
	}
	
	public Person breed(Person parent) {
		Person child = new Person((super.x+parent.x)/2,(super.y+parent.y)/2,0);
		if(super.sick || parent.sick) child.sick = true;
		this.child = child;
		parent.child = child;
		return child;
	}
	
	@Override
	public void die() {
		super.die();
		World.curPop--;
		if(super.sick) World.infected--;
	}
	
	private void age() {
		age+=0.00002777777;
	}
	
	private void move() {
		mx = 0;
		my = 0;
		if(t != null) {
			if(t.x - super.x != 0) mx += (float) ((t.x - super.x)*(Math.random()*0.5)/Math.abs(t.x - super.x));
			if(t.y - super.y != 0) my += (float) ((t.y - super.y)*(Math.random()*0.5)/Math.abs(t.y - super.y));
		}
		if(child != null && child.age < 4) {
			if(child.x - super.x!= 0) mx += (float) ((child.x - super.x)*(Math.random()*0.25)/Math.abs(child.x - super.x));
			if(child.y - super.y!= 0) my += (float) ((child.y - super.y)*(Math.random()*0.25)/Math.abs(child.y - super.y));
		}
		if(super.cycles % 23 == 0 || super.cycles % 41 == 0) {
			mx += (float) ((Math.random() * 1) - 0.25);
			my += (float) ((Math.random() * 1) - 0.25);
		}
		if(t == null) {
			mx *= 2;
			my *= 2;
		}
		super.x += s.mx(null, t, child, (float) (Math.random()*2));
		super.y += s.my();
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, size);
		if(building) buildingCycles++;
		if(alive) {
			if(super.sick && items != null) for(Item i : items) if(this.doesCollide(i)) i.infect();
//			if(age >= 65 && Math.random() > 0.99992) die();
//			if(super.sick && Math.random() > 0.9999) die();
			age();
			grow();
			move();
			draw(super.x-tx,super.y-ty,size*zm);
			
//			Sanitizer
//			if((super.cycles % 2493 == 0 || super.cycles % 2171 == 0) && Math.random() > 0.97) {
//				if(super.sick) World.infected--;
//				super.sick = false;
//			}
			
//			Washing Hands
//			if((buildingCycles % 7493 == 100 || buildingCycles % 8171 == 100) && Math.random() > 0.95) {
//				if(super.sick) World.infected--;
//				super.sick = false;
//			}
		}
	}
	
	private void grow() {
		if (age < 35) size = (float) age * 2;
		else size = 35 * 2;	
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		if(ax >= 0 && ax < Width && ay >= 0 && ay < Height) {
			if(bdParty) DrawQuadTex(Artist.birthday, ax-size/4,ay-size,size/2,size/2);
			if(concert) DrawQuadTex(Artist.concert, ax-size/4,ay-size,size/2,size/2);
			DrawQuadTex(findTex(), ax-size/2,ay-size/2,size,size);
		}
	}
	
	private Texture findTex() {
		if(age < 4) {
			if(gender == "male" && sick) return maleChildSick;	
			if(gender == "male" && !sick) return maleChild;	
			if(gender == "female" && sick) return femaleChildSick;	
			if(gender == "female" && !sick) return femaleChild;	
		} else if(age > 65) {
				if(gender == "male" && sick) return maleOldSick;	
				if(gender == "male" && !sick) return maleOld;	
				if(gender == "female" && sick) return femaleOldSick;	
				if(gender == "female" && !sick) return femaleOld;	
		} else {
			if(gender == "male" && sick) return maleSick;	
			if(gender == "male" && !sick) return male;	
			if(gender == "female" && sick) return femaleSick;	
			if(gender == "female" && !sick) return female;	
		}
		return black;
	}
	
}
