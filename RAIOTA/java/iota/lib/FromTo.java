package iota.lib;

public final class FromTo<A> implements EventHandler {
	Atom from;
	Atom to;

	public FromTo(Atom from, Atom to) {
		this.from = from;
		this.to= to;
	}

	@Override
	public Boolean checkType(Bullet b) {

		return b.getOld().get().equals(from.get())&&b.getCurrent().get().equals(to.get());
	}
}
