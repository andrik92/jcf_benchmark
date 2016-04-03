package com.epam.cdp.jcf.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.epam.cdp.jcf.enumeration.CollectionType;
import com.epam.cdp.jcf.operation.TestListOperation;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class Firts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Firts frame = new Firts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Firts() {
		setTitle("Benchmark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox<CollectionType> comboBox = new JComboBox<>();
		comboBox.setBackground(SystemColor.controlLtHighlight);
		comboBox.setModel(new DefaultComboBoxModel<CollectionType>(CollectionType.values()));
		comboBox.setBounds(41, 114, 135, 29);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem() == CollectionType.LIST) {
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
					
					TableFrame frame = new TableFrame();
					frame.setVisible(true);
					
					dispose();
					
				}
			}
		});
		btnNewButton.setBounds(143, 207, 135, 23);
		contentPane.add(btnNewButton);

		JLabel lblBenchmark = new JLabel("Benchmark JCF");
		lblBenchmark.setFont(new Font("Cooper Black", Font.ITALIC, 22));
		lblBenchmark.setBounds(41, 11, 205, 73);
		contentPane.add(lblBenchmark);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.controlLtHighlight);
		textField.setText("10000");
		textField.setBounds(279, 118, 92, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfItems = new JLabel("Number of items:");
		lblNumberOfItems.setBounds(279, 86, 120, 23);
		contentPane.add(lblNumberOfItems);
	}
}
