package main;

import iota.lib.*;
import nz.sodium.*;
import nz.sodium.time.*;

public class Main {

	public static void main(String[] args) {
		Atom open = new Atom("open");
		Atom close = new Atom("close");
		Atom locked = new Atom("locked");
		Atom unlocked = new Atom("unlocked");
		Atom on = new Atom("on");
		Atom off = new Atom("off");

		Field door = new Field("door");

		door.addCommand(open);
		door.addCommand(close);

		Field door2 = new Field("door");

		door2.addCommand(open);
		door2.addCommand(close);

		Field lock = new Field("lock");
		lock.addCommand(locked);
		lock.addCommand(unlocked);

		Field button = new Field("button");

		button.addCommand(on);
		button.addCommand(off);

		Field button2 = new Field("button");

		button2.addCommand(on);
		button2.addCommand(off);

		Arrow arrow2 = new Arrow();
		Arrow arrow = new Arrow();
		arrow.setCommand(new SingleBullet(unlocked));

		
		SingleBullet abc = new SingleBullet(off);

		System.out.println("!!!!!");

		arrow2.setCommand(new SingleBullet(off));

		door.shoot(arrow).shoot(lock).shoot(arrow2).shoot(button);
		
		door2.shoot(arrow);

		
		
		
		
		
		
		Listener l3 = arrow2.getInput().listen(x -> System.out.println("in\tarrow2\t" + x));
		Listener l4 = arrow2.getOutput().listen(x -> System.out.println("out\tarrow2\t" + x));
		Listener l9 = arrow2.getConnecter().listen(x -> System.out.println("conn\tarrow2\t" + x));

		Listener l5 = door.output().listen(x -> System.out.println("out\tdoor\t" + x));
		Listener l25 = door.con().listen(x -> System.out.println("check\tdoor\t" + x));
		Listener l6 = door.input().listen(x -> System.out.println("input\tdoor\t" + x));

		Listener l21 = lock.output().listen(x -> System.out.println("out\tlock\t" + x));
		Listener l24 = lock.con().listen(x -> System.out.println("check\tlock\t" + x));
		Listener l22 = lock.input().listen(x -> System.out.println("in\tlock\t" + x));

		Listener l213 = button.output().listen(x -> System.out.println("out\tbutton\t" + x));
		Listener l244 = button.con().listen(x -> System.out.println("check\tbutton\t" + x));
		Listener l221 = button.input().listen(x -> System.out.println("in\tbutton\t" + x));

		Listener l33 = arrow.getInput().listen(x -> System.out.println("in\tarrow\t" + x));
		Listener l34 = arrow.getOutput().listen(x -> System.out.println("out\tarrow\t" + x));
		Listener l49 = arrow.getConnecter().listen(x -> System.out.println("conn\tarrow\t" + x));

		// button.change(off);

		// door.change(open);

		System.out.println("----lock current\t" + lock.current().get());
		System.out.println("----door current\t" + door.current().get());
		System.out.println("----button current\t" + button.current().get());
		System.out.println("----button2 current\t" + button2.current().get());
		//door.change(open);
		door.change(close);
		//door.change(close);
		// lock.change(unlocked);
		System.out.println("----lock current\t" + lock.current().get());
		System.out.println("----door current\t" + door.current().get());
		System.out.println("----button current\t" + button.current().get());
		System.out.println("----button2 current\t" + button2.current().get());

	}
}
/*
 * 
 * Reactive IOTA programing syntax 작성중
 * 
 * 
 * Form :: field와 field를 연결 시켜주는 객체
 * 
 * conn : Form과 Form의 스트림을 받는 field를 연결시킨다. set : Condition을 설정한다. obs : Form이
 * 받는 스트림을 발사하는 field를 연결한다. def : 들어오는 event를 설정한다. shoot : 발사하는 action을 설정한다.
 * chain : form 과 form 을 연결한다.
 *
 * Field:: 상태를 가진 객체 Sodium의 CellSink를 상속받아 구현
 *
 * link : Field와 Form을 연결한다. change : Field의 값을 바꾼다. old : 이전 값을 받아온다. current:
 * 현재 값을 받아온다.
 * 
 * Linker::Form과 Form을 연결할 때 사용되는 중간 객체 Field를 상속 받는다.
 *
 * set : 들어오는 값을 저장한다. get : 받은 값을 보내준다. conv : Linker에서 나오는 스트림을 받는 Form을 연결한다.
 * 
 * interface Condition : isTrue() 메소드를 가진다. Boolean 값을 반환. Form에 조건을 설정할 때 사용.
 *
 * EventHandler: typeCheck() 메소드를 가진다. Event의 타입을 결정할 때 사용.
 *
 */
