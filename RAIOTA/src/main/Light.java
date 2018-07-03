package main;

import iota.lib.Device;
import iota.lib.Field;

public class Light extends Device{
	
	public Light(String name) {
		super(name);
		

		Field switch2 = new Field("switch", "off");
		addField("switch", switch2);
		
	}

}
