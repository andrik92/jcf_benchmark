package com.epam.cdp.jcf.service;

import java.util.Collection;
import java.util.Map;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;

public abstract class CollectionBenchmarkService {

	protected final String pattern = "Element %d";
	protected final int RUNS = 30;
	protected int num;
	protected Map<String, Long> rezultTotalMap;
	protected long rezultTotalMemoryUsage;

	BenchmarkDaoImpl benchmarkDao = new BenchmarkDaoImpl();

	protected void add(Collection<String> collection) {
		long freeMemoryBefore = Runtime.getRuntime().freeMemory();
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			collection.add(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;
		long memorySize = freeMemoryBefore - Runtime.getRuntime().freeMemory();

		updateRezultTotalMap("add", executionTime);

		rezultTotalMemoryUsage += memorySize;
	}

	protected void removeByObject(Collection<String> collection) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			collection.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("removeByObject", executionTime);
	}

	protected void contains(Collection<String> collection) {
		long startTime = System.nanoTime();

		collection.contains(String.format(pattern, 0));

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("contains", executionTime);
	}

	protected void updateRezultTotalMap(String methodName, long executionTime) {
		if (rezultTotalMap.containsKey(methodName)) {
			executionTime += rezultTotalMap.get(methodName);
		}
		rezultTotalMap.put(methodName, executionTime);
	}

}
