package iota.lib;

import nz.sodium.*;

import java.util.ArrayList;
import java.util.Optional.*;

public class Field<A> {
	private A fName;
	private String id;
	private ArrayList<A> status = new ArrayList();

	private StreamSink<SingleBullet<Atom>> inputStream;
	private Stream<Bullet<Atom>> inputFromArrow;
	private Stream<Atom> checkEffectiveness;
	private Stream<Atom> effectiveCommand;
	private Atom old = new Atom();
	private Atom current = new Atom();
	private Cell<Atom> currentCell = new Cell<Atom>(null);
	private Stream<Atom> setOutput;
	private Stream<Bullet<Atom>> outputStream;

	private ArrayList<Arrow> linkedArrow = new ArrayList();

	public Field(A fName) {

		this.fName = fName;
		this.id = this.toString();
		inputStream = new StreamSink();
		inputFromArrow = new Stream().orElse(inputStream);
		checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x.getValue()))
				.map(x -> new Atom(x.getValue().get()));
		effectiveCommand = checkEffectiveness.map(x->x);
		currentCell = effectiveCommand.hold(null);
		setOutput = Operational.updates(currentCell);
		outputStream = setOutput.map(x -> new Wave(id(), old(), x));

	}

	public void addCommand(Atom c) {

		if (status.isEmpty()) {
			currentCell = checkEffectiveness.hold(c);
			current = c;

		}
		status.add((A) c.get());
	}

	private Boolean isCorrectCommand(Atom x) {
		if (status.contains(x.get())) {
			old = currentCellValue();
			current = x;
			return true;
		} else
			return false;

	}

	public Atom old() {
		return new Atom(old.get());
	}

	public Atom current() {
		return new Atom(current.get());
	}

	private Atom currentCellValue() {
		return new Atom(currentCell.sample().get());
	}

	public void change(Atom c) {
		if (status.contains(c.get()) && !current.get().equals(c.get())) {
			old = current();
			inputStream.send(new SingleBullet(c));
		}
	}

	public Arrow shoot(Arrow a) {
		if (!linkedArrow.contains(a))
			linkedArrow.add(a);
		a.setInput(this);
		update();
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
	public void joinInput(Arrow a) {
		this.inputFromArrow = inputFromArrow.orElse(a.getOutput());
		update();
	}
	public A fname() {
		return fName;
	}
	public String id() {
		return this.id;
	}
	private void update() {
		checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x.getValue()))
				.map(x -> new Atom(x.getValue().get()));
		effectiveCommand = checkEffectiveness.map(x->x);
		currentCell = effectiveCommand.hold(current());
		setOutput = Operational.updates(currentCell);
		outputStream = setOutput.map(x -> new Wave(id(), old(), x));
		for (Arrow a : linkedArrow)
			a.setInput(this);
	}
}
