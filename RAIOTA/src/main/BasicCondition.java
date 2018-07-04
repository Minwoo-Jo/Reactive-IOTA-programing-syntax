package main;

import iota.lib.Field;
import iota.lib.Predicate;

public class BasicCondition implements Predicate{

	public Boolean isTrue() {
		System.out.println("false");
		return false;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
