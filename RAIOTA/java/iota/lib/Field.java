package iota.lib;

import nz.sodium.*;
import java.util.Optional.*;

public class Field<A> extends CellSink<A> {
	A fName;
	
	A old;

	public Field(A fName, A value) {
		super(value);
		this.fName = fName;
	}
	
	public Field() {
		super(null);
	}
	public Field(A value) {
		super(value);
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
		this.sample();
	}
	
	public void change(A x, A value) {
		old = this.current();
		this.send(value);
		this.sample();
	}
	
	public Field set(A f , A c ) {
		
		return new Field(c, f);
	}
	
	public Field conv(Form f) {
		f.obs(this);
		
		return this;
	}
}
