//package iota.lib;
//
//import java.util.HashMap;
//
//import nz.sodium.Listener;
//import nz.sodium.Stream;
//
//public class ListenerList {
//	public static HashMap<String, Stream> ls = new HashMap();
//
//	public ListenerList() {
//
//	}
//
//	public static void add(Field f) {
//		if (!ls.containsKey(f.id()))
//			ls.put(f.id(), f.forL());
//		else {
//			ls.remove(f.id());
//			ls.put(f.id(), f.forL());
//		}
//	}
//
//}
