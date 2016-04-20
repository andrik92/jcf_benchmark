package com.epam.cdp.jcf.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;
import com.epam.cdp.jcf.service.ListBenchmarkService;

public class WriteToFile {
	
	public static void main(String[] args) {

		ListBenchmarkService lbs = new ListBenchmarkService();
		lbs.runBenchmarkTest(10000);

		try {
			File file = new File("src/main/resources/benchmark.txt");

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			
			PrintWriter pw = new PrintWriter(fw);
			
			// writing

			List<String> methodNameList = new ArrayList<String>();
			methodNameList.add("Type");

			for (Benchmark benchmark : BenchmarkDaoImpl.benchmarkResults
					.get(BenchmarkDaoImpl.benchmarkResults.keySet().first())) {
				methodNameList.add(benchmark.getMethodName());
			}
			methodNameList.add("memory size");

			for (String str : methodNameList) {
				pw.printf("  %-30s", str);
			}

			
			for (String collectionName : BenchmarkDaoImpl.benchmarkResults.keySet()) {
				pw.println();
				pw.printf("%-30s", collectionName);
				for (Benchmark benchmark : BenchmarkDaoImpl.benchmarkResults.get(collectionName)) {
					pw.printf("| %-30s", benchmark.getExecutionTime() / 1000000 + " ms");
				}
				pw.printf("| %-30s",
						String.valueOf(BenchmarkDaoImpl.memoryUsage.get(collectionName) / 1024) + " Kb");
			}

			pw.close();
			
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
