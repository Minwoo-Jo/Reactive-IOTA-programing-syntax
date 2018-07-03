package iota.lib;

public final class From<A> implements EventHandler{

	Atom from;
	
	public From(Atom from) {
		this.from = from;
	}
	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		return b.getOld().get().equals(from.get());
	}
}
