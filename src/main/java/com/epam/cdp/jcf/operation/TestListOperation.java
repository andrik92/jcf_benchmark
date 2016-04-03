package com.epam.cdp.jcf.operation;

import java.util.Collections;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class TestListOperation {

	private static final int NUM = 5000;
	private String pattern = "Element %d";
	private String name;

	BenchmarkDaoImpl benchmarksList = new BenchmarkDaoImpl();

	public void addToEnd(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.add(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToEnd", NUM, executionTime));
	}

	public void addToTop(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.add(0, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToTop", NUM, executionTime));
	}

	public void addToMiddle(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.add(i / 2, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToMiddle", NUM, executionTime));
	}

	public void removeFromTopByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			list.remove(0);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromTopByIndex", NUM, executionTime));
	}

	public void removeFromEndByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = NUM; i > 0; i--) {
			list.remove(i - 1);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromEndByIndex", NUM, executionTime));
	}

	public void removeFromMiddleByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.remove((NUM - i) / 2);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromMiddleByIndex", NUM, executionTime));
	}

	public void removeByObject(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeByObject", NUM, executionTime));
	}

	public void sort(List<String> list) {
		Collections.shuffle(list);

		long startTime = System.nanoTime();

		Collections.sort(list);

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("sort", NUM, executionTime));
	}

	public void getByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.get(i);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("getByIndex", NUM, executionTime));
	}

	public void contains(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < NUM; i++) {
			list.contains(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("contains", NUM, executionTime));
	}

	public void run(String name, List<String> list) {
		this.name = name;

		addToEnd(list);
		removeFromEndByIndex(list);
		addToMiddle(list);
//		removeFromMiddleByIndex(list);
//		addToTop(list);
//		removeFromTopByIndex(list);
//		addToEnd(list);
//		sort(list);
//		getByIndex(list);
//		contains(list);
//		removeByObject(list);
	}
}
