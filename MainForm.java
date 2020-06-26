package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectData.ConnectData;
import InOut.InOut;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfIn;
	private JTextField tfOut;
	private JTabbedPane tabbedPane;
	private JTable table;
	DefaultTableModel model;
	boolean isUpdate = false;
	boolean isBackHistory = false;
	static final int indexTabHome = 0;
	static final int indexTabInOutput = 1;
	static final int indexTabHistory = 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 626);
		setTitle("Insane Coloured Triangles");
		
		/**
		 *  Tạo menubar
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Tạo mới nơi nhập xuất dữ liệu
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isUpdate = false;
				isBackHistory = false;
				tabbedPane.setSelectedIndex(indexTabInOutput);
				tfIn.setText("");
				tfOut.setText("");
			}
		});
		mnFile.add(mntmNew);
		
		// Thoát chương trình
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
		// Hiển thị thông tin của nhóm thực hiện
		JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Đồ án lập trình ứng dụng\n"
						+ "Nhóm 5 (Lớp 18TCLC Nhật)\n"
						+ "Đề tài: Insane Triangle Colours\n"
						+ "Thành viên:  Nguyễn Thanh Dũng\n"
						+ "                       Lê Cảnh Kiều Oanh");
			}
		});
		menuBar.add(mnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		/**
		 * Tạo panel chứa các tab
		 */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		/**
		 * Tab Home
		 */
		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("Home", null, panelHome, null);
		panelHome.setLayout(null);
		
		// Panel chứa tiêu đề
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 204, 204));
		panel_3.setBounds(88, 0, 496, 99);
		panelHome.add(panel_3);
		panel_3.setLayout(null);
		
		//Label chứa tiêu đề
		JLabel lblNewLabel = new JLabel("Insane Coloured Triangles");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(93, 13, 299, 73);
		panel_3.add(lblNewLabel);
		
		//Label chứa hình ảnh minh họa
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("triangles_1.png"));
		lblNewLabel_1.setBounds(175, 123, 319, 276);
		panelHome.add(lblNewLabel_1);
		
		// Button "Start" dùng để bắt đầu chương trình 
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(indexTabInOutput);
			}
		});
		btnStart.setBackground(new Color(204, 204, 204));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBounds(88, 428, 168, 45);
		panelHome.add(btnStart);
		
		//Button "Exit" dùng để thoát chương trình
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBackground(new Color(204, 204, 204));
		btnExit.setBounds(416, 428, 168, 45);
		panelHome.add(btnExit);
		
		/**
		 * Tab InOut
		 */
		JPanel panelInOut = new JPanel();
		panelInOut.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("In_Output", null, panelInOut, null);
		panelInOut.setLayout(null);
		
		// Label Input
		JLabel lblNewLabel_2 = new JLabel("Input");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(89, 170, 56, 32);
		panelInOut.add(lblNewLabel_2);
		
		// TextField nhập input
		tfIn = new JTextField();
		tfIn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfIn.setBounds(193, 173, 392, 30);
		panelInOut.add(tfIn);
		tfIn.setColumns(10);
		
		// Label Output
		JLabel lblNewLabel_2_1 = new JLabel("Output");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(89, 233, 67, 32);
		panelInOut.add(lblNewLabel_2_1);
		
		// TextField xuất Output
		tfOut = new JTextField();
		tfOut.setFont(new Font("Tahoma", Font.BOLD, 17));
		tfOut.setBounds(193, 238, 81, 29);
		panelInOut.add(tfOut);
		tfOut.setColumns(10);
		
		// Label hiển thị màu là kết quả 
		JLabel lblResult = new JLabel("");
		lblResult.setBounds(307, 239, 40, 27);
		lblResult.setOpaque(true);
		lblResult.setBackground(new Color(153,153,153));
		panelInOut.add(lblResult);
		
		
		// Button OK
		JButton btnOK1 = new JButton("OK");
		btnOK1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Kiểm tra dữ liệu đầu vào
				
				// Biến kiểm tra xem có lỗi hay không
				boolean isInputError = false;
				
				// Nếu dữ liệu đầu vào rỗng thì bắt lỗi
				if(tfIn.getText().equals("")) {
					isInputError = true;
				}
				
				// Nếu dữ liệu đầu vào khác 3 kí tự R, G, B thì bắt lỗi
				char[] input = tfIn.getText().toCharArray();
				for(int i = 0; i < input.length; i++) {
					if(input[i] != 'R' && input[i] != 'G' && input[i] != 'B') {
						isInputError = true;
						break;
					}
				}
				
				// Sau khi kiểm tra các trường hợp lỗi nếu có lỗi thì in ra thông báo
				if(isInputError == true) {
					try {
						throw new Exception("Dữ liệu vào không hợp lệ\nDữ liệu vào chỉ chứa các kí tự R, G hoặc B") ;
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(rootPane, e1.getMessage(), "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					
					// Nếu không có lỗi thì thực hiện lấy dữ liệu vào và xử lý 
					InOut io = new InOut();
					io.setInput(tfIn.getText());
//					io.Triangle();
					io.Triangle2();
					io.setDTime(Calendar.getInstance().getTime());
					tfOut.setText(io.getOutput());
					
					// Nếu biến kiểm tra Cập nhật = true thì thực hiện việc cập nhật
					if(isUpdate == true) {
						int k = table.getSelectedRow(); // lấy chỉ số dòng được chọn
						model = (DefaultTableModel) table.getModel();
						
						// Lấy thông tin dòng được chọn
						InOut io1 = new InOut();
						io1.setInput(model.getValueAt(k, 1).toString());
						io1.setOutput(model.getValueAt(k, 2).toString());
						try {
							io1.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getValueAt(k, 3).toString()));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						// Chạy thực hiện lại với Input vừa được cập nhật
						InOut io2 = new InOut();
						io2.setInput(tfIn.getText());
//						io2.Triangle();
						io2.Triangle2();
						io2.setDTime(Calendar.getInstance().getTime());
						tfOut.setText(io2.getOutput());
						
						// Thực hiện việc Cập nhật vào cơ sở dữ liệu
						if(new ConnectData().updateInOut(io1, io2) == true) {
							JOptionPane.showMessageDialog(rootPane, "Update Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
						}
						else JOptionPane.showMessageDialog(rootPane, "Not Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
						
						model.setRowCount(0); // Xóa hết thông tin trong bảng hiện có 
						
						ShowData();  // Hiển thị lại dữ liệu vừa cập nhật
						
						isUpdate = false;  // Đặt biến kiểm tra cập nhật về false
						
						tabbedPane.setSelectedIndex(indexTabHistory); // Quay trở lại tab History
						
						
					}else {
						
						// Nếu biến kiểm tra cập nhật = false thì thực hiện việc tạo mới
						if(new ConnectData().addInOut(io) == true) {
							JOptionPane.showMessageDialog(rootPane, "Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							ShowData();
						}else JOptionPane.showMessageDialog(rootPane, "Not Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
					
					// Hiển thị màu là kết quả lên label hiển thị kết quả
					if(tfOut.getText().equals("R")) {
						lblResult.setBackground(Color.RED);
					}else if(tfOut.getText().equals("B")) {
						lblResult.setBackground(Color.BLUE);
					}else if(tfOut.getText().equals("G")) {
						lblResult.setBackground(Color.GREEN);
					}
				}
			}
		});
		btnOK1.setBackground(new Color(204, 204, 204));
		btnOK1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOK1.setBounds(74, 359, 116, 37);
		panelInOut.add(btnOK1);
		
		// Button "Reset" dùng để làm mới lại các Textfield Input, Output
		JButton btnReset1 = new JButton("Reset");
		btnReset1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfIn.setText("");
				tfOut.setText("");
				lblResult.setBackground(new Color(153,153,153));
			}
		});
		btnReset1.setBackground(new Color(204, 204, 204));
		btnReset1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset1.setBounds(287, 359, 116, 37);
		panelInOut.add(btnReset1);
		
		// Button "Cancel"
		JButton btnCancel1 = new JButton("Cancel");
		btnCancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Nếu biến kiểm tra Tab History = true thì quay trở lại Tab History nếu không thì quay trở lại Tab Home
				if(isBackHistory == true) {
					tabbedPane.setSelectedIndex(indexTabHistory);
				}else tabbedPane.setSelectedIndex(indexTabHome);
				
				isUpdate = false;  // Đặt lại biến kiểm tra cập nhật = false
				isBackHistory = false;  // Đặt lại biến kiểm tra Tab History = false
				
				// Reset lai hai TextField Input, Output
				tfIn.setText("");
				tfOut.setText("");
				lblResult.setBackground(new Color(153,153,153));
			}
		});
		btnCancel1.setBackground(new Color(204, 204, 204));
		btnCancel1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel1.setBounds(488, 359, 116, 37);
		panelInOut.add(btnCancel1);
		
		// Panel chứa tiêu đề
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(204, 204, 204));
		panel_3_1.setBounds(89, 0, 496, 99);
		panelInOut.add(panel_3_1);
		
		// label chứa tiêu đề
		JLabel lblNewLabel_4 = new JLabel("Insane Coloured Triangles");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(93, 13, 299, 73);
		panel_3_1.add(lblNewLabel_4);
		
		// Label chứa chữ "R"
		JLabel lblNewLabel_3 = new JLabel("R");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(193, 308, 28, 16);
		panelInOut.add(lblNewLabel_3);
		
		// Label hiển thị màu đỏ
		JLabel lblRed = new JLabel("");
		lblRed.setBounds(218, 309, 40, 16);
		lblRed.setOpaque(true);
		lblRed.setBackground(Color.red);
		panelInOut.add(lblRed);
		
		// Label chứa chữ "G"
		JLabel lblNewLabel_3_1 = new JLabel("G");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(308, 309, 28, 16);
		panelInOut.add(lblNewLabel_3_1);
		
		// Label chứa chữ "B"
		JLabel lblNewLabel_3_2 = new JLabel("B");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(423, 309, 28, 16);
		panelInOut.add(lblNewLabel_3_2);
		
		// Label hiển thị màu xanh lá cây
		JLabel lblGreen = new JLabel("");
		lblGreen.setBounds(336, 309, 40, 16);
		lblGreen.setOpaque(true);
		lblGreen.setBackground(Color.green);
		panelInOut.add(lblGreen);
		
		// Label hiển thị màu xanh da trời
		JLabel lblBlue = new JLabel("");
		lblBlue.setBounds(447, 309, 40, 16);
		lblBlue.setOpaque(true);
		lblBlue.setBackground(Color.blue);
		panelInOut.add(lblBlue);
		
		/**
		 * Tab History
		 */
		
		JPanel panelHistory = new JPanel();
		panelHistory.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("History", null, panelHistory, null);
		panelHistory.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 147, 648, 279);
		panelHistory.add(scrollPane);
		
		// Bảng hiển thị lịch sử sử dụng
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// Xử lý sự kiên cho thao tác click chuột vào hàng bất kì trong bảng Lịch sử
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int k = table.getSelectedRow();		// Lấy index dòng được chọn
				model = (DefaultTableModel) table.getModel();
				
				// Đưa thông tin của dòng được chọn lên hai TextField Input, OutPut
				tfIn.setText(model.getValueAt(k, 1).toString());
				tfOut.setText(model.getValueAt(k, 2).toString());
				
				// Hiển thông tin Output bằng label hiển thị màu
				if(tfOut.getText().equals("R")) {
					lblResult.setBackground(Color.RED);
				}else if(tfOut.getText().equals("B")) {
					lblResult.setBackground(Color.BLUE);
				}else if(tfOut.getText().equals("G")) {
					lblResult.setBackground(Color.GREEN);
				}
			}
		});
		
		// Tạo các cột tương ứng các trường dữ liệu
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order", "Input", "Output", "DateTime"
			}
		));
		scrollPane.setViewportView(table);
		
		// Panel chứa tiêu đề
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(new Color(204, 204, 204));
		panel_3_2.setBounds(88, 0, 496, 99);
		panelHistory.add(panel_3_2);
		
		// Label chưa tên tiêu đề
		JLabel lblNewLabel_5 = new JLabel("Insane Coloured Triangles");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(93, 13, 299, 73);
		panel_3_2.add(lblNewLabel_5);
		
		// Button "Update"
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k = table.getSelectedRow();		// Lấy index dòng được chọn
				model = (DefaultTableModel) table.getModel();
				
				if(k < 0) {
					JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dữ liệu\nHãy chọn dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					isUpdate = true; // Đặt biến kiểm tra cập nhật = true
					isBackHistory = true;  // Đặt biến kiểm tra Tab History = true
					tabbedPane.setSelectedIndex(indexTabInOutput); // Chuyển về Tab In_Output để thực hiện cập nhật dữ liệu
				}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(204, 204, 204));
		btnUpdate.setBounds(88, 463, 116, 37);
		panelHistory.add(btnUpdate);
		
		// Button "Cancel" dùng để quay về Tab In_Output
		JButton btnCancel2 = new JButton("Cancel");
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(indexTabInOutput);
				tfIn.setText("");
				tfOut.setText("");
				lblResult.setBackground(new Color(153,153,153));
			}
		});
		btnCancel2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel2.setBackground(new Color(204, 204, 204));
		btnCancel2.setBounds(468, 463, 116, 37);
		panelHistory.add(btnCancel2);
		
		// Button "Delete" dùng đề xóa thông tin hiển thị trên bảng lịch sử
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k = table.getSelectedRow();		// Lấy index dòng được chọn
				model = (DefaultTableModel) table.getModel();
				
				// Kiểm tra xem đã chọn dữ liệu hay chưa
				if(k < 0) {
					JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn dữ liệu\nHãy chọn dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					// Lấy thông tin dòng được chọn
					InOut io1 = new InOut();
					io1.setInput(model.getValueAt(k, 1).toString());
					io1.setOutput(model.getValueAt(k, 2).toString());
					try {
						io1.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getValueAt(k, 3).toString()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					// Thực hiện xóa thông tin
					int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn xóa?");
					if(a == JOptionPane.YES_OPTION) {
						if(new ConnectData().deleteInOut(io1) == true) {
							JOptionPane.showMessageDialog(rootPane, "Delete Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
						}
						else JOptionPane.showMessageDialog(rootPane, "Not Success!", "Thông báo", JOptionPane.WARNING_MESSAGE);
						
						model.setRowCount(0); // Xóa tất cả dữ liệu trong bảng lịch sử
						
						ShowData(); // Cập nhật lại bảng với thông tin vừa được cập nhật
					}
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBackground(new Color(204, 204, 204));
		btnDelete.setBounds(279, 463, 116, 37);
		panelHistory.add(btnDelete);
		
		ShowData(); // Hiển thị thông tin từ cơ sở dữ liệu khi khởi chạy chương trình
	}
	
	/**
	 * Hàm hiển thị dữ liệu từ cơ sở dữ liệu khi chạy chương trình
	 */
	
	public void ShowData() {
		model = (DefaultTableModel) table.getModel();
		ConnectData cndt = new ConnectData();
		cndt.GetData(table, model);
	}
}
