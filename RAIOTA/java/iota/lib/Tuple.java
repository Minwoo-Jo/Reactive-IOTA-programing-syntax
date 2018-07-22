package iota.lib;

public class Tuple<A,B,C> {
	A first = null;
	B second =null;
	int time;
	public Tuple(A old, B current) {
		this.first = old;
		this.second = current;
	}
	
	public Tuple(int time) {
		this.time = time;
	}
	
	public A fst() {
		return first;
	}
	
	public B snd() {
		return second;
	}
	public int time() {
		return time;
	}

}
