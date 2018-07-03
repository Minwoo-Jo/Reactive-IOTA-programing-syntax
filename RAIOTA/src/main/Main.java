package main;

import iota.lib.*;
import nz.sodium.*;
import nz.sodium.time.*;

public class Main {

	public static void main(String[] args) {
		Field door = new Field("door");

		door.addCommand(new SingleBullet(new Atom("open")));
		door.addCommand(new SingleBullet(new Atom("close")));
		
		Field lock = new Field("lock");
		lock.addCommand(new SingleBullet(new Atom("locked")));
		lock.addCommand(new SingleBullet(new Atom("unlocked")));
		
		
		
		
		Field button = new Field("button");
		
		button.addCommand(new SingleBullet(new Atom("on")));
		button.addCommand(new SingleBullet(new Atom("off")));
		
		testCase tc = new testCase(button,new Atom("on") );

		Arrow arrow = new Arrow();
		
		arrow.setCommand(new SingleBullet(new Atom("unlocked")));
		//arrow.setHandler(new From(new Atom("open")));
		//arrow.setHandler(new FromTo(new Atom("open"), new Atom("close")));
		arrow.setHandler(new To(new Atom("close")));
		arrow.setCondition(tc);
		
		door.shoot(arrow).shoot(lock);
			
		System.out.println("   lock " + lock.current().get());
		System.out.println("   door " + door.current().get());
		
		Listener l3 = arrow.getInput().listen(x->System.out.println("input arrow " +x));
		Listener l4 = arrow.getOutput().listen(x-> System.out.println("output arrow " + x));
		
		Listener l5 = door.output().listen(x-> System.out.println("output door " + x));
		Listener l25 = door.con().listen(x-> System.out.println("efec door "+ x));
		Listener l6 = door.input().listen(x-> System.out.println("input door " + x));
		
		Listener l9 = arrow.getConnecter().listen(x-> System.out.println("connecter arrow " + x));
		
		Listener l21 = lock.output().listen(x-> System.out.println("output lock " + x));
		Listener l24 = lock.con().listen(x-> System.out.println("efec lock " + x));
		Listener l22 = lock.input().listen(x-> System.out.println("input lock " + x));
	
		
	
		door.change(new Atom("close"));	
		//door.change(new Atom("open"));	
		
		System.out.println("   lock " + lock.current().get());
		System.out.println("   door " + door.current().get());
		
		door.change(new Atom("open"));	
		

		System.out.println("   lock " + lock.current().get());
		System.out.println("   door " + door.current().get());

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
