package iota.lib;

import nz.sodium.*;

import java.util.ArrayList;
import java.util.Optional.*;

public class Field<A> {
	private A fName;

	private ArrayList<A> status= new ArrayList();;

	private StreamSink<SingleBullet> inputStream;
	private Stream<Bullet> inputFromArrow;

	private Stream<Bullet> checkEffectiveness;

	private Stream<Bullet> effectiveCommand;
	private Bullet<A> old;
	private Cell<Bullet> current;
	private Stream<Bullet> setOutput;
	private Stream<Bullet> outputStream;

	public Field(A fName) {

		this.fName = fName;

			inputStream = new StreamSink();
			inputFromArrow = new Stream().orElse(inputStream);

			checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x));

			effectiveCommand = checkEffectiveness.filter(x -> status.contains(x.getValue()));
			current = effectiveCommand.hold(null);
			setOutput = Operational.updates(current);
			outputStream = setOutput.map(x -> new FieldBullet(this, old().getValue(), current().getValue()));


	}

	public void addCommand(SingleBullet<A> c) {

		if (status.isEmpty())
			current = checkEffectiveness.hold(c);
		status.add(c.getValue());
	}

	public Boolean isCorrectCommand(Bullet<A> c) {
		return this.status.contains(c.getValue());
	}

	public Bullet current() {
		return current.sample();
	}

	public Bullet old() {
		return old;
	}

	public void change(SingleBullet c) {
			old = current();
			inputStream.send(c);
			

	}

	public Arrow shoot(Arrow a) {
		a.setInput(this);
		return a;
	}

	public Stream<Bullet> input() {
		return this.inputFromArrow;
	}

	public Stream<Bullet> output() {
		return this.outputStream;
	}

	public Stream<Bullet> con() {
		return this.checkEffectiveness;
	}

	public void joinInput(Stream<Bullet> s) {
			this.inputFromArrow = inputFromArrow.orElse(s);
			this.checkEffectiveness = this.inputFromArrow.filter(x -> isCorrectCommand(x));
			this.effectiveCommand = checkEffectiveness.filter(x -> true);
			this.current = effectiveCommand.hold(current());
			setOutput = Operational.updates(current);
			outputStream = setOutput.map(x -> new FieldBullet(this, old().getValue(), current().getValue()));
	}

//	public void joinOutput(Stream<Bullet> s) {
//		//this.outputStream = setOutput.orElse(s);
//	}
}
