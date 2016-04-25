package com.epam.cdp.jcf.service;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.epam.cdp.jcf.model.Benchmark;

public class QueueBenchmarkService extends CollectionBenchmarkService{
	
	private void peek(Queue<String> queue) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			queue.peek();
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("peek", executionTime);
	}
	
	private void poll(Queue<String> queue) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			queue.poll();
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("poll", executionTime);
	}
	
	private void offer(Queue<String> queue) {
		long startTime = System.nanoTime();

		for (int i = 0; i < num; i++) {
			queue.offer(String.format(pattern, i));
		}

		long executionTime = System.nanoTime() - startTime;

		updateRezultTotalMap("offer", executionTime);
	}
	
	public void runBenchmarkTest(int numberOfItems) {
		Queue<String> arrayDeque = new ArrayDeque<String>();
		Queue<String> arrayDequeWithInitSize = new ArrayDeque<String>(numberOfItems);
		Queue<String> linkedList = new LinkedList<String>();
		Queue<String> priorityQueue = new PriorityQueue<String>();
		Queue<String> priorityQueueWithInitSize = new PriorityQueue<String>(numberOfItems);
		
		runTest("LinkedList", linkedList, numberOfItems);
		runTest("ArrayDeque", arrayDeque, numberOfItems);
		runTest("ArrayDeque with init size", arrayDequeWithInitSize, numberOfItems);
		runTest("PriorityQueue", priorityQueue, numberOfItems);
		runTest("PriorityQueue with init size", priorityQueueWithInitSize, numberOfItems);
	}

	private void runTest(String name, Queue<String> queue, int numberOfItems) {
		this.num = numberOfItems;

		rezultTotalMap = new HashMap<String, Long>();
		rezultTotalMemoryUsage = 0;

		for (int i = 0; i < RUNS; i++) {
			add(queue);
			contains(queue);
			removeByObject(queue);
			offer(queue);
			peek(queue);
			poll(queue);
		}

		for (Map.Entry<String, Long> entry : rezultTotalMap.entrySet()) {
			benchmarkDao.addBenchmark(name, new Benchmark(entry.getKey(), num, RUNS, entry.getValue() / RUNS));
		}
		
		benchmarkDao.addMemoryUsageResult(name, rezultTotalMemoryUsage / RUNS);
	}

}