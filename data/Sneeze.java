package data;

import static helpers.Artist.*;

public class Sneeze extends Entity {
	
	public Sneeze(Entity host) {
		super(host);
		super.host = host;
	}
	
	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm, (3 * super.cycles) + 3);
		if(super.alive) {
			draw(super.host.x-tx,super.host.y-ty,super.size*zm);
			if(super.cycles > Math.random()*20+50) die();
		}
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		if(ax >= 0 && ax < Width && ay >= 0 && ay < Height) for(int i = 0; i < cycles/40; i++) DrawQuadTexRot(sneeze, ax + (int) (Math.random()*size-size/2), ay + (int) (Math.random()*size-size/2), 4, 4, (float) (Math.random() * 360));
	}
	
	public boolean infect(Person p) {
		if(super.alive && this.doesCollide(p) && Math.random() > (0.8/p.getSusceptibility())) {
			p.infect();
			return true;
		}
		return false;
	}

	@Override
	public boolean doesCollide(Entity t) {
		return Math.abs(super.x-t.x) <= 1.5 * super.cycles + t.size && Math.abs(super.y-t.y) <= 1.5 * super.cycles + t.size;
	}
}
