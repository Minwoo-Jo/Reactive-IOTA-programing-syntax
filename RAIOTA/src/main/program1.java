package main;

import iota.lib.ECA;
import iota.lib.TimerArrow;
import iota.lib.Tuple;
import nz.sodium.Listener;
import iota.lib.Cmd;

public class program1 {
	public static void start() {
		RegisteredDevices rd = RegisteredDevices.set();
		rd.FrontDoor.Lock.lift(new ECA() {
			public Cmd apply(Object from, Object to) {
				if (from.equals(Cmd.unlock) && rd.FrontDoor.Lock().equals(Cmd.lock))
					return Cmd.on;

				else
					return null;

			}

		}, rd.HallwayLight.Switch);
		rd.FrontDoor.Lock.lift(new ECA() {
			public Cmd apply(Object from, Object to) {
				if (from.equals(Cmd.lock) && rd.FrontDoor.Lock().equals(Cmd.unlock))
					return Cmd.off;
				else
					return null;

			}

		}, rd.HallwayLight.Switch);

		rd.HallwayLight.Switch.lift(new ECA() {
			@Override
			public Cmd apply(Object from, Object to) {
				// TODO Auto-generated method stub
				return Cmd.start;
			}
		}, rd.FrontDoor.Timer);

		rd.FrontDoor.Timer.lift(new ECA() {

			@Override
			public Tuple apply(int time) {
				// TODO Auto-generated method stub
				if (time==3)
					return new Tuple(Cmd.close, Cmd.stop);
				else
					return null;
			}

		}, rd.FrontDoor.Status, rd.FrontDoor.Timer);
	}

}
