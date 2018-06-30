package main;
import iota.lib.*;
import nz.sodium.*;
import nz.sodium.time.*;


public class Main {
	
	public static void main(String[] args) {

		Door A = new Door("frontDoor");
		Door B = new Door("backDoor");
		
		A.getField("lock").link(new Form() {
		
			Condition c = new BasicCondition();
			
			public Form apply() {
				return apply(c);
			}
			
						
		 }.apply()).obs(B.getField("lock"));
	
		System.out.println(A.getField("lock").current());
				
		A.getField("lock").change("unlocked");
		
		System.out.println(A.getField("lock").current());
		System.out.println(A.getField("lock").old());
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
 * 				obs		x y 	:: Form 	-> Field	-> Void
 * 
 * 				apply	x y		:: Form		-> Condition-> Form
 * 
 */


