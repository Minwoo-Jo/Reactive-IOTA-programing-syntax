package iota.lib;

import java.util.ArrayList;

public class Form {

	ArrayList<Field> pf = new ArrayList<>();
	Field lf;

	Condition c;
	
	public Form() {
		pf = new ArrayList<>();
		lf = new Field<String>(null, "A");
	}

	public Form conn(Field f) {
		pf.add(f);
		return this;
	}


	public void obs(Field f) {
		lf = f;
	}


	public Form apply(Condition c) {
		
		this.c = c;
		return new Form();
	}

}
