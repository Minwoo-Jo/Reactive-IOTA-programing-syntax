package main;

import nz.sodium.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional.*;
import iota.lib.*;



public class Device<A> {
	public String devName;
	public HashMap<String, Field> map = new HashMap();
	public ArrayList<Field> fields = new ArrayList();
	
	public Device() {}
	
	//Device생성자. 입력받은 값을 String으로 치환하여 이름 저장.
	public Device(A devName) {
		this.devName = (String)devName;	
	}
	//Device의 이름을 반환하는 메소드
	public String getName() {
		return devName;
	}
	
	//필드를 생성, 추가하는 메소드
	public void addField(Field fd) {
		fields.add(fd);
		map.put(fd.name(), fd);
	}
	//모든 필드와 그 필드의 상태(Atom)을 문자열로 출력
		public String getField() {
			String fNa = "";
			for(Field fd : fields) {
				fNa = fNa+"Field : "+fd.name()+"	State : "+fd.current().get()+"\n";
			}
			return fNa; 
		}

	//입력받은 필드 이름을 매개로 필드를 반환하는 메소드 
	public Field getField(String fdName) {
		
		return map.get(fdName);
	}
	
	//입력받은 필드 이름을 매개로 필드를 삭제하는 메소드
	public void removeField(String fdName) {
		map.remove(fdName); 
	}
	
}

