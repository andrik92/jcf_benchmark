package com.epam.cdp.jcf.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Test {

	public static void main(String[] args) {		
		List<String> list = new ArrayList<String>();
		
		Set <String> set = new HashSet<String>();
		
		Queue<String> queue = new PriorityQueue<>();
		
		list.add(0, "top");
		set.add("s");
		Collections.sort(list);
		
		queue.add("test1");
		queue.add("test2");
		
		System.out.println(queue);

		System.out.println("peek" + queue.peek());
		
		
		System.out.println(queue.poll()+ "+poll" + queue);
		

		System.out.println("+peek" + queue);
		
	}
}
