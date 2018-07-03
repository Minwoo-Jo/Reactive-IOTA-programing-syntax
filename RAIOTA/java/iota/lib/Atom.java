package iota.lib;

public final class Atom <A>{
	A value = null;
	public Atom(A value) {
		this.value = value;
	}
	public Atom() {
	}
	public A get() {
		return value;
	}
}
