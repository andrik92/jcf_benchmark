package com.epam.cdp.jcf.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.epam.cdp.jcf.model.Benchmark;
import com.google.common.collect.TreeMultimap;

public class Test {

	public static void main(String[] args) {
		Benchmark b = new Benchmark("add", 100, 0, 500);
		Benchmark b1 = new Benchmark("add", 100, 0, 5);
		Benchmark b2 = new Benchmark("add1", 100, 0, 5);
		long exe = 10000;

		TreeMultimap<String, Benchmark> rez = TreeMultimap.create();

		rez.put("A", b);
		rez.put("A", b1);
		rez.put("B", b1);
		rez.put("A", b2);

		System.out.println(rez);

		System.out.println(rez.get("A").toString());

		for (Benchmark bench : rez.get("A")) {

			if (bench.getMethodName() == "add1") {
				exe += bench.getExecutionTime();
				bench.setExecutionTime(exe);
			}

			System.out.println(bench + "**");
		}
		// rez.get("A").iterator()
		// rez.asMap().get("A").

		for (Benchmark bench : rez.get("A")) {

			// if (bench.getMethodName() == "add1"){
			// exe += bench.getExecutionTime();
			// bench.setExecutionTime(exe);
			// }

			System.out.println(bench + "**");
		}

		if (rez.get("A").contains(b1)) {
			// rez.get("A").
		}

		Map<String, Integer> memory = new TreeMap<>();

		memory.put("A", 15);
		memory.put("A", 7);

		System.out.println(memory);

		Map<String, Integer> memory1 = new TreeMap<>();

		int x = 5;

		memory1.put("A", 15);
		if (memory1.containsKey("A")) {
			x += memory1.get("A");
			x /= 2;
		}

		memory1.put("A", x);

		System.out.println(memory1);

		Map<String, Long> map = new HashMap<>();
		map.put("A", 10L);
		map.put("B", 20L);
		map.put("C", 0L);

		for (Map.Entry<String, Long> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		
	}
}
