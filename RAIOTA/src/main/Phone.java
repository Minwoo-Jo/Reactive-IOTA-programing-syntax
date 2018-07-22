package main;

import iota.lib.Cmd;
import iota.lib.Field;
import iota.lib.Timer;

public class Phone extends Device {
	Field Location;

	Phone(String dName) {
		super(dName);
		Location = new Field("Location");

		Location.add(Cmd.home);
		Location.add(Cmd.away);
		
		addField(Location);
	}
	
	public Cmd Location() {
		return Location.current();
	}

}
