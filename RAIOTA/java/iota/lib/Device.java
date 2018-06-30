package iota.lib;

import java.util.HashMap;

public abstract class Device {
	String name;
	HashMap<String, Field> fMap = new HashMap();
	
	public Device(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Field getField(String fname) {
		
		return fMap.get(fname);
	}
	
	public void addField(String fName, Field f) {
		fMap.put(fName, f);
	}

}
