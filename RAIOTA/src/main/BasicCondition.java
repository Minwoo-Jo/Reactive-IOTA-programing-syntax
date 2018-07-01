package main;

import iota.lib.Condition;

public class BasicCondition implements Condition{

	public Boolean isTrue() {
		System.out.println("false");
		return false;
	}
	
}
