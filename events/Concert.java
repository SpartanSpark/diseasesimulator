package events;

import data.Person;

public class Concert extends Event {

	public Concert(Person t) {
		super(t);
		super.affRange = 500;
		super.attRange = 55000;
		super.lifeCycles = 3800;
	}
	
	@Override
	public void affect(Person a) {
		a.concert = true;
	}
	
	@Override
	public void unaffect(Person a) {
		a.concert = false;
	}

}
