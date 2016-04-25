package com.epam.cdp.jcf.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import com.epam.cdp.jcf.model.Benchmark;

public class SetBenchmarkService extends CollectionBenchmarkService{

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

	private void getByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.get(i);
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("getByIndex", executionTime);
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
	/*
			add(list);
			removeFromEndByIndex(list);
			addToMiddle(list);
			removeFromMiddleByIndex(list);
			addToTop(list);
			removeFromTopByIndex(list);
			add(list);
			Collections.shuffle(list);
			sort(list);
			getByIndex(list);
			Collections.shuffle(list);
			contains(list);
			removeByObject(list);
		*/
		}

		for (Map.Entry<String, Long> entry : rezultTotalMap.entrySet()) {
			benchmarkDao.addBenchmark(name, new Benchmark(entry.getKey(), num, RUNS, entry.getValue() / RUNS));
		}

		benchmarkDao.addMemoryUsageResult(name, rezultTotalMemoryUsage / RUNS);

	}
}
