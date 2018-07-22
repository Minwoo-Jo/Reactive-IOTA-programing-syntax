package iota.lib;

public interface ECA<A,B> {
	default Cmd apply(A from, B to) {
		return null;
	}

	default Tuple apply(int time) {
		return null;
	}
	
}
