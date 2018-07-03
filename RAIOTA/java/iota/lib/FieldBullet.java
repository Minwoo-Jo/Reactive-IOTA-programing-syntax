package iota.lib;

public class FieldBullet <A> implements Bullet{
	Field field;
	A old;
	A current;

	public FieldBullet(Field f, A old, A current) {
		this.field = f;
		this.old = old;
		this.current = current;
	}
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return field;
	}

	@Override
	public A getOld() {
		// TODO Auto-generated method stub
		return old;
	}

	@Override
	public A getCurrent() {
		// TODO Auto-generated method stub
		return current;
	}

}
