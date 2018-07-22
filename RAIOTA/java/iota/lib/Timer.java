package iota.lib;

import java.util.HashMap;

import main.RegisteredDevices;
import nz.sodium.Cell;
import nz.sodium.Listener;
import nz.sodium.Operational;
import nz.sodium.Stream;
import nz.sodium.StreamSink;
import nz.sodium.Transaction;
import nz.sodium.time.MillisecondsTimerSystem;
import nz.sodium.time.TimerSystem;

public class Timer extends Field {
	private int count;
	TimerSystem sys;
	Cell<Long> time;
	
	Long startTime;

	private Stream<Cmd> input = new Stream();
	private Listener timer = new Listener();
	private StreamSink<Tuple> output = new StreamSink();
	private HashMap<Arrow, Field> inMap = new HashMap();
	private HashMap<TimerArrow, Field> hMap = new HashMap();

	public Timer(Object fName) {
		super(fName);
		sys = new MillisecondsTimerSystem();
		time = sys.time;
		count = 0;
		input = new Stream();
		for (TimerArrow a : hMap.keySet())
			input.orElse(a.getTimerOutput());
		setTimer();
	}

	public TimerArrow shoot(TimerArrow a) {
		if (!hMap.containsKey(a))
			hMap.put(a, a.outputField);
		a.setInput(this);
		return a;
	}

//	public void lift(ECA eca, Field f) {
//		this.shoot(new TimerArrow().set(eca).shoot(f));
//		update();
//
//	}
	public void lift(ECA eca, Field f, Timer t) {
		this.shoot(new TimerArrow().set(eca).shoot(f).shoot(t).update());
		update();
	}

	public Stream<Cmd> input() {
		return this.input;
	}

	public Stream<Tuple> output() {
		return this.output;
	}

	public void joinInput(Arrow a) {
		if (!this.inMap.containsKey(a))
			inMap.put(a, this);
		else {
			inMap.remove(a);
			inMap.put(a, this);
		}
		//update();
	}

	private void update() {
		this.input = new Stream();
		for (Arrow a : inMap.keySet()) {
			if(a.getClass().getSimpleName().equals("Arrow"))
				this.input = input.orElse(a.getOutput());
			else if(a.getClass().getSimpleName().equals("TimerArrow"))
				this.input = input.orElse(((TimerArrow) a).getTimerOutput());
		}
		setTimer();
	}

	public void setTimer() {
		timer.unlisten();
		timer = Transaction.run(() -> {
			return input.listen(x -> new Thread() {
				public void run() {
					if (x == Cmd.start) {
						 startTime = time.sample()/1000;
						 output.send(new Tuple(0));
					
					} else if (x == Cmd.stop) {
						System.out.println("timer " + x);
						this.stop();
						
					} else if (x != Cmd.stop) {
						try {
							sleep(990);
							System.out.println((time.sample()/1000-startTime));
							output.send(new Tuple((int) (time.sample()/1000-startTime)));
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}.start());
		});
	}
}
