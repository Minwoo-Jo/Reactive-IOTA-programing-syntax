package iota.lib;

public class FromTo<A> implements EventHandler {
	A from;
	A to;

	public FromTo(A from, A to) {
		this.from = from;
		this.to= to;
	}

	@Override
	public Boolean checkType(Bullet b) {
		// TODO Auto-generated method stub
		System.out.println(b.getOld());
		System.out.println(b.getCurrent());
		return b.getOld().equals(from)&&b.getCurrent().equals(to);
	}

}
