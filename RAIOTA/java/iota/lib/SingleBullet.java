package iota.lib;

public class SingleBullet<A> implements Bullet {
	Atom value;

	public SingleBullet() {
	}

	public SingleBullet(Atom value) {
		this.value = new Atom(value.get());
	}

	public Atom getValue() {
		return value;
	}


	public Atom getOld() {
		return value;
	}

	public Atom getCurrent() {
		return value;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
