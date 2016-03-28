package com.epam.cdp.jcf.model;

public class Benchmark {

	String name;
	String methodName;
	long executiionTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getExecutiionTime() {
		return executiionTime;
	}

	public void setExecutiionTime(long executiionTime) {
		this.executiionTime = executiionTime;
	}

}
