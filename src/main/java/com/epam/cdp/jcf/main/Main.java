package com.epam.cdp.jcf.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.epam.cdp.jcf.dao.impl.ListBenchmarkDaoImpl;
import com.epam.cdp.jcf.operation.TestListOperation;


public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		List<String> listWithInitSize = new ArrayList<String>(5000);
		
		List<String> linkedList = new LinkedList<String>();
		
		TestListOperation listTest = new TestListOperation();
		listTest.run("ArrayList",list);
		listTest.run("ArrayList with init size", listWithInitSize);
		listTest.run("LinkedList", linkedList);
		
		System.out.println(ListBenchmarkDaoImpl.TIME_DIMENSION);
	}
}
