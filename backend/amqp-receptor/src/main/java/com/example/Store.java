package com.example;

import java.util.ArrayList;
import java.util.List;

public class Store {
	private static List<Message> msgs = new ArrayList<Message>();
	
	public static void add(Message msg) {
		msgs.add(msg);
	}
	public static List<Message> get() {
		return msgs;
	}
}
