package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import tictactoe.model.History;
import tictactoe.model.HistoryDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class HistoryFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel_1;

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
	JLabel Label_Head = new JLabel();
	JPanel panel_table = new JPanel();
	JPanel panle_Head = new JPanel();
	
	public HistoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 549, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 192, 549, 169);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		
		
		panel_1.add(panle_Head, BorderLayout.NORTH);
		panle_Head.add(Label_Head);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				getDataFromGUITable(table, panel_table, selectedRow);
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		
		//showdata
				try {
//					String pname = textField.getText();
					HistoryDAO dao = new HistoryDAO();
					List<History>  histories = dao.getHistory();
					
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Table size");
					model.addColumn("Winner");
					model.addColumn("Replay table");
					int i = 0;
					for(History history: histories) {
						Object [] obj = {history.getTable(),history.getWinner(),history.getReplay()};
						model.addRow(obj);

					}
					table.setModel(model);
					
					
					
					
		}catch (Exception e) {
		// TODO: handle exception
		}
				
				
	
}
	String[] data=null;
	private Object[] getDataFromGUITable(JTable table, JPanel panel, int selectedRow) {
		panel.removeAll();
		panel.repaint();
		panel.setBackground(Color.PINK);
        int column = table.getColumnCount() + 1;
        Object[] data = new Object[column];
        for (int i = 0; i < column - 1; i++) {
            data[i] = table.getValueAt(selectedRow, i);
        }
        String[] getsize = data[0].toString().split(" ");
        String[] getreplay = data[2].toString().split(",");
        Label_Head.setText("Winner: "+data[1].toString());
        int size = Integer.parseInt(getsize[0]) * Integer.parseInt(getsize[0]);
        if(Integer.parseInt(getsize[0]) > 5) {
        	setBounds(100, 100, 575,  66 * Integer.parseInt(getsize[0]));
        	panel_1.setBounds(10, 192, 549, 50 * Integer.parseInt(getsize[0]));
        }else {
        	setBounds(100, 100, 575, 411);
        	panel_1.setBounds(10, 192, 549, 169);
        }
        
        data = new String[size];
        JButton[] buttons_table = new JButton[size];
 		panel.setLayout(new GridLayout(Integer.parseInt(getsize[0]),Integer.parseInt(getsize[0]),5,5));
 		for (int i = 0; i < buttons_table.length; i++) {
 				buttons_table[i] = new JButton(getreplay[i]);
 	 			buttons_table[i].setBackground(Color.lightGray);
 	 			buttons_table[i].setFont(new Font("Tahoma", Font.BOLD, 20));
 	 			buttons_table[i].setBorder(BorderFactory.createLineBorder(Color.black));
 	 			panel.add(buttons_table[i]);
 		}
 		
 		panel_1.add(panel,BorderLayout.CENTER);
 		panel_1.validate();
        return data;
    }
}
