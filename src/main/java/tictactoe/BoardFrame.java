package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import tictactoe.model.History;
import tictactoe.model.HistoryDAO;

import java.util.Date;
import java.util.Random;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class BoardFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardFrame frame = new BoardFrame(3);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int num = 0;
	final int size;
	final JButton[] buttons_table = new JButton[num];
	int woncountX = 0;
	int woncountO = 0;
	JLabel Label_X = new JLabel("X -");
	JLabel Label_O = new JLabel("- O");
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * Create the frame.
	 */
	public BoardFrame(final int num) {
		this.num = num;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int fWidth = 0;
		int fHeight = 0;
		if(num > 10) {
			 fWidth = 50 * num;
			 fHeight = 50 * num;
		}else {
			 fWidth = 100 * num;
			 fHeight = 100 * num;
		}
		setTitle(num +" x "+num);
		setBounds(100, 100, fWidth, fHeight);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		JPanel panel_Head = new JPanel();
		contentPane.add(panel_Head,BorderLayout.NORTH);
		
		
		Label_X.setFont(new Font("Segoe Print", Font.BOLD, 20));
		panel_Head.add(Label_X);
		
		final JLabel Label_turn = new JLabel("Turn");
		Label_turn.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Label_turn.setFont(new Font("Segoe Print", Font.BOLD, 20));
		panel_Head.add(Label_turn);
		
		
		Label_O.setFont(new Font("Segoe Print", Font.BOLD, 20));
		panel_Head.add(Label_O);
		
		
		
		//GridLayout 
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 22);
		panel.setLayout(new GridLayout(num,num,5,5));
		panel.setBackground(Color.gray);
		
		 size = num*num;
		
		final JPanel[] panel_table = new JPanel[size];
		final JButton[] buttons_table = new JButton[size];
		System.out.println(size);
		
		for (int i = 0; i < buttons_table.length; i++) {
			buttons_table[i] = new JButton();
			buttons_table[i].setBackground(Color.lightGray);
			buttons_table[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			buttons_table[i].setBorder(BorderFactory.createLineBorder(Color.black));
			buttons_table[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < buttons_table.length; j++) {
						if(e.getSource() == buttons_table[j] && buttons_table[j].getText().isEmpty()) {
							buttons_table[j].setText("X");
							
							Random rand = new Random();
							int cpuRan = (int)(Math.random()*size);
							
							while (true) {
								Label_turn.setText("Turn O");
									if(buttons_table[cpuRan].getText().isEmpty()) {
										buttons_table[cpuRan].setText("O");
										Label_turn.setText("Turn X");
										break;
									}else {
										int count=0;
										for (int k = 0; k < buttons_table.length; k++) {
											if(buttons_table[k].getText().isEmpty()) {
												count++;
											}
										}
										if(count == 0) {											
											break;																
										}
									}
									cpuRan = (int)(Math.random()*size);
							}
						}
					}
					
					int index = 0;
					String[][]  table = new String[num][num];
					for (int r = 0; r < num; r++) {	
						for (int k = 0; k < num; k++) {
							table[r][k] = buttons_table[index].getText();
							index++;
						}
					}

					

					int cross2 = num-1;
					int wincheck_cross1 = 0;
					int wincheck_cross2 = 0;
					
					for (int r = 0; r < num; r++) {	
						int wincheck_row = 0;
						int wincheck_col = 0;	
						for (int c = 0; c < num; c++) {
							if(table[r][0] != "" && table[r][0] == table[r][c]) {
								wincheck_row++;
								if(wincheck_row == num) {
									System.out.println("Winer_Row:"+ table[r][0]);
									woncount(table[r][0]);
									showDialog(table[r][0]);
//									buttonEnable(buttons_table);
									reGame(buttons_table,Label_turn);
									
								}
							}
							if(table[0][r] != "" && table[0][r] == table[c][r]) {
								wincheck_col++;
								if(wincheck_col == num) {
									System.out.println("Winer_Col:"+ table[0][r]);
									woncount(table[0][r]);
//									buttonEnable(buttons_table);
									showDialog(table[0][r]);
									reGame(buttons_table,Label_turn);
									
								}
							}				
						}
						if(table[0][0] != "" && table[0][0] == table[r][r]) {
							wincheck_cross1++;
							if(wincheck_cross1 == num) {
								System.out.println("Winer_Cross1:"+ table[0][0]);
								woncount(table[0][0]);
//								buttonEnable(buttons_table);
								showDialog(table[0][0]);
								reGame(buttons_table,Label_turn);
								
							}
						}
						if(table[0][num-1] != "" && table[0][num-1] == table[r][cross2]) {
							wincheck_cross2++;
							if(wincheck_cross2 == num) {
								System.out.println("Winer_Cross2:"+ table[0][num-1]);
								woncount(table[0][num-1]);
//								buttonEnable(buttons_table);
								showDialog(table[0][num-1]);
								reGame(buttons_table,Label_turn);
								
							}
						}
						cross2--;
						System.out.println();
					}
					int count=0;
					for (int k = 0; k < buttons_table.length; k++) {
						if(buttons_table[k].getText().isEmpty()) {
							count++;
						}
					}
					if(count == 0) {											
						JOptionPane.showMessageDialog(null,"Draw","Winner",JOptionPane.PLAIN_MESSAGE);
						reGame(buttons_table,Label_turn);
					}	
					
				}
			});
			
			Label_turn.setText("Turn X");
			panel.add(buttons_table[i]);
		}
		
		contentPane.add(panel,BorderLayout.CENTER);
		
	}
	
	public void buttonEnable(JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}
	}
	
	public void reGame(JButton[] buttons,JLabel label) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText("");
		}
		label.setText("Turn\tX");
	}
	
	public void showDialog(String message) {
		JOptionPane.showMessageDialog(null,"The winner is '"+message+"'","Winner",JOptionPane.PLAIN_MESSAGE);	
	}
	
	public void woncount(String text) {
		if(text == "X") {
			woncountX++;
			Label_X.setText("X "+woncountX);
		}else if (text == "O") {
			woncountO++;
			Label_O.setText(woncountO+" O");
		}
		
		History history = new History();
		history.setSize(size);
		history.setTable(num+ " x " +num);
		history.setWinner(text);
		
		HistoryDAO dao = new HistoryDAO();
		int affected = dao.addHistory(history);
		System.out.println(affected);
	}

}
