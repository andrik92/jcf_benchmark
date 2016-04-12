package com.epam.cdp.jcf.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class ChartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChartFrame(String title) {
		super(title);
		JPanel chartPanel = createDemoPanel();
		chartPanel.setPreferredSize(new Dimension(1000, 540));
		setContentPane(chartPanel);
	}

	private static CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (String collectionName : BenchmarkDaoImpl.benchmarkResults.keySet()) {
			for (Benchmark benchmark : BenchmarkDaoImpl.benchmarkResults.get(collectionName)) {
				dataset.addValue(benchmark.getExecutiionTime(), collectionName, benchmark.getMethodName());
			}
		}

		return dataset;
	}

	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createLineChart("Benchmark chart", // title
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

		// customise the renderer
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesShapesVisible(0, true);
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setFillPaint(Color.white);

		return chart;
	}

	private static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

}