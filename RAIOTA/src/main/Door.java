package main;

import java.util.HashMap;

import iota.lib.*;


public class Door<A> extends Device{
	public Field<A> Status;
	public Field<A> Lock;
	public Door(String devName) {
		this.devName = devName;
		Status = new Field("Status");
		Lock = new Field("Lock");

		addField(Status);
		Status.addCommand(new Atom("open"));
		Status.addCommand(new Atom("close"));
		
		addField(Lock);
		Lock.addCommand(new Atom("locked"));
		Lock.addCommand(new Atom("unlocked"));
		

	}

}

