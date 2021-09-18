# TicTacToe

# วิธีการ setup, run โปรแกรม
 - สามารถ Download ZIP ไป Run ได้เลย
 - การเล่นจะมี 3x3 กับ Bot หรือเล่น 2 คน
 - การเล่นขนาดอื่น ๆ จะ เล่นได้กับบอทเท่านั้น
 - การชนะ ถ้า X หรือ O เรียงครบ แนวตั้ง แนวนอน หรือ แนวทแยง จะเป็นฝ่ายชนะ
 
 
 # วิธีออกแบบโปรแกรมและ algorithm ที่ใช้
  
 
###### เช็คการชนะและเสมอ
 
 เช็คแนวนอนจะวนรอบเพื่อเช็คจากตำแหน่งด้านซ้ายไปขวาถ้ามีการเรียงกันครบกับขนาดของตาราง เช่น 3x3 ถ้าเรียงครบ 3 ก็จะชนะ
 
   ```java
   if(table[r][0] != "" && table[r][0] == table[r][c]) {
      wincheck_row++;
      winner = table[r][0];
	    if(wincheck_row == num) {
         woncount(winner,buffer);
         showDialog(winner);
         reGame(buttons_table,label);
     }
 }
```

เช็คแนวตั้งจะวนรอบเพื่อเช็คจากตำแหน่งด้านบนลงด้านล่างถ้ามีการเรียงกันครบกับขนาดของตาราง เช่น 3x3 ถ้าเรียงครบ 3 ก็จะชนะ


```java
if (table[0][r] != "" && table[0][r] == table[c][r]) {
  wincheck_col++;
  winner = table[0][r];
  if (wincheck_col == num) {
    woncount(winner, buffer);
    showDialog(winner);
    reGame(buttons_table, label);		
		}
 }
```



- แนวทแยงจะมีการเช็คจากด้านบนซ้ายไปขวาล่าง และ เช็คจากด้านบนขวาไปล่างซ้าย

เช็คแนวทแยงที่ 1 จะวนรอบเพื่อเช็คจากตำแหน่งด้านบนซ้ายลงด้านล่างขวาถ้ามีการเรียงกันครบกับขนาดของตาราง เช่น 3x3 ถ้าเรียงครบ 3 ก็จะชนะ
```java
if (table[0][0] != "" && table[0][0] == table[r][r]) {
  wincheck_cross1++;
  if (wincheck_cross1 == num) {
  woncount(table[0][0], buffer);
  showDialog(table[0][0]);
  reGame(buttons_table, label);
		}
}
```


เช็คแนวทแยงที่ 2 จะวนรอบเพื่อเช็คจากตำแหน่งด้านบนขวาไปล่างซ้ายถ้ามีการเรียงกันครบกับขนาดของตาราง เช่น 3x3 ถ้าเรียงครบ 3 ก็จะชนะ

```java
if (table[0][num - 1] != "" && table[0][num - 1] == table[r][cross2]) {
  wincheck_cross2++;
  if (wincheck_cross2 == num) {
    woncount(table[0][num - 1], buffer);
    showDialog(table[0][num - 1]);
    reGame(buttons_table, label);
    }
}
cross2--;
 ```
 
 
เช็คการเสมอจะทำการวนรอบนับปุ่มที่ไม่มีข้อมูล(isEmpty) ถ้าเท่ากับ 0 คือปุ่มทั้งหมดมีข้อมูลหมดแล้วไม่มีตำแหน่งให้กดแล้วก็จะเสมอกัน

```java 
int count = 0;
for (int k = 0; k < buttons_table.length; k++) {
  if (buttons_table[k].getText().isEmpty()) {
    count++;
  }
}
if (count == 0) {
  JOptionPane.showMessageDialog(null, "Draw", "Winner", JOptionPane.PLAIN_MESSAGE);
  woncount("Draw", buffer);
  reGame(buttons_table, label);
}
 ```
 
 
 - Bot จะทำการ Random ตำแหน่งตามขนาดของตาราง ถ้า Random ได้ตำแหน่งที่ว่างจะให้เขียน O ลงไปในตาราง ถ้าช่องนั้นไม่ว่างจะทำการ Random ใหม่ และเมื่อไม่มีตำแหน่งที่ว่างเลยจะทำการเช็ค ว่าตำแหน่งที่ว่างเหลือ 0 ไหม 
 ถ้าใช่จะให้บอทหยุดทำงานถ้าไม่ทำการเช็ค Bot จะทำงานไปเรื่อย ๆ
 ```java 
 if (isBot()) {
  buttons_table[j].setText("X");
  int cpuRan = (int) (Math.random() * size);
  while (isFlag()) {
    Label_turn.setText("Turn O");
    if (buttons_table[cpuRan].getText().isEmpty()) {
      buttons_table[cpuRan].setText("O");
      break;
    } else {
      int count = 0;
      for (int k = 0; k < buttons_table.length; k++) {
        if (buttons_table[k].getText().isEmpty()) {
          count++;
          }
       }
       if (count == 0) {
        break;
       }
     }
     cpuRan = (int) (Math.random() * size);
   }
}
```



###### ประวัติการเล่น

- เมื่อทำการเช็คการชนะกับเสมอแล้วจะทำการเรียก Method woncount() เพื่อทำการนับคะแนนและจะทำการ Insert ข้อมูลลงในฐานข้อมูล โดยจะ Insert จำนวนตาราง ขนาดของตาราง ผู้ชนะ และข้อมูลแต่ละช่องในตาราง

```java 
 	public void woncount(String text, StringBuffer buffer) {
		if (text == "X") {
			woncountX++;
			Label_X.setText("X " + woncountX);
		} else if (text == "O") {
			woncountO++;
			Label_O.setText(woncountO + " O");
		}
		History history = new History();
		history.setSize(num);
		history.setTable(num + " x " + num);
		history.setWinner(text);
		history.setReplay(buffer.toString());

		HistoryDAO dao = new HistoryDAO();
		int affected = dao.addHistory(history);
	}
 ```
 
 
 การ Insert จะดึงข้อมูล จำนวนตาราง ขนาดของตาราง ผู้ชนะ และข้อมูลแต่ละช่องในตาราง และจะทำการ Insert ข้อมูลลงในฐานข้อมูล
 
 ```java 
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
 ```
 
 
 การแสดงประวัติการเล่น จะทำการดึงข้อมูลจากฐานข้อมูลมาแสดง โดยนำ ขนาดของตาราง ผู้ชนะ และข้อมูลแต่ละช่องในตาราง มาแสดง
 ```java 
 try {
			HistoryDAO dao = new HistoryDAO();
			List<History> histories = dao.getHistory();

			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Table size");
			model.addColumn("Winner");
			model.addColumn("Replay table");
			for (History history : histories) {
				Object[] obj = { history.getTable(), history.getWinner(), history.getReplay() };
				model.addRow(obj);

			}
			table.setModel(model);
		} catch (Exception e) {
			// TODO: handle exception
		}
  ```
  
  
  การ Select ประวัติการเล่นมาแสดงจะทำการ Select จำนวนตาราง ผู้ชนะ ขนาดของตาราง  และข้อมูลแต่ละช่องในตาราง แล้ว return กลับไป
  
  ```java  
  public List<History> getHistory() {
		List<History> historys = new ArrayList<History>();
		try {
			String sql = "select `table`,`winner`,`size`, `replay`  from history";
			PreparedStatement ps = this.connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				History history = new History();
				history.setTable(rs.getString("table"));
				history.setWinner(rs.getString("winner"));
				history.setSize(rs.getInt("size"));
				history.setReplay(rs.getString("replay"));
				historys.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return historys;
	}
 ```
  
