package iota.lib;

import java.util.ArrayList;

import nz.sodium.*;

public class Arrow<A> {

	private EventHandler e = new NullEvent();
	private Predicate p = new TrueCondition();
	private Bullet<Atom> c;
	private Stream<Bullet<Atom>> input = new Stream();
	private Stream<Bullet<Atom>> check = new Stream();
	private Stream<Predicate> connecter = new Stream();
	private Stream<Bullet<Atom>> output;

	private ArrayList<Field> linkedField = new ArrayList();

	public Arrow() {
		c = new SingleBullet();
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
		this.c = new SingleBullet(c.getValue());
		update();
		

	}

	public Arrow setInput(Field f) {
		this.input = input.orElse(f.output());
		update();
		return this;
	}

	public void setOutput(Field f) {

		f.joinInput(this);
	}

	public Field shoot(Field f) {
		linkedField.add(f);
		f.joinInput(this);
		update();
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
		for (Field f : linkedField) {
			f.joinInput(this);
		}

	}
}
