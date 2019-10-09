package data;

public abstract class Entity {

	public boolean alive, hosting, disease = false;
	public int x, y, cycles;
	public float size;
	public Entity host;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		hosting = false;
		alive = true;
		cycles = 0;
	}
	
	public Entity(int x, int y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
		hosting = false;
		alive = true;
		cycles = 0;
	}

	public Entity(Entity host) {
		this.host = host;
		hosting = true;
		alive = true;
		cycles = 0;
	}
	
	public void update() {
		if(alive) {
			cycles++;
		}		
	}
	
	public void update(int tx, int ty, float zm) {
		if(alive) {
			cycles++;
			if (hosting) draw((int) ((host.x - tx)*zm), (int) ((host.y - ty)*zm), size*zm);
			else draw(x, y, size);
		}
	}
	
	public void update(int tx, int ty, float zm, float size) {
		this.size = size;
		if(alive) {
			cycles++;
			if (hosting) draw((int) ((host.x - tx)*zm), (int) ((host.y - ty)*zm), size*zm);
			else draw(x - tx, y - ty, size*zm);
		}
	}
	
	public void die() {
		alive = false;
	}
	
	public void draw(int ax, int ay, float size) {
		
	}

	public boolean doesCollide(Entity t) {
		if(hosting) return Math.abs(host.x-t.x) <= size + t.size && Math.abs(host.y-t.y) <= size + t.size;
		else return Math.abs(x-t.x) <= size + t.size && Math.abs(y-t.y) <= size + t.size;
	}	
}
