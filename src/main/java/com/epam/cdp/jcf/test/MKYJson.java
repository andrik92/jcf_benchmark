package com.epam.cdp.jcf.test;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;
import com.epam.cdp.jcf.service.ListBenchmarkService;

public class MKYJson {

	public static void main(String[] args) {

		ListBenchmarkService lbs = new ListBenchmarkService();
		lbs.runBenchmarkTest(1000);

		JSONObject objCollection = new JSONObject();

		for (String collectionName : BenchmarkDaoImpl.benchmarkResults.keySet()) {
			JSONObject objBenchmark = new JSONObject();
			for (Benchmark benchmark : BenchmarkDaoImpl.benchmarkResults.get(collectionName)) {
				objBenchmark.put(benchmark.getMethodName(), benchmark.getExecutionTime());
				System.out.println(benchmark.getMethodName() + benchmark.getExecutionTime());
			}
			objCollection.put(collectionName, objBenchmark);
		}

		try {

			FileWriter file = new FileWriter("src/main/resources/benchmark.json");
			file.write(objCollection.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(objCollection);

	}
}
