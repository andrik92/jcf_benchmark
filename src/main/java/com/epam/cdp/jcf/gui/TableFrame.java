package com.epam.cdp.jcf.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.epam.cdp.jcf.dao.impl.BenchmarkDaoImpl;
import com.epam.cdp.jcf.model.Benchmark;

public class TableFrame extends ApplicationFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table;

	public TableFrame() {
		super("Benchmark");
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Graphic View");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChartFrame graphic = new ChartFrame("Benchmark");
				graphic.pack();
				RefineryUtilities.centerFrameOnScreen(graphic);
				graphic.setVisible(true);
			}
		});

		btnNewButton.setBounds(694, 354, 126, 33);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 825, 332);
		contentPane.add(scrollPane);

		table = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				if (col == 0) {
					return this.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(this,
							this.getValueAt(row, col), false, false, row, col);
				} else {
					return super.prepareRenderer(renderer, row, col);
				}
			}
		};

		table.setModel(initModel());
		table.setEnabled(false);

		scrollPane.setViewportView(table);
	}

	private DefaultTableModel initModel() {
		List<String> methodNameList = new ArrayList<>();

		methodNameList.add("");

		for (Benchmark benchmark : BenchmarkDaoImpl.mapBenchmarkResultOfList
				.get(BenchmarkDaoImpl.mapBenchmarkResultOfList.keySet().first())) {
			methodNameList.add(benchmark.getMethodName());
		}

		int row = BenchmarkDaoImpl.mapBenchmarkResultOfList.keySet().size();
		int column = methodNameList.size();

		String[][] resultSet = new String[row][column];

		row = 0;
		for (String collectionName : BenchmarkDaoImpl.mapBenchmarkResultOfList.keySet()) {
			column = 0;
			resultSet[row][column] = collectionName;
			for (Benchmark benchmark : BenchmarkDaoImpl.mapBenchmarkResultOfList.get(collectionName)) {
				column++;
				resultSet[row][column] = String.valueOf(benchmark.getExecutiionTime());
			}
			row++;
		}

		return new DefaultTableModel(resultSet, methodNameList.toArray());
	}
}
