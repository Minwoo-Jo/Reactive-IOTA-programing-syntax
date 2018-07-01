package main;
import iota.lib.*;
import nz.sodium.*;
import nz.sodium.time.*;


public class Main {
	
	public static void main(String[] args) {

		Door A = new Door("frontDoor");
		Door B = new Door("backDoor");
		
		Form fm = new Form();

		
		
		A.getField("lock").link(new Form() {
			
			Form apply() {
				EventHandler e = new From("locked");
				Condition c = new TrueCondition();
				
				def(e);
				set(c);
				return this;
			}
			
		}.apply()).obs(B.getField("lock")).shoot(new Command("unlocked"));
		
		//A.getField("lock").link(fm).obs(B.getField("lock")).set(new BasicCondition());

			
		System.out.println("!" + A.getField("lock").current());
		
		B.getField("lock").change("unlocked");
		
		System.out.println("!" + A.getField("lock").current());
		System.out.println("!" + A.getField("lock").old());
		

	}

	
	
}
/*
 * 
 * Reactive IOTA programing syntax 작성중
 * 
 * 
 * 			Form :: field와 field를 연결 시켜주는 객체
 * 					
 * 					conn   : Form과 Form의 스트림을 받는 field를 연결시킨다.
 * 					set    : Condition을 설정한다.
 *					obs	   : Form이 받는 스트림을 발사하는 field를 연결한다.
 *					def    : 들어오는 event를 설정한다.
 *					shoot  : 발사하는 action을 설정한다.
 *					chain  : form 과 form 을 연결한다.
 *
 *			Field:: 상태를 가진 객체 Sodium의 CellSink를 상속받아 구현
 *
 *					link   : Field와 Form을 연결한다.
 *					change : Field의 값을 바꾼다.
 *					old	   : 이전 값을 받아온다.
 *					current: 현재 값을 받아온다.
 *			
 *			Linker::Form과 Form을 연결할 때 사용되는 중간 객체 Field를 상속 받는다.
 *
 *					set	   : 들어오는 값을 저장한다.
 *					get	   : 받은 값을 보내준다.
 *					conv   : Linker에서 나오는 스트림을 받는 Form을 연결한다.
 *		
 *		interface 
 *			Condition  	: 
 *				isTrue() 메소드를 가진다. Boolean 값을 반환.
 *				Form에 조건을 설정할 때 사용.
 *
 *			EventHandler: 
 *				typeCheck() 메소드를 가진다. 
 *				Event의 타입을 결정할 때 사용.
 *
 */


