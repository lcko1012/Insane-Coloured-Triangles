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
	
	/**
	 *  Hàm thực hiện kết nối đến cơ sở dữ liệu trong mySQL
	 */
	public ConnectData() {
		
		final String hostName = "localhost";
		final String dbName = "test";
		final String userName = "root";
		final String password = "Thanhdung1";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			connect = DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Hàm thực hiện lấy dữ liệu từ cơ sở dữ liệu đổ lên bảng hiển thị Lịch sử
	 * @param table
	 * @param tableModel
	 */
	public void GetData(JTable table,DefaultTableModel tableModel) {
		String sqlGetData = "SELECT * FROM in_out WHERE Flag = 1";	// Câu truy vấn dùng để hiển thị những thông tin có trường Flag = 1 
																// (Flag là trường hiển thị dữ liệu được phép hiện trên bảng lịch sử hay không)
		try {
			PreparedStatement sm = connect.prepareStatement(sqlGetData);
			ResultSet rs = sm.executeQuery();
			int i = 1; // Biến i dùng để đánh số thự tự các bản ghi
			while(rs.next()) {
				String row[] = new String[5];
				row[0] = Integer.toString(i);	// Cột số thứ tự
				row[1] = rs.getString(1);  	// Cột Input
				row[2] = rs.getString(2);	// Cột Output
				row[3] = rs.getString(3);	// Cột ngày giờ truy cập
				tableModel.addRow(row);
				i++;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	/**
	 * Hàm thêm mới một bản ghi
	 * @param io
	 * @return
	 */
		public boolean addInOut(InOut io) {
			String sqlInsertData = "INSERT INTO in_out() VALUES(?, ?, ?, 1)";
			try {
				PreparedStatement ps = connect.prepareStatement(sqlInsertData);
				ps.setString(1, io.getInput());
				ps.setString(2, io.getOutput());
				ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io.getDTime()));
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		/**
		 * Hàm cập nhật lại thông tin bản ghi
		 * @param io1
		 * @param io2
		 * @return
		 */
		public boolean updateInOut(InOut io1, InOut io2) {
			String sqlUpdateData = "UPDATE in_out SET Input = ?, Output = ?, DateTime = ? WHERE Input = ? AND Output = ? AND DateTime = ?";
			try {
				PreparedStatement ps = connect.prepareStatement(sqlUpdateData);
				ps.setString(1, io2.getInput());
				ps.setString(2, io2.getOutput());
				ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io2.getDTime()));
				ps.setString(4, io1.getInput());
				ps.setString(5, io1.getOutput());
				ps.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io1.getDTime()));
				return ps.executeUpdate() > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		/**
		 * Hàm xóa "mềm" một bản ghi
		 * (Chỉ cập nhật lại biến Flag = 0)
		 * @param io
		 * @return
		 */
		public boolean deleteInOut(InOut io) {
			String sqlDeleteData = "UPDATE in_out SET Flag = 0 WHERE Input = ? and Output = ? and DateTime = ?";
			try {
				PreparedStatement ps = connect.prepareStatement(sqlDeleteData);
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
