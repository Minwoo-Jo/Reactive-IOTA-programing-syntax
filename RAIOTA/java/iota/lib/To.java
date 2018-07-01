package iota.lib;

public class To<A> implements EventHandler {

	A value;

	public To(A value) {
		this.value = value;
	}

	@Override
	public Boolean checkType(Field f) {

		return f.current().equals(value);

	}

}
