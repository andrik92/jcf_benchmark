package com.epam.cdp.jcf.dao.impl;

import com.epam.cdp.jcf.dao.BenchmarkDao;
import com.epam.cdp.jcf.model.Benchmark;
import com.google.common.collect.TreeMultimap;

public class BenchmarkDaoImpl implements BenchmarkDao {

	public static TreeMultimap<String, Benchmark> mapBenchmarkResultOfList = TreeMultimap.create();
	
	@Override
	public void addBenchmark(String keyCollectionType, Benchmark benchmark) {
		mapBenchmarkResultOfList.put(keyCollectionType, benchmark);
	}
}
