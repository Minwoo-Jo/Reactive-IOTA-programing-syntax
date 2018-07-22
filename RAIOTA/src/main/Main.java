package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import iota.lib.*;
import nz.sodium.Listener;
import nz.sodium.StreamSink;

public class Main {

	public static void main(String[] args) {
		RegisteredDevices rd = RegisteredDevices.set();
		//program1.start();
		program2.start();

		rd.print();


		console(rd);

	}

	public static void console(RegisteredDevices rd) {
		ArrayList<Cmd> ar = new ArrayList();
		ar.add(Cmd.close);
		Scanner input = new Scanner(System.in);
		System.out.println("Input Command ex) Device.Field.Cmd");
		System.out.print("  \t     | ");
		while (true) {

			String string = input.nextLine();
			StringTokenizer tokens = new StringTokenizer(string);

			String dev = tokens.nextToken(".");
			String field = tokens.nextToken(".");
			String cmd = tokens.nextToken();

			new Thread() {
				public void run() {
					rd.device(dev).field(field).change(Cmd.valueOf(cmd));
//					rd.print();
//					System.out.println("Input Command ex) Device.Field.Cmd");
//					System.out.print("  \t     | ");

				}
			}.start();
		}
	}
}
