package iota.lib;

import nz.sodium.*;
import java.util.Optional.*;

public final class Field<A> extends CellSink<A> {
	A fName;
	
	A old;

	public Field(String fName, A value) {
		super(value);
		this.fName = value;
	}

	public Form link(Form form) {
		return form.conn(this);
	}

	public A current() {
		return this.sampleLazy().get();
	}
	public A old() {
		return this.old;
	}
	
	public void change(A value) {
		old = this.current();
		this.send(value);
		System.out.println(this.sample());
	}
}
