package iota.lib;

public class SingleBullet<A> implements Bullet {
	Atom value;


	
	public SingleBullet(Atom value) {
		this.value = value;
	}
	public Atom getValue() {
		return value;
	}
	public String getId() {
		return "S";
	}
	public Atom getOld() {
		return value;
	}
	public Atom getCurrent() {
		return value;
	}

}
