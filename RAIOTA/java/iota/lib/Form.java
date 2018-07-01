package iota.lib;

import nz.sodium.*;

public class Form {
	
	EventHandler e;
	Condition c;
	Command a;
	

	Stream lf;
	Stream event;
	Stream action;

	Listener perform;

	Field pf;

	public Form() {
		e = new NullEvent();
		c = new TrueCondition();
		a = new Command(null);
		
		

	}

	public Form conn(Field f) {

		this.pf = f;

		return this;
	}

	public Form obs(Field f) {

		this.lf = Operational.updates(f);

		event = lf.filter(Boolean -> e.checkType(f));

		action = event.map(x -> c).filter(Boolean -> c.isTrue());

		perform = action.listen(x -> new Thread() {
			public void run() {
				pf.change(a.get());
			}
		}.start());

		return this;
	}

	public Form set(Condition c) {

		this.c = c;

		return this;
	}

	public Form def(EventHandler e) {
		this.e = e;
		return this;
	}

	public Form shoot(Command a) {
		
		this.a = a;
		return this;
	}

}
