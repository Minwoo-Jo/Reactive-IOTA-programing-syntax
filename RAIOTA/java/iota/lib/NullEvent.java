package iota.lib;

public class NullEvent<A> implements EventHandler{
	A value;
	@Override
	public Boolean checkType(Field f) {
		
		return true;
	}

}
