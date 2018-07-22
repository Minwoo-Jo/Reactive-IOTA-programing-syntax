package main;

import java.util.ArrayList;
import java.util.HashMap;

import nz.sodium.Listener;
import nz.sodium.Stream;

public class RegisteredDevices {
	private static RegisteredDevices rd;

	Door FrontDoor = new Door("FrontDoor");
	Door EntranceDoor = new Door("EntranceDoor");
	Light HallwayLight = new Light("HallwayLight");
	Phone Mom_Phone = new Phone("Mom_Phone");
	Phone Dad_Phone = new Phone("Dad_Phone");
	Phone Child_Phone = new Phone("Child_Phone");


	HashMap<String, Device> dMap = new HashMap();
	
	private RegisteredDevices() {
		dMap.put(FrontDoor.devName, FrontDoor);
		dMap.put(EntranceDoor.devName, EntranceDoor);
		dMap.put(HallwayLight.devName, HallwayLight);
		dMap.put(Mom_Phone.devName, Mom_Phone);
		dMap.put(Dad_Phone.devName, Dad_Phone);
		dMap.put(Child_Phone.devName, Child_Phone);
	}
	
	public static RegisteredDevices set() {
		if(rd==null)
			rd = new RegisteredDevices();
		
		return rd;
	}
	
	public Device device(String s) {
		return dMap.get(s);
	}
	
	

	public void print() {

		System.out.println("\nFrontDoor    |\tLock\t:: "+FrontDoor.Lock.current());
		System.out.println("             |\tStatus\t:: " +FrontDoor.Status.current());
		System.out.println("EntranceDoor |\tLock\t:: "+EntranceDoor.Lock.current());
		System.out.println("             |\tStatus\t:: "+EntranceDoor.Status.current());
		System.out.println("HallwayLight |\tSwitch\t:: "+HallwayLight.Switch.current());
		System.out.println("             |\t       ");
		System.out.println("Mom_Phone    |\tLocation:: "+Mom_Phone.Location.current());
		System.out.println("Dad_Phone    |\tLocation:: "+Dad_Phone.Location.current());
		System.out.println("Child_Phone  |\tLocation:: "+Child_Phone.Location.current());
		
		
		System.out.println("-------------------------------------------------------");
	}
}
