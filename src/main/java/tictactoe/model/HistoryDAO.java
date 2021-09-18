package tictactoe.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





public class HistoryDAO {
	Connection connect;
		public HistoryDAO() {
			this.connect = getConnection();
		}
		
	public List<History> getHistory() {
			List<History> historys = new ArrayList<History>();
			try {
				String sql = "select `table`,`winner`,`size`, `replay`  from history";
				PreparedStatement ps = this.connect.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					History history = new History();
					history.setTable(rs.getString("table"));
					history.setWinner(rs.getString("winner"));;
					history.setSize(rs.getInt("size"));
					history.setReplay(rs.getString("replay"));
					historys.add(history);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return historys;
		}
	
	public int addHistory(History history) {
		int affected = 0;
		try {
			String sql = "insert into history (`size`, winner, `table`, `replay`) values (?, ?, ?, ?)";
			PreparedStatement ps = this.connect.prepareStatement(sql);
			ps.setInt(1, history.getSize());
			ps.setString(2, history.getWinner());
			ps.setString(3, history.getTable());
			ps.setString(4, history.getReplay());
			
			
			affected = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affected;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties prop = new Properties();
			prop.load(input);
			String url = prop.getProperty("database.url");
			String username = prop.getProperty("database.username");
			String password = prop.getProperty("database.password");
			Connection connect = DriverManager.getConnection(url 
					+ "user="+username+"&password="+password);
			System.out.println("Connection Success");
			return connect;
		}catch(Exception ex) {
			System.out.println("Error"+ex);
		}
		return null;
	}
}
