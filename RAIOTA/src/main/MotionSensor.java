package main;

import java.util.HashMap;

import iota.lib.Atom;
import iota.lib.Field;
import iota.lib.SingleBullet;

public class MotionSensor<A> extends Device{
	public Field<A> Sensor;
	public MotionSensor(String devName) {
		this.devName = devName;
		Sensor = new Field("Sensor");

		addField(Sensor);
		Sensor.addCommand(new Atom("no detect"));
		Sensor.addCommand(new Atom("detected"));

	}
}
