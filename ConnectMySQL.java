package ConnectData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Input_Output.In_Out;

public class ConnectMySQL {
	private Connection connect;
	
	public ConnectMySQL() {
		
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
	
	// Add new data into database
	public boolean addInOut(In_Out io) {
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
	
	// Delete data from database
	public boolean updateInOut(In_Out io1, In_Out io2) {
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
	
	// Get data from database into Array
	public ArrayList<In_Out> getInOut(){
		ArrayList<In_Out> list = new ArrayList<>();
		String sql = "SELECT * FROM tbldata1";
		try {
			PreparedStatement ps = (PreparedStatement)connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				In_Out io = new In_Out();
				io.setInput(rs.getString(1));
				io.setOutput(rs.getString(2));
				try {
					io.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(3)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				list.add(io);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
