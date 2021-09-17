package tictactoe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import tictactoe.model.History;
import tictactoe.model.HistoryDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBackground(Color.PINK);
		setForeground(Color.GRAY);
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Play 3x3");
		btnNewButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardFrame frame = new BoardFrame(3);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(157, 11, 120, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setFont(new Font("Segoe Print", Font.BOLD, 15));
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt(textField.getText());
				if(size <= 3 || size > 15) {
					JOptionPane.showMessageDialog(null,"Table must be over 3 - 15","Message",JOptionPane.PLAIN_MESSAGE);	
				}else {
					BoardFrame frame = new BoardFrame(size);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				
			}
		});
		btnNewButton_1.setBounds(172, 106, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("History");
		btnNewButton_2.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryFrame frame = new HistoryFrame();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_2.setBounds(172, 154, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(172, 202, 89, 23);
		contentPane.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setToolTipText("Input Number");
		textField.setBounds(172, 84, 89, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
