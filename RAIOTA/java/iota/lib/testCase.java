package iota.lib;

public class testCase implements Predicate{

	Field f;
	Atom a;
	
	public testCase(Field f, Atom a) {
		this.f = f;
		this.a = a;
	}
	
	public Boolean isTrue() {
		if(f.now().get().equals(a.get())) {
					return true;
		}
		else
			return false;
	}

}
