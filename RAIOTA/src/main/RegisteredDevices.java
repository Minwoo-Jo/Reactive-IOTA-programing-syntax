package main;

import nz.sodium.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional.*;
import iota.lib.*;

public class RegisteredDevices {
	public Door EntranceDoor;
	public Door KitchenDoor;
	public Light HallwayLight;
	public Light AilseLight;
	public MotionSensor MotionSensor;
	
	
	
	public HashMap<String, Device> map = new HashMap();
	
	public RegisteredDevices() {
		EntranceDoor = new Door("EntranceDoor");
		KitchenDoor = new Door("KitchenDoor");
		
		HallwayLight = new Light("HallwayLight");
		AilseLight = new Light("AilseLight");
		
		//MotionSensor = new MotionSensor("MotionSensor");
		
		map.put("EntranceDoor", EntranceDoor);
		map.put("KitchenDoor", KitchenDoor);
		map.put("HallwayLight", HallwayLight);
		map.put("AilseLight", AilseLight);
	}
	
	public void printAllState() {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(EntranceDoor.getName());
		System.out.println(EntranceDoor.getField());
		
		System.out.println(KitchenDoor.getName());
		System.out.println(KitchenDoor.getField());
		
		System.out.println(HallwayLight.getName());
		System.out.println(HallwayLight.getField());
		
		System.out.println(AilseLight.getName());
		System.out.println(AilseLight.getField());
		/*
		System.out.println(MotionSensor.getName());
		System.out.println(MotionSensor.getField("Sensor").name());
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		*/
		}

	

	
}

