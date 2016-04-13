package com.epam.cdp.jcf.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class ListBenchmarkService {

	private String pattern = "Element %d";
	private int num;
	private String name;

	BenchmarkDaoImpl benchmarkDao = new BenchmarkDaoImpl();

	private void addToEnd(List<String> list) {
		long freeMemoryBefore = Runtime.getRuntime().freeMemory();
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;
		long memorySize = freeMemoryBefore - Runtime.getRuntime().freeMemory();
		
		benchmarkDao.addBenchmark(name, new Benchmark("addToEnd", num, executionTime));
		benchmarkDao.addMemoryUsageResult(name, memorySize);
	}

	private void addToTop(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(0, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("addToTop", num,
				executionTime));
	}

	private void addToMiddle(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(i / 2, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("addToMiddle", num,
				executionTime));
	}

	private void removeFromTopByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = 0; i < num; i++) {
			list.remove(0);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("removeFromTopByIndex",
				num, executionTime));
	}

	private void removeFromEndByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = num; i > 0; i--) {
			list.remove(i - 1);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("removeFromEndByIndex",
				num, executionTime));
	}

	private void removeFromMiddleByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove((num - i) / 2);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark(
				"removeFromMiddleByIndex", num, executionTime));
	}

	private void removeByObject(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("removeByObject", num,
				executionTime));
	}

	private void sort(List<String> list) {
		Collections.shuffle(list);

		long startTime = System.nanoTime();

		Collections.sort(list);

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("sort", num,
				executionTime));
	}

	private void getByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.get(i);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("getByIndex", num,
				executionTime));
	}

	private void contains(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.contains(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarkDao.addBenchmark(name, new Benchmark("contains", num,
				executionTime));
	}

	private void runTest(String name, List<String> list, int numberOfItems) {
		this.name = name;
		this.num = numberOfItems;

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
	
	public static void runBenchmarkTest(int numberOfItems){
		List<String> list = new ArrayList<String>();
		List<String> listWithInitSize = new ArrayList<String>(numberOfItems);
		List<String> linkedList = new LinkedList<String>();

		ListBenchmarkService listTest = new ListBenchmarkService();

		
		listTest.runTest("ArrayList", list, numberOfItems);
		listTest.runTest("ArrayList with init size", listWithInitSize, numberOfItems);
		listTest.runTest("LinkedList", linkedList, numberOfItems);
	}
}
