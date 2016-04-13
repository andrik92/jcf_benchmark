package com.epam.cdp.jcf.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.epam.cdp.jcf.enumeration.CollectionType;
import com.epam.cdp.jcf.gui.TableFrame;
import com.epam.cdp.jcf.service.ListBenchmarkService;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int RUNS = 20;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnButton;
	private int numberOfItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
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

		btnButton = new JButton("Run");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numberOfItems = Integer.parseInt(textField.getText());

				if (comboBox.getSelectedItem() == CollectionType.LIST) {

					ListBenchmarkService.runBenchmarkTest(numberOfItems);
//					ListBenchmarkService lbs = new ListBenchmarkService();
//					lbs.runBenchmarkTest(numberOfItems);

					TableFrame frame = new TableFrame();
					frame.setVisible(true);

					dispose();

				}
			}
		});
		btnButton.setBounds(143, 207, 135, 23);
		contentPane.add(btnButton);

		JLabel lblBenchmark = new JLabel("Benchmark JCF");
		lblBenchmark.setFont(new Font("Cooper Black", Font.ITALIC, 22));
		lblBenchmark.setBounds(41, 11, 205, 73);
		contentPane.add(lblBenchmark);

		textField = new JTextField();

		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				verify();
			}

			public void removeUpdate(DocumentEvent e) {
				verify();
			}

			public void insertUpdate(DocumentEvent e) {
				verify();
			}

			public void verify() {
				try {
					numberOfItems = Integer.parseInt(textField.getText());
					btnButton.setEnabled(true);
				} catch (NumberFormatException e) {
					btnButton.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Error: Please enter integer", "Error Massage",
							JOptionPane.ERROR_MESSAGE);
				}

				if (numberOfItems <= 0) {
					btnButton.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Error: Please enter integer bigger than 0", "Error Massage",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

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
