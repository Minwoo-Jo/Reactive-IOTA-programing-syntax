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
		
		A.getField("lock").link(fm).obs(B.getField("lock")).set(new BasicCondition());

		//System.out.println(A.getField("lock").current());
				
		
	
		//B.getField("lock").change("locked");
	
	
		B.getField("lock").change("unlocked");
		

		System.out.println("!" + A.getField("lock").current());

	}

	
	
}
/*
 * 
 * Reactive IOTA programing syntax 작성중
 * 
 * 객체 :
 * 		Device		<-- Feild가 들어있는 객체
 * 		
 * 		Field 		<-- CellSink를 상속 받아 구현
 * 		Form  		<-- Stream을 바꿔주는 객체
 * 		Condition	<-- Form의 내용을 구성하는 객체
 *
 * 		
 * 		사용 가능한 문법		
 * 				conn	x y 	:: Field 	-> Form 	-> Form 
 * 					--> Form에서 나오는 Stream을 받을 Field와 연결
 * 				obs		x y 	:: Form 	-> Field	-> Form
 * 					--> Form으로 들어가는 Stream을 발사하는 Field와 연결
 * 				set		x y		:: Form		-> Condition-> Form
 * 					--> Form에 Condition을 부여
 */


