package main;

import java.util.HashMap;

import iota.lib.*;

public class Light extends Device {
	Field Switch;
	Timer Timer;
	Light(String dName) {
		super(dName);
		Switch = new Field("Switch");
		
		Switch.add(Cmd.off);
		Switch.add(Cmd.on);
		addField(Switch);
		
		Timer = new Timer("Timer");
		Timer.add(Cmd.stop);
		Timer.add(Cmd.start);
		addField(Timer);

	}
	public Cmd Switch() {
		return Switch.current();
	}

}
