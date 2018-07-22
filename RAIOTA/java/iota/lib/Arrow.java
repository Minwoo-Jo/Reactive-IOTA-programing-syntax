package iota.lib;

import java.util.ArrayList;
import java.util.HashMap;

import nz.sodium.*;
import nz.sodium.time.*;

public class Arrow<A> {

	public ECA eca;
	private Stream<Tuple> input = new Stream();
	private Listener connecter = new Listener();
	private StreamSink<Cmd> output = new StreamSink();
	public Field inputField;
	public Field outputField;

	public Arrow() {
//		connecter = input.listen(x -> new Thread() {
//			public void run() {
//				if (eca.apply((Cmd) x.fst(), (Cmd) x.snd()) != null)
//					output.send(eca.apply((Cmd) x.fst(), (Cmd) x.snd()));
//			}
//		}.start());
		////	output = input.map(x -> eca.apply((Cmd) x.fst(), (Cmd) x.snd())).filter(x -> x != null);
	}

	public Arrow(ECA eca) {
		this.eca = eca;
	}
	public Arrow set(ECA eca) {
		this.eca = eca;
		return this;
	}

	public Arrow setInput(Field f) {
		this.inputField = f;
	//	this.input = inputField.output();
		return this;
	}

	public Arrow shoot(Field f) {
		this.outputField = f;
		return this;
	}

	public Arrow shoot(Timer tf) {
		this.outputField = tf;
		return this;
	}

	public Stream<Tuple> getInput() {
		return this.input;
	}

	public Stream<Cmd> getOutput() {
		return this.output;
	}

	public Arrow update() {
		connecter.unlisten();
		//this.input = inputField.output();
		
		connecter = ((Stream<Tuple>)(inputField.output())).listen(x -> new Thread() {
			public void run() {
				if (eca.apply((Cmd) x.fst(), (Cmd) x.snd()) != null)
					output.send(eca.apply((Cmd) x.fst(), (Cmd) x.snd()));
			}
		}.start());
		outputField.joinInput(this);
		
		//output = input.map(x -> eca.apply(x.fst(), x.snd())).filter(x -> x != null);
		return this;

	}
}
