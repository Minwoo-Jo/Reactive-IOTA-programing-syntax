package iota.lib;

import nz.sodium.*;

import java.util.ArrayList;
import java.util.Optional.*;

public class Field<A> {
	private A fName;
	private String id;

	private ArrayList<A> status = new ArrayList();;

	private StreamSink<SingleBullet<Atom>> inputStream;
	private Stream<Bullet<Atom>> inputFromArrow;

	private Stream<Atom> checkEffectiveness;

	private Stream<Atom> effectiveCommand;
	private Atom old = new Atom();
	private Atom now = new Atom();

	private Cell<Atom> current = new Cell<Atom>(null);

	private Stream<Atom> setOutput;
	private Stream<Bullet<Atom>> outputStream;

	public Field(A fName) {

		this.fName = fName;
		this.id = this.toString();
		inputStream = new StreamSink();
		inputFromArrow = new Stream().orElse(inputStream);
		checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x.getValue()))
				.map(x -> new Atom(x.getValue().get()));
		effectiveCommand = checkEffectiveness.filter(x -> status.contains(x.get()));
		current = effectiveCommand.hold(null);
		setOutput = Operational.updates(current);
		outputStream = setOutput.map(x -> new Wave(id(), new Atom(old.get()), new Atom(x.get())));

	}

	public void addCommand(SingleBullet<Atom> c) {

		if (status.isEmpty()) {
			current = checkEffectiveness.hold(c.getValue());
			now = new Atom(c.getValue().get());

		}
		status.add((A) c.getValue().get());
	}

	public Boolean isCorrectCommand(Atom x) {
		if (status.contains(x.get())) {
			old = new Atom(current().get());
			now = new Atom(x.get());
			return true;
		} else
			return false;
	}

	public Atom old() {
		return new Atom(old.get());
	}

	public Atom now() {
		return new Atom(now.get());
	}

	public Atom current() {
		return new Atom(current.sample().get());
	}

	public void change(Atom c) {
		if (isCorrectCommand(c)) {
			old = new Atom(current().get());
			inputStream.send(new SingleBullet(c));
		}

	}

	public Arrow shoot(Arrow a) {
		update();
		a.setInput(this);
		return a;
	}

	public Stream<Bullet<Atom>> input() {
		return this.inputFromArrow;
	}

	public Stream<Bullet<Atom>> output() {
		return this.outputStream;
	}

	public Stream<Atom> con() {
		return this.checkEffectiveness;
	}

	public void joinInput(Stream<Bullet<Atom>> s) {
		this.inputFromArrow = inputFromArrow.orElse(s);
		update();
	}

	public String id() {
		return this.id;
	}

	public void update() {
		this.checkEffectiveness = this.inputFromArrow.filter(x -> isCorrectCommand(x.getValue()))
				.map(x -> x.getValue());
		this.effectiveCommand = this.checkEffectiveness.filter(x -> status.contains(x.get()));
		this.current = effectiveCommand.hold(current());
		this.setOutput = Operational.updates(current);
		this.outputStream = setOutput.map(x -> new Wave(id(), new Atom(current.sample().get()), new Atom(x.get())));
	}
}
