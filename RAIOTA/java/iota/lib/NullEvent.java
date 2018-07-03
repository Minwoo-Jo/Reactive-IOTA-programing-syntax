package iota.lib;

public class NullEvent<A> implements EventHandler{
	A value;

	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		return true;
	}

}
