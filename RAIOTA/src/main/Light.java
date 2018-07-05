package main;

import java.util.HashMap;

import iota.lib.Atom;
import iota.lib.Field;
import iota.lib.SingleBullet;

public class Light<A> extends Device{
	public Field<A> Switch;
	public Light(String devName) {
		this.devName = devName;
		Switch = new Field("Switch");

		addField(Switch);
		Switch.addCommand(new Atom("on"));
		Switch.addCommand(new Atom("off"));

	}

}

