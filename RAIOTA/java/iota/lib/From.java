package iota.lib;

public class From<A> implements EventHandler{

	A value;
	
	public From(A value) {
		this.value = value;
	}

	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		return true;
	}

}
