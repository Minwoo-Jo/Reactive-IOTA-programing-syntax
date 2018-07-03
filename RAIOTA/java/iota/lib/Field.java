package iota.lib;

import nz.sodium.*;

import java.util.ArrayList;
import java.util.Optional.*;

public class Field<A> {
	private A fName;

	private ArrayList<Command> command;

	private StreamSink<Command> inputStream;
	private Stream<Command> inputFromArrow;

	private Stream<Command> checkEffectiveness;

	private Stream<Command> effectiveCommand;
	private Command old;
	private Cell<Command> current;
	Stream<Command> outputStream;

	public Field(A fName) {

		this.fName = fName;
		command = new ArrayList();
		Transaction.runVoid(() -> {
			inputStream = new StreamSink();
			inputFromArrow = new Stream().orElse(inputStream);

			checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x));
			effectiveCommand = checkEffectiveness.filter(x -> true);
			current = effectiveCommand.hold(null);

			outputStream = Operational.updates(current);

		});
	}

	public void addCommand(Command c) {

		if (command.isEmpty())
			current = checkEffectiveness.hold(c);
		command.add(c);

	}

	public Boolean isCorrectCommand(Command c) {
		return true;
	}

	public Command current() {
		return current.sampleLazy().get();
	}

	public Command old() {
		return old;
	}

	public void change(Command c) {
		Transaction.runVoid(() -> {
			old = current();
			inputStream.send(c);
		});
	}

	public Arrow shoot(Arrow a) {
		a.setInput(this);
		return a;
	}

	public Stream<Command> input() {
		return this.inputFromArrow;
	}

	public Stream<Command> output() {
		return this.outputStream;
	}

	public Stream<Command> con() {
		return this.checkEffectiveness;
	}

	public void joinInput(Stream<Command> s) {

		Transaction.runVoid(() -> {
			this.inputFromArrow = inputFromArrow.orElse(s);
			this.checkEffectiveness = this.inputFromArrow.filter(x -> isCorrectCommand(x));
			this.effectiveCommand = checkEffectiveness.filter(x -> true);
			this.current = effectiveCommand.hold(current());
			outputStream = Operational.updates(current);
		});

	}

	public void joinOutput(Stream<Command> s) {
		this.outputStream = outputStream.orElse(s);
	}

}
