package events;

import data.Person;

public class Birthday extends Event {

	public Birthday(Person t) {
		super(t);
		super.affRange = 500;
		super.attRange = 50000;
		super.lifeCycles = 2000;
	}
	
	@Override
	public void affect(Person a) {
		a.bdParty = true;
	}
	
	@Override
	public void unaffect(Person a) {
		a.bdParty = false;
	}

}
