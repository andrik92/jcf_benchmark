package com.epam.cdp.jcf.model;

public class Benchmark implements Comparable<Benchmark> {

	String methodName;
	long count;
	long executionTime;

	public Benchmark(String methodName, long count, long executiionTime) {
		super();
		this.methodName = methodName;
		this.count = count;
		this.executionTime = executiionTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getExecutiionTime() {
		return executionTime;
	}

	public void setExecutiionTime(long executiionTime) {
		this.executionTime = executiionTime;
	}

	@Override
	public String toString() {
		return "Benchmark [methodName=" + methodName + ", count=" + count + ", executiionTime=" + executionTime + "]";
	}

	@Override
	public int compareTo(Benchmark benchmark) {
		return (this.methodName).compareTo(benchmark.methodName);
	}

}
