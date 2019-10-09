package data;

public abstract class DiseaseEntity extends Entity {

	public boolean sick = false;
	
	public DiseaseEntity(Entity host) {
		super(host);
	}

	public DiseaseEntity(int x, int y) {
		super(x, y);
	}

	public DiseaseEntity(int x, int y, float size) {
		super(x, y, size);
	}

	public boolean infect() {
		sick = true;
		return true;
	}

	@Override
	public void update(int tx, int ty, float zm) {
		super.update(tx, ty, zm, super.size);
	}
	
	@Override
	public void draw(int ax, int ay, float size) {
		
	}
	
}
