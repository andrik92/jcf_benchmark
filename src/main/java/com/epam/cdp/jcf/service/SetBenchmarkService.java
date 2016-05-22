package com.epam.cdp.jcf.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.epam.cdp.jcf.model.Benchmark;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultiset;

public class SetBenchmarkService extends CollectionBenchmarkService{

	public void runBenchmarkTest(int numberOfItems) {
		Set<String> hashSet = new HashSet<String>();
		Set<String> hashSetWithInitSize = new HashSet<String>(numberOfItems);
		Set<String> treeSet = new TreeSet<String>();
		Set<String> linkedHashSet = new LinkedHashSet<String>();
		Set<String> linkedHashSetWithInitSize = new LinkedHashSet<String>(numberOfItems);
		
		Multiset<String> hashMultiSet = HashMultiset.create();
		Multiset<String> hashMultiSetWithInitSize = HashMultiset.create(numberOfItems);
		Multiset<String> treeMultiSet = TreeMultiset.create();
		Set<String> guavaHashSet = Sets.newHashSet();
		
		runTest("HashSet", hashSet, numberOfItems);
		runTest("HashSet with init size", hashSetWithInitSize, numberOfItems);
		runTest("TreeSet", treeSet, numberOfItems);
		runTest("LinkedHashSet", linkedHashSet, numberOfItems);
		runTest("LinkedHashSet with init size", linkedHashSetWithInitSize, numberOfItems);
		runTest("HashMultiset", hashMultiSet, numberOfItems);
		runTest("HashMultiset with init size", hashMultiSetWithInitSize, numberOfItems);
		runTest("TreeMultiset", treeMultiSet, numberOfItems);
		runTest("Guava HashSet", guavaHashSet, numberOfItems);
	}

	private void runTest(String name, Collection<String> set, int numberOfItems) {
		this.num = numberOfItems;

		rezultTotalMap = new HashMap<String, Long>();
		rezultTotalMemoryUsage = 0;

		for (int i = 0; i < RUNS; i++) {
			add(set);
			contains(set);
			removeByObject(set);
		}

		for (Map.Entry<String, Long> entry : rezultTotalMap.entrySet()) {
			benchmarkDao.addBenchmark(name, new Benchmark(entry.getKey(), num, RUNS, entry.getValue() / RUNS));
		}

		benchmarkDao.addMemoryUsageResult(name, rezultTotalMemoryUsage / RUNS);

	}
}
