package iota.lib;

public class SingleBullet<A> implements Bullet {
	A value;

	public SingleBullet(A value) {
		this.value = value;
	}

	@Override
	public A getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getOld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public A getCurrent() {
		// TODO Auto-generated method stub
		return null;
	}

}
