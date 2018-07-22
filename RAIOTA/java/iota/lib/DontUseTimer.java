package iota.lib;

import java.util.Optional;

import nz.sodium.Cell;
import nz.sodium.CellLoop;
import nz.sodium.Listener;
import nz.sodium.Stream;
import nz.sodium.StreamSink;
import nz.sodium.Transaction;
import nz.sodium.Unit;
import nz.sodium.time.MillisecondsTimerSystem;
import nz.sodium.time.TimerSystem;

public class DontUseTimer {
	public int num = 0;
	TimerSystem sys;
	Cell<Long> time;
	StreamSink<Unit> sMain;

	public Stream<Long> periodic(TimerSystem sys, long period) {
		Cell<Long> time = sys.time;
		CellLoop<Optional<Long>> oAlarm = new CellLoop<>();
		Stream<Long> sAlarm = sys.at(oAlarm);
		oAlarm.loop(sAlarm.map(t -> Optional.of(t + period)).hold(Optional.<Long>of(time.sample() + period)));
		return sAlarm;
	}

	public DontUseTimer() {
		sys = new MillisecondsTimerSystem();
		time = sys.time;
		sMain = new StreamSink<Unit>();
	}

	public void start() {
		Listener l = Transaction.run(() -> {
			long t0 = time.sample();
			Listener l1 = periodic(sys, 1000).listen(t -> new Thread() {
				public void run() {
				}
			}.start());
			return l1;
		});
		try {
			Thread.sleep(990);
		} catch (InterruptedException e) {
		}
		//sMain.send(Unit.UNIT);
		l.unlisten();

	}
}