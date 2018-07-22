package main;

import java.util.HashMap;

import iota.lib.*;

public class Device<A> {
	String devName;
	HashMap<A, Field> fieldMap = new HashMap<>();
	Device(String dName) {
		this.devName = dName;
	
	}

	public Field field(String s) {
		return fieldMap.get(s);
	}
	
	public void addField(Field f) {
		this.fieldMap.put((A) f.fname(), f);
	}
}
