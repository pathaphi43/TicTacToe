package tictactoe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import tictactoe.model.History;
import tictactoe.model.HistoryDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HistoryFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryFrame frame = new HistoryFrame();
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
	public HistoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 245);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//showdata
				try {
//					String pname = textField.getText();
					HistoryDAO dao = new HistoryDAO();
					List<History>  histories = dao.getHistory();
					
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("table");
					model.addColumn("winner");
				
					for(History history: histories) {
						Object [] obj = {history.getTable(),history.getWinner()};
						model.addRow(obj);
					}
					table.setModel(model);
	}catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
