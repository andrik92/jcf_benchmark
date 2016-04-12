package com.epam.cdp.jcf.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.operation.TestListOperation;

public class WriteToFile {
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();

		List<String> listWithInitSize = new ArrayList<String>(5000);

		List<String> linkedList = new LinkedList<String>();

		TestListOperation listTest = new TestListOperation();
//		listTest.run("ArrayList", list);
//		listTest.run("ArrayList with init size", listWithInitSize);
//		listTest.run("LinkedList", linkedList);

		System.out.println(BenchmarkDaoImpl.benchmarkResults);

		try {

			String content = "This is the content to write into file";

			File file = new File("src/main/resources/benchmark.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// writing

			for (String key: BenchmarkDaoImpl.benchmarkResults.keySet()) {
				bw.write(key);
				bw.newLine();
				bw.write(BenchmarkDaoImpl.benchmarkResults.toString());
				bw.newLine();
			}
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
