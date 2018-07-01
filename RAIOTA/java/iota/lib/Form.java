package iota.lib;

import java.util.ArrayList;

import nz.sodium.*;

public class Form {

	Field pf;

	Condition c;

	Stream lf;
	Stream handler;

	Listener shoot;

	public Form() {
		pf = new Field(null, null);

		lf = new Stream();
		handler = lf.map(x -> c).filter(Boolean -> c.isTrue());

		shoot = handler.listen(x -> new Thread() {
			public void run() {
				pf.change("A");
			}
		}.start());

	}

	public Form conn(Field f) {
		pf = f;
		return this;
	}

	public void obs(Field f) {
		this.lf = lf.orElse((Operational.updates(f)));
	}

	public Form apply(Condition c) {

		this.c = c;
		return this;
	}

}
