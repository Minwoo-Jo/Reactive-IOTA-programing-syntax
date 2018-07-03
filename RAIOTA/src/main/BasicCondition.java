package main;

import iota.lib.Predicate;

public class BasicCondition implements Predicate{

	public Boolean isTrue() {
		System.out.println("false");
		return false;
	}
	
}
