package iota.lib;

import nz.sodium.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional.*;

import main.RegisteredDevices;

public class Field<A, B> {
	protected A fName;
	private String id;
	protected ArrayList<Cmd> status = new ArrayList();

	private StreamSink<Cmd> inputStream;
	private Stream<Cmd> inputFromArrow;
	private Stream<Cmd> checkEffectiveness;
	private Stream<Cmd> effectiveCommand;
	private Cmd old = null;
	private Cmd current = null;
	private Cell<Cmd> currentCell = new Cell<Cmd>(null);
	private Stream<Cmd> setOutput;
	private Stream<Tuple> outputStream;

	protected HashMap<Arrow, Field> inMap = new HashMap();
	protected HashMap<Arrow, Field> hMap = new HashMap();

	private Listener fl = new Listener();

	public Field(A fName) {

		this.fName = fName;
		this.id = this.toString();
		inputStream = new StreamSink();
		inputFromArrow = new Stream().orElse(inputStream).filter(x-> x!=current);
		checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x));
		currentCell = checkEffectiveness.hold(current);
		setOutput = Operational.updates(currentCell);
		outputStream = setOutput.map(x -> new Tuple(old, x));
		
		fl = outputStream.listen(x-> {
			RegisteredDevices.set().print();
		});

	}

	public void add(Cmd v) {

		if (status.isEmpty()) {
			currentCell = checkEffectiveness.hold(v);
			current = v;

		}
		status.add(v);
	}

	private Boolean isCorrectCommand(Cmd command) {
		if (status.contains(command)) {
			old = currentCellValue();
			current = command;
			return true;
		} else {
			
			return false;
		}

	}

	public Cmd old() {
		return old;
	}

	public Cmd current() {
		return current;
	}

	private Cmd currentCellValue() {
		return currentCell.sample();
	}

	public void change(Cmd c) {
		if (status.contains(c) && !current.equals(c)) {
			inputStream.send(c);
		}

	}
	
	public Stream forL() {
		return outputStream;
	}

	public void lift(ECA eca, Field f) {
       this.shoot(new Arrow(eca).shoot(f));
       update();
     
	}
	
	public Arrow shoot(Arrow a) {
		if (!hMap.containsKey(a))
			hMap.put(a, a.outputField);
		update();
		return a;
	}

	public Stream<Cmd> input() {
		return this.inputFromArrow;
	}

	public Stream<Tuple> output() {
		return this.outputStream;
	}

	private Stream<Cmd> con() {
		return this.checkEffectiveness;
	}

	public void joinInput(Arrow a) {
		if (!this.inMap.containsKey(a))
			inMap.put(a, this);
		else {
			inMap.remove(a);
			inMap.put(a, this);
		}
		
		update();

	}

	public A fname() {
		return fName;
	}

	public String id() {
		return this.id;
	}

	private void update() {
		inputStream = new StreamSink();
		inputFromArrow = new Stream().orElse(inputStream);
		for (Arrow a : inMap.keySet())
			this.inputFromArrow = inputFromArrow.orElse(a.getOutput().filter(x-> x!=current));
		checkEffectiveness = inputFromArrow.filter(x -> isCorrectCommand(x));
		currentCell = checkEffectiveness.hold(current());
		setOutput = Operational.updates(currentCell);
		outputStream = setOutput.map(x -> new Tuple(old(), x));
		for (Arrow a : hMap.keySet()) {
			a.inputField = this;
			a.update();
		}
		fl.unlisten();
		fl = outputStream.listen(x-> {
			RegisteredDevices.set().print();
		});
	}

}
