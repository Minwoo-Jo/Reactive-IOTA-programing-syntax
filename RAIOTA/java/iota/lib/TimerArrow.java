package iota.lib;

import nz.sodium.Listener;
import nz.sodium.Stream;

public class TimerArrow extends Arrow {
	public ECA eca;
	private Stream<Tuple> input = new Stream();
	private Stream<Cmd> output;
	private Stream<Cmd> timer;

	public Field inputField;
	public Timer timerField;
	public Field outputField;

	public TimerArrow() {

		output = input.map(x -> (Cmd) eca.apply(x.time()).fst()).filter(x -> x != null);
		timer = input.map(x -> (Cmd) eca.apply(x.time()).snd());

	}

	public TimerArrow set(ECA eca) {
		this.eca = eca;
		return this;
	}

	public TimerArrow setInput(Timer t) {
		this.inputField = t;
		this.timerField = t;
		return this;
	}

	public TimerArrow shoot(Field f) {
		this.outputField = f;
		return this;
	}

	public TimerArrow shoot(Timer t) {
		this.timerField = t;
		return this;
	}

	public Stream<Tuple> getInput() {
		return this.input;
	}

	public Stream<Cmd> getOutput() {
		return this.output;
	}

	public Stream<Cmd> getTimerOutput() {
		return this.timer;
	}

	public TimerArrow update() {
		this.input = timerField.output();
		output = input.filter(x -> eca.apply(x.time()) != null).map(x -> (Cmd) eca.apply(x.time()).fst());
		timer = input.filter(x -> eca.apply(x.time()) == null).map(x->Cmd.stay)
				.orElse(input.filter(x -> eca.apply(x.time()) != null).map(x -> (Cmd) eca.apply(x.time()).snd()));
		outputField.joinInput(this);
		timerField.joinInput(this);
		return this;

	}
}
