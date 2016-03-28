package com.epam.cdp.jcf.model;

import java.util.Objects;

public class Benchmark {

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
	public int hashCode() {
		return Objects.hash(methodName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Benchmark other = (Benchmark) obj;
		if (methodName == null) {
			if (other.methodName != null) {
				return false;
			}
		} else if (!methodName.equals(other.methodName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Benchmark [methodName=" + methodName + ", count=" + count
				+ ", executiionTime=" + executionTime + "]";
	}

}
