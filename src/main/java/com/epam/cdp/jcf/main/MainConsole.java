package com.epam.cdp.jcf.main;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.service.ListBenchmarkService;

public class MainConsole {
	public static void main(String[] args) {
		ListBenchmarkService lbs = new ListBenchmarkService();
		lbs.runBenchmarkTest(1000);
		
		System.out.println(BenchmarkDaoImpl.benchmarkResults.toString());
	}
}
