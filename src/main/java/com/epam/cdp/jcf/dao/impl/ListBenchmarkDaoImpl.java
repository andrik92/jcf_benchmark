package com.epam.cdp.jcf.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.epam.cdp.jcf.dao.BenchmarkDao;
import com.epam.cdp.jcf.model.Benchmark;

public class ListBenchmarkDaoImpl implements BenchmarkDao {

	public static final HashSet<Benchmark> BENCHMARK_LIST = new HashSet<Benchmark>();
	public static final Map<String, HashSet<Benchmark>> TIME_DIMENSION = new HashMap<String, HashSet<Benchmark>>();

	
	
	public void addBenchmark(String keyCollectionType, Benchmark benchmark) {
		BENCHMARK_LIST.add(benchmark);
		TIME_DIMENSION.put(keyCollectionType, BENCHMARK_LIST);
	}

	

	
//	
//	public List<Benchmark> getAllBenchmarksByCollection(String keyCollectionType) {
		
//		return timeDimension.get(key)
		
//		return timeDimension.get(ListImplementations.valueOf(keyCollectionType
//				.toUpperCase()));
//	}
}
