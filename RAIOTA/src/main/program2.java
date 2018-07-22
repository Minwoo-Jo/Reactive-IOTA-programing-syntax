package main;

import iota.lib.ECA;
import iota.lib.Tuple;
import iota.lib.Cmd;

public class program2 {
	public static void start() {
		RegisteredDevices rd = RegisteredDevices.set();

		ECA phoneCheck1 = new ECA() {
			public Cmd apply(Object from, Object to) {
				if (rd.Mom_Phone.Location() == Cmd.away && rd.Dad_Phone.Location() == Cmd.away
						&& rd.Child_Phone.Location() == Cmd.away)
					return Cmd.start;
				else
					return null;
			};
		};
		ECA phoneCheck2 = new ECA() {
			public Tuple apply(int time) {
				if (time == 10)
					if (rd.Mom_Phone.Location() == Cmd.away && rd.Dad_Phone.Location() == Cmd.away
							&& rd.Child_Phone.Location() == Cmd.away)
						return new Tuple(Cmd.on, Cmd.stop);
					else
						return null;
				else 
					return null;
			};
		};
		
		rd.Mom_Phone.Location.lift(phoneCheck1, rd.HallwayLight.Timer);
		rd.Dad_Phone.Location.lift(phoneCheck1, rd.HallwayLight.Timer);
		rd.Child_Phone.Location.lift(phoneCheck1, rd.HallwayLight.Timer);
		
		rd.HallwayLight.Timer.lift(phoneCheck2, rd.HallwayLight.Switch, rd.HallwayLight.Timer);
		

	}
}
