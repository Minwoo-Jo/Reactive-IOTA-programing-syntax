package iota.lib;

public class To<A> implements EventHandler {

	A value;

	public To(A value) {
		this.value = value;
	}


	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		System.out.println(b.getCurrent());
		return b.getCurrent().equals(value);
	}

}
