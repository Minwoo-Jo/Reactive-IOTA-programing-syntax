package iota.lib;

import nz.sodium.*;

public class Arrow {

	EventHandler e = new NullEvent();
	Predicate p = new TrueCondition();
	Command c = new Command(null);

	Stream<Command> input = new Stream();
	Stream<Command> check = new Stream();
	Stream<Predicate> connecter = new Stream();
	Stream<Command> output;

	public Arrow() {

		Transaction.runVoid(() -> {
			input = new Stream();
			//check = input.filter(x -> e.checkType(f));
			connecter = input.map(x -> p).filter(x -> x.isTrue());
			output = connecter.map(x -> c);

		});
	}

	public void setHandler(EventHandler e) {
		this.e = e;
	}

	public void setCondition(Predicate p) {
		this.p = p;
		Transaction.runVoid(() -> {

			input = new Stream();
			connecter = input.map(x -> p).filter(x -> x.isTrue());
			output = connecter.map(x -> c);

		});
	}

	public void setCommand(Command c) {
		this.c = c;
		Transaction.runVoid(() -> {

			input = new Stream();
			connecter = input.map(x -> p).filter(x -> x.isTrue());
			output = connecter.map(x -> c);

		});

	}

	public Arrow setInput(Field f) {

		Transaction.runVoid(() -> {
			this.input = input.orElse(f.output());
			connecter = input.map(x -> p).filter(x -> x.isTrue());
			output = connecter.map(x -> c);

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

	public Stream<Command> getInput() {
		return this.input;
	}

	public Stream<Command> getOutput() {
		return this.output;
	}

	public Stream<Predicate> getConnecter() {
		return this.connecter;
	}

}
