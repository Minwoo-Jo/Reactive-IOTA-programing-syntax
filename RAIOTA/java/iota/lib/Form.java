package iota.lib;

import nz.sodium.*;

public class Form {
	
	EventHandler e;
	Condition c;
	Command a;
	

	Stream<Field> lf;
	Stream<Field> event;
	Stream<Field> action;

	Listener perform;

	Field pf;

	public Form() {
		lf = new Stream();
		e = new NullEvent();
		c = new TrueCondition();
		a = new Command(null);
		
		

	}

	public Form conn(Field f) {

		this.pf = f;

		return this;
	}


	public Form obs(Field f) {

		this.lf = lf.orElse((Operational.updates(f).map(x -> f)));

		event = lf.filter(x -> e.checkType(x));

		action = event.map(x -> pf).filter(Boolean -> c.isTrue());

		perform = action.listen(x -> new Thread() {
			public void run() {
				x.change(x, a.get());
			}
		}.start());

		return this;
	}
	
	public Form chain(Form f) {
		
		this.conn(new Linker().set(pf, a.get()).conv(f));
		
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
