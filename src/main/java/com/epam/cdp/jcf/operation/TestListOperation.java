package com.epam.cdp.jcf.operation;

import java.util.Collections;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class TestListOperation {

	private String pattern = "Element %d";
	private int num;
	private String name;

	BenchmarkDaoImpl benchmarksList = new BenchmarkDaoImpl();

	public void addToEnd(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToEnd", num, executionTime));
	}

	public void addToTop(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(0, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToTop", num, executionTime));
	}

	public void addToMiddle(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.add(i / 2, String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("addToMiddle", num, executionTime));
	}

	public void removeFromTopByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = 0; i < num; i++) {
			list.remove(0);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromTopByIndex", num, executionTime));
	}

	public void removeFromEndByIndex(List<String> list) {
		long startTime = System.nanoTime();
		for (int i = num; i > 0; i--) {
			list.remove(i - 1);
		}
		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromEndByIndex", num, executionTime));
	}

	public void removeFromMiddleByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove((num - i) / 2);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeFromMiddleByIndex", num, executionTime));
	}

	public void removeByObject(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("removeByObject", num, executionTime));
	}

	public void sort(List<String> list) {
		Collections.shuffle(list);

		long startTime = System.nanoTime();

		Collections.sort(list);

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("sort", num, executionTime));
	}

	public void getByIndex(List<String> list) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.get(i);
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("getByIndex", num, executionTime));
	}

	public void contains(List<String> list) {
		Collections.shuffle(list);
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			list.contains(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		benchmarksList.addBenchmark(name, new Benchmark("contains", num, executionTime));
	}

	public void run(String name, List<String> list, int numberOfItems) {
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
}
