package com.epam.cdp.jcf.main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;
import com.epam.cdp.jcf.operation.TestListOperation;

public class ChartFrame extends JFrame {

	/**
	 * Creates a new demo.
	 *
	 * @param title
	 *            the frame title.
	 */
	public ChartFrame(String title) {
		super(title);
		JPanel chartPanel = createDemoPanel();
		chartPanel.setPreferredSize(new Dimension(1000, 540));
		setContentPane(chartPanel);
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return The dataset.
	 */
	private static CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		/*
		
		for(String collectionName: BenchmarkDaoImpl.mapBenchmarkResultOfList.keySet()){
			for (Benchmark benchmark: BenchmarkDaoImpl.mapBenchmarkResultOfList.get(collectionName)){
				dataset.addValue(benchmark.getExecutiionTime(), collectionName, benchmark.getMethodName());
			}
		}
		
		return dataset;
		
	}

	/**
	 * Creates a sample chart.
	 * 
	 * @param dataset
	 *            a dataset.
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createChart(CategoryDataset dataset) {

		
//		ChartFactory.createL
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart("Benchmark chart", // chart
																				// title
				"", // domain axis label
				"Execution time, nanoseconds", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);

		chart.setBackgroundPaint(Color.white);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		// customise the renderer...
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesShapesVisible(0, true);
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setFillPaint(Color.white);

		return chart;
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 */
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(String[] args) {
		/**
		 * RUN TEST TEMP
		 */

		List<String> list = new ArrayList<String>();

		List<String> listWithInitSize = new ArrayList<String>(5000);

		List<String> linkedList = new LinkedList<String>();

		TestListOperation listTest = new TestListOperation();
		listTest.run("ArrayList", list);
		listTest.run("ArrayList with init size", listWithInitSize);
		listTest.run("LinkedList", linkedList);

		/**
		 * 
		 */
		
		ChartFrame demo = new ChartFrame("Benchmark");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}