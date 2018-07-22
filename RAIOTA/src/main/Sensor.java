package main;

import java.util.HashMap;

import iota.lib.*;

public class Sensor extends Device {
	Field Switch;
	Timer Timer;
	Sensor(String dName) {
		super(dName);
		Field Switch = new Field("Switch");
		Switch.add(Cmd.on);
		Switch.add(Cmd.off);
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
