package main;

import java.util.HashMap;

import iota.lib.*;

public class Door extends Device{
	Field Status;
	Field Lock;
	Timer Timer;
	Door(String dName) {
		super(dName);

		Status = new Field("Status");
		Status.add(Cmd.open);
		Status.add(Cmd.close);
		addField(Status);
		
		Lock = new Field("Lock");
		Lock.add(Cmd.lock);
		Lock.add(Cmd.unlock);
		addField(Lock);
		
		Timer = new Timer("Timer");
		Timer.add(Cmd.stop);
		Timer.add(Cmd.start);
		addField(Timer);

		
	}


	public Cmd Status() {
		return Status.current();
	}
	
	
	public Cmd Lock() {
		return Lock.current();
	}

}
