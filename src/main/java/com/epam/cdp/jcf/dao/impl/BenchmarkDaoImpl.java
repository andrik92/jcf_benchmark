package com.epam.cdp.jcf.dao.impl;

import java.util.Map;
import java.util.TreeMap;

import com.epam.cdp.jcf.dao.BenchmarkDao;
import com.epam.cdp.jcf.model.Benchmark;
import com.google.common.collect.TreeMultimap;

public class BenchmarkDaoImpl implements BenchmarkDao {

	public static TreeMultimap<String, Benchmark> benchmarkResults = TreeMultimap.create();
	public static Map<String, Long> memoryUsage = new TreeMap<String, Long>();

	@Override
	public void addBenchmark(String keyCollectionType, Benchmark benchmark) {
//		benchmarkResults.get(keyCollectionType).
		
		benchmarkResults.put(keyCollectionType, benchmark);
	}

	@Override
	public void addMemoryUsageResult(String keyCollectionType, Long size) {
		
		if (memoryUsage.containsKey(keyCollectionType)){
			size += memoryUsage.get(keyCollectionType);
			size/=2;
		}		
		
		memoryUsage.put(keyCollectionType, size);
	}
}
