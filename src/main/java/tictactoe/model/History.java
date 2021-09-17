package tictactoe.model;

import java.util.Date;

public class History {
	private int hid;
	private int size;
	private String winner;
	private String table;
	private String play_date;
	
	public History() {
		// TODO Auto-generated constructor stub
	}
	
	public History(int hid, int size, String winner, String table, String play_date) {
		this.hid = hid;
		this.size = size;
		this.winner = winner;
		this.table = table;
		this.play_date = play_date;
	}
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getPlay_date() {
		return play_date;
	}
	public void setPlay_date(String string) {
		this.play_date = string;
	}

}
