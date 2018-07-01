package iota.lib;

public class FromTo<A> implements EventHandler {
	A from;
	A to;

	public FromTo(A from, A to) {
		this.from = from;
		this.to= to;
	}

	@Override
	public Boolean checkType(Field f) {

		return f.old().equals(from) && f.current().equals(to);
	}

}
