package iota.lib;

public class Wave <A> implements Bullet{
	String id;
	Atom old;
	Atom current;
	
	public Wave() {
		
	}
	

	public Wave(String id, Atom old, Atom current) {
		this.id = id;
		this.old = old;
		this.current = current;
	}

	public Atom getValue() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public Atom getOld() {
		// TODO Auto-generated method stub
		return old;
	}


	public Atom getCurrent() {
		// TODO Auto-generated method stub
		return current;
	}

}
