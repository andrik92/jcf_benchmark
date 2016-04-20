package com.epam.cdp.jcf.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class ListBenchmarkService {

	private final String pattern = "Element %d";
	private final int RUNS = 30;
	private int num;
	private Map<String, Long> rezultTotalMap;
	private long rezultTotalMemoryUsage;

	BenchmarkDaoImpl benchmarkDao = new BenchmarkDaoImpl();

	private void addToEnd(List<String> list) {
		long freeMemoryBefore = Runtime.getRuntime().freeMemory();
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;
		long memorySize = freeMemoryBefore - Runtime.getRuntime().freeMemory();

		updateRezultTotalMap("addToEnd", executionTime);

		rezultTotalMemoryUsage += memorySize;
	}

	private void addToTop(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(0, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("addToTop", executionTime);
	}

	private void addToMiddle(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(i / 2, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("addToMiddle", executionTime);
	}

	private void removeFromTopByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = 0; i < num; i++) {
			list.remove(0);
		}
		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("removeFromTopByIndex", executionTime);
	}

	private void removeFromEndByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = num; i > 0; i--) {
			list.remove(i - 1);
		}
		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("removeFromEndByIndex", executionTime);
	}

	private void removeFromMiddleByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove((num - i) / 2);
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("removeFromMiddleByIndex", executionTime);
	}

	private void removeByObject(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("removeByObject", executionTime);
	}

	private void sort(List<String> list) {
		Collections.shuffle(list);

		long startTime = System.nanoTime();

		Collections.sort(list);

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("sort", executionTime);
	}

	private void getByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.get(i);
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("getByIndex", executionTime);
	}

	private void contains(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		list.contains(String.format(pattern, 0));

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("contains", executionTime);
	}

	public void runBenchmarkTest(int numberOfItems) {
		List<String> arrayList = new ArrayList<String>();
		List<String> arrayListWithInitSize = new ArrayList<String>(numberOfItems);
		List<String> linkedList = new LinkedList<String>();
		List<String> vector = new Vector<String>();
		List<String> vectorWithInitSize = new Vector<String>(numberOfItems);
		List<String> stack = new Stack<String>();

		runTest("ArrayList", arrayList, numberOfItems);
		runTest("ArrayList with init size", arrayListWithInitSize, numberOfItems);
		runTest("LinkedList", linkedList, numberOfItems);
		runTest("Vektor", vector, numberOfItems);
		runTest("Vektor with init size", vectorWithInitSize, numberOfItems);
		runTest("Stack", stack, numberOfItems);
	}

	private void runTest(String name, List<String> list, int numberOfItems) {
		this.num = numberOfItems;

		rezultTotalMap = new HashMap<String, Long>();
		rezultTotalMemoryUsage = 0;

		for (int i = 0; i < RUNS; i++) {
			addToEnd(list);
			removeFromEndByIndex(list);
			addToMiddle(list);
			removeFromMiddleByIndex(list);
			addToTop(list);
			removeFromTopByIndex(list);
			addToEnd(list);
			sort(list);
			getByIndex(list);
			contains(list);
			removeByObject(list);
		}

		for (Map.Entry<String, Long> entry : rezultTotalMap.entrySet()) {
			benchmarkDao.addBenchmark(name, new Benchmark(entry.getKey(), num,
					RUNS, entry.getValue() / RUNS));
		}
		
		benchmarkDao.addMemoryUsageResult(name, rezultTotalMemoryUsage / RUNS);

	}

	private void updateRezultTotalMap(String methodName, long executionTime) {
		if (rezultTotalMap.containsKey(methodName)) {
			executionTime += rezultTotalMap.get(methodName);
		}
		rezultTotalMap.put(methodName, executionTime);
	}
}
