package iota.lib;

public class Command<A> {
	A value;
	
	public Command(A value) {
		this.value = value;
	}
	
	public A get() {
		return value;
	}

}