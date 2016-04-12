package com.epam.cdp.jcf.dao;

import com.epam.cdp.jcf.model.Benchmark;

public interface BenchmarkDao {

	public void addBenchmark(String keyCollectionType, Benchmark benchmark);
	public void addMemoryUsageResult(String keyCollectionType, Long size);
}
