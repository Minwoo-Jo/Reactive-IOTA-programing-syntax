package iota.lib;

public class To<A> implements EventHandler {

	Atom to;

	public To(Atom to) {
		this.to = to;
	}


	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		System.out.println(b.getOld().get() + " " + b.getCurrent().get());
		return b.getCurrent().get().equals(to.get());
	}

}
