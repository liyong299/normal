package com.ly.test.awt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskAddUI
{

	private JFrame frame;
	private JPanel panel;
	private JTextPane textPane;
	private JButton btnNewButton;
	private JPanel panel_1;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TaskAddUI window = new TaskAddUI();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TaskAddUI()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textPane = new JTextPane();
		textPane.setText("123");
		panel.add(textPane);
		
		btnNewButton = new JButton("New button1");
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(50);
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(30);
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		
		textField = new JTextField();
		textField.setText("123");
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton_1 = new JButton("aaaa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton_1);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(50);
		flowLayout_1.setHgap(200);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
	}
}
