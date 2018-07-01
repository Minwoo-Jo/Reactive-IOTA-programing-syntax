package iota.lib;

import nz.sodium.*;

public class Linker <A> extends Field{

	A command;
	
	
	public Linker(String fName, Object value) {
		super(fName, value);
		// TODO Auto-generated constructor stub
	}
	
	public Linker() {
		super(null);
	}
	
	public Linker(A value) {
		super(value);
	}
	
	
	public Form link(Form form) {
			
		return form.conn(this);
		
		
	}
	
	public Linker conv(Form form) {
		form.obs(this);
		
		return this;
	}
	
	public void change(Field a, A b) {
		this.command = b;
		send(a);
		sample();
	}

}
