package ConnectData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import InOut.InOut;

public class ConnectData {

	private Connection connect;
	
	public ConnectData() {
		
		final String url = "jdbc:mysql://localhost:3306/test";
		final String user = "root";
		final String password = "123456789";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void GetData(JTable table,DefaultTableModel tableModel) {
		String sql = "SELECT * FROM tbldata1";
		try {
			PreparedStatement sm = connect.prepareStatement(sql);
			ResultSet rs = sm.executeQuery();
			int i = 1;
			while(rs.next()) {
				String row[] = new String[5];
				row[0] = Integer.toString(i);
				row[1] = rs.getString(1);
				row[2] = rs.getString(2);
				row[3] = rs.getString(3);
				tableModel.addRow(row);
				i++;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	// Add new data into database
		public boolean addInOut(InOut io) {
			String sql = "INSERT INTO tbldata1() VALUES(?, ?, ?)";
			try {
				PreparedStatement ps = connect.prepareStatement(sql);
				ps.setString(1, io.getInput());
				ps.setString(2, io.getOutput());
				ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io.getDTime()));
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean updateInOut(InOut io1, InOut io2) {
			String sql = "UPDATE tbldata1 SET Input = ?, Output = ?, DateTime = ?"
					+ " WHERE Input = '"+io1.getInput()+"' AND Output = '"+io1.getOutput()+"' "
					+ "AND DateTime = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io1.getDTime())+"'";
			try {
				PreparedStatement ps = connect.prepareStatement(sql);
				ps.setString(1, io2.getInput());
				ps.setString(2, io2.getOutput());
				ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io2.getDTime()));
				return ps.executeUpdate() > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean deleteInOut(InOut io) {
			String sql = "DELETE FROM tbldata1 WHERE Input = ? and Output = ? and DateTime = ?";
			try {
				PreparedStatement ps = connect.prepareStatement(sql);
				ps.setString(1, io.getInput());
				ps.setString(2, io.getOutput());
				ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io.getDTime()));
				return ps.executeUpdate() > 0;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
}
