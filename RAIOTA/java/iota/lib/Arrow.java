package iota.lib;

import nz.sodium.*;

public class Arrow <A>{

	EventHandler e = new NullEvent();
	Predicate p = new TrueCondition();
	Bullet<Atom> c = new SingleBullet(null);

	Stream<Bullet<Atom>> input = new Stream();
	Stream<Bullet<Atom>> check = new Stream();
	Stream<Predicate> connecter = new Stream();
	Stream<Bullet<Atom>> output;

	public Arrow() {
		check = input.filter(x -> e.checkType(x));
		connecter = check.map(x -> p).filter(x -> x.isTrue());
		output = connecter.map(x -> c);
	}

	public void setHandler(EventHandler e) {
		this.e = e;
		update();
	}

	public void setCondition(Predicate p) {
		this.p = p;
		update();
	}

	public void setCommand(Bullet<Atom> c) {
		this.c = new SingleBullet(new Atom("unlocked"));
		Transaction.runVoid(() -> {
			update();
		});

	}

	public Arrow setInput(Field f) {

		Transaction.runVoid(() -> {
			this.input = input.orElse(f.output());
			update();

		});
		return this;
	}

	public void setOutput(Field f) {

		f.joinInput(this.getOutput());
	}

	public Field shoot(Field f) {
		f.joinInput(this.getOutput());

		return f;
	}

	public Stream<Bullet<Atom>> getInput() {
		return this.input;
	}

	public Stream<Bullet<Atom>> getOutput() {
		return this.output;
	}

	public Stream<Predicate> getConnecter() {
		return this.connecter;
	}

	public void update() {
		check = input.filter(x -> e.checkType(x));
		connecter = check.map(x -> p).filter(x -> x.isTrue());
		output = connecter.map(x -> c);

	}
}
