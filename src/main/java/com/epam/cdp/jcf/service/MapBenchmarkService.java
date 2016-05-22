package com.epam.cdp.jcf.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class MapBenchmarkService {

	protected final String pattern = "Element %d";
	protected final int RUNS = 30;
	protected int num;
	protected Map<String, Long> rezultTotalMap;
	protected long rezultTotalMemoryUsage;

	BenchmarkDaoImpl benchmarkDao = new BenchmarkDaoImpl();

	private void put(Map<String, String> map) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			map.put(String.format(pattern, i), String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("put", executionTime);
	}

	protected void get(Map<String, String> map) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			map.get(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("get", executionTime);
	}

	private void containsValue(Map<String, String> map) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			map.containsValue(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("containsValue", executionTime);		
	}

	private void containsKey(Map<String, String> map) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			map.containsKey(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("containsValue", executionTime);		
	}
	
	private void remove(Map<String, String> map) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			map.remove(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("remove", executionTime);
	}
	
	public void runBenchmarkTest(int numberOfItems) {
		Map<String, String> hashMap = new HashMap<String, String>();
		Map<String, String> hashMapWithInitSize = new HashMap<String, String>(numberOfItems);
		Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		Map<String, String> linkedHashMapWithInitSize = new LinkedHashMap<String, String>(numberOfItems);
		Map<String, String> treeMap = new TreeMap<String, String>();
		Map<String, String> hashTable = new Hashtable<String, String>();
		Map<String, String> hashTableWithInitSize = new Hashtable<String, String>(numberOfItems);		
		
		runTest("HashMap", hashMap, numberOfItems);
		runTest("HashMap with init size", hashMapWithInitSize, numberOfItems);
		runTest("LinkedHashMap", linkedHashMap, numberOfItems);
		runTest("LinkedHashMap with init size", linkedHashMapWithInitSize, numberOfItems);
		runTest("TreeMap", treeMap, numberOfItems);
		runTest("HashTable", hashTable, numberOfItems);
		runTest("HashTable with init size", hashTableWithInitSize, numberOfItems);
	}

	private void runTest(String name, Map<String, String> map, int numberOfItems) {
		this.num = numberOfItems;

		rezultTotalMap = new HashMap<String, Long>();
		rezultTotalMemoryUsage = 0;

		for (int i = 0; i < RUNS; i++) {
			put(map);
			get(map);
			containsKey(map);
			containsValue(map);
			remove(map);
		}

		for (Map.Entry<String, Long> entry : rezultTotalMap.entrySet()) {
			benchmarkDao.addBenchmark(name, new Benchmark(entry.getKey(), num, RUNS, entry.getValue() / RUNS));
		}

		benchmarkDao.addMemoryUsageResult(name, rezultTotalMemoryUsage / RUNS);

	}

	protected void updateRezultTotalMap(String methodName, long executionTime) {
		if (rezultTotalMap.containsKey(methodName)) {
			executionTime += rezultTotalMap.get(methodName);
		}
		rezultTotalMap.put(methodName, executionTime);
	}

}
