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
	boolean checkUpdate = false;
	boolean checkHistory = false;

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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUpdate = false;
				checkHistory = false;
				tabbedPane.setSelectedIndex(1);
				tfIn.setText("");
				tfOut.setText("");
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
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
		lblNewLabel_1.setIcon(new ImageIcon("D:\\triangles_1.png"));
		lblNewLabel_1.setBounds(175, 123, 319, 276);
		panelHome.add(lblNewLabel_1);
		
		// Button "Start"
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnStart.setBackground(new Color(204, 204, 204));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBounds(88, 428, 168, 45);
		panelHome.add(btnStart);
		
		//Button "Exit"
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
				
				boolean check = true;
				
				if(tfIn.getText().equals("")) {
					check = false;
				}
				
				char[] input = tfIn.getText().toCharArray();
				for(int i = 0; i < input.length; i++) {
					if(input[i] != 'R' && input[i] != 'G' && input[i] != 'B') {
						check = false;
						break;
					}
				}
				
				if(check == false) {
					try {
						throw new Exception("Invalid Input") ;
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(rootPane, e1.getMessage());
					}
				}else {
					InOut io = new InOut();
					io.setInput(tfIn.getText());
//					io.Triangle();
					io.Triangle2();
					io.setDTime(Calendar.getInstance().getTime());
					tfOut.setText(io.getOutput());
					
					if(checkUpdate == true) {
						int k = table.getSelectedRow();
						model = (DefaultTableModel) table.getModel();
						
						InOut io1 = new InOut();
						io1.setInput(model.getValueAt(k, 1).toString());
						io1.setOutput(model.getValueAt(k, 2).toString());
						try {
							io1.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getValueAt(k, 3).toString()));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						InOut io2 = new InOut();
						io2.setInput(tfIn.getText());
//						io2.Triangle();
						io2.Triangle2();
						io2.setDTime(Calendar.getInstance().getTime());
						tfOut.setText(io2.getOutput());
						
						
						if(new ConnectData().updateInOut(io1, io2) == true) {
							JOptionPane.showMessageDialog(rootPane, "Update Success!");
						}
						else JOptionPane.showMessageDialog(rootPane, "Not Success!");
						
						model.setRowCount(0);
						
						ShowData();
						
						checkUpdate = false;
						
						tabbedPane.setSelectedIndex(2);
					}else {
						if(new ConnectData().addInOut(io) == true) {
							JOptionPane.showMessageDialog(rootPane, "Success!");
							ShowData();
						}else JOptionPane.showMessageDialog(rootPane, "Not Success!");
					}
					
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
		
		// Button "Reset"
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
				if(checkHistory == true) {
					tabbedPane.setSelectedIndex(2);
				}else tabbedPane.setSelectedIndex(0);
				
				checkUpdate = false;
				checkHistory = false;
				
				tfIn.setText("");
				tfOut.setText("");
				lblResult.setBackground(new Color(153,153,153));
			}
		});
		btnCancel1.setBackground(new Color(204, 204, 204));
		btnCancel1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel1.setBounds(488, 359, 116, 37);
		panelInOut.add(btnCancel1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(204, 204, 204));
		panel_3_1.setBounds(89, 0, 496, 99);
		panelInOut.add(panel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("Insane Coloured Triangles");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(93, 13, 299, 73);
		panel_3_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("R");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(193, 308, 28, 16);
		panelInOut.add(lblNewLabel_3);
		
		JLabel lblRed = new JLabel("");
		lblRed.setBounds(218, 309, 40, 16);
		lblRed.setOpaque(true);
		lblRed.setBackground(Color.red);
		panelInOut.add(lblRed);
		
		JLabel lblNewLabel_3_1 = new JLabel("G");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(308, 309, 28, 16);
		panelInOut.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("B");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(423, 309, 28, 16);
		panelInOut.add(lblNewLabel_3_2);
		
		JLabel lblGreen = new JLabel("");
		lblGreen.setBounds(336, 309, 40, 16);
		lblGreen.setOpaque(true);
		lblGreen.setBackground(Color.green);
		panelInOut.add(lblGreen);
		
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int k = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				
				tfIn.setText(model.getValueAt(k, 1).toString());
				tfOut.setText(model.getValueAt(k, 2).toString());
				
				if(tfOut.getText().equals("R")) {
					lblResult.setBackground(Color.RED);
				}else if(tfOut.getText().equals("B")) {
					lblResult.setBackground(Color.BLUE);
				}else if(tfOut.getText().equals("G")) {
					lblResult.setBackground(Color.GREEN);
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order", "Input", "Output", "DateTime"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(new Color(204, 204, 204));
		panel_3_2.setBounds(88, 0, 496, 99);
		panelHistory.add(panel_3_2);
		
		JLabel lblNewLabel_5 = new JLabel("Insane Coloured Triangles");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(93, 13, 299, 73);
		panel_3_2.add(lblNewLabel_5);
		
		// Button "Update"
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUpdate = true;
				checkHistory = true;
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(204, 204, 204));
		btnUpdate.setBounds(88, 463, 116, 37);
		panelHistory.add(btnUpdate);
		
		// Button "Cancel"
		JButton btnCancel2 = new JButton("Cancel");
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				tfIn.setText("");
				tfOut.setText("");
				lblResult.setBackground(new Color(153,153,153));
			}
		});
		btnCancel2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel2.setBackground(new Color(204, 204, 204));
		btnCancel2.setBounds(468, 463, 116, 37);
		panelHistory.add(btnCancel2);
		
		// Button "Delete"
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				
				InOut io1 = new InOut();
				io1.setInput(model.getValueAt(k, 1).toString());
				io1.setOutput(model.getValueAt(k, 2).toString());
				try {
					io1.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getValueAt(k, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				if(new ConnectData().deleteInOut(io1) == true) {
					JOptionPane.showMessageDialog(rootPane, "Delete Success!");
				}
				else JOptionPane.showMessageDialog(rootPane, "Not Success!");
				
				model.setRowCount(0);
				
				ShowData();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBackground(new Color(204, 204, 204));
		btnDelete.setBounds(279, 463, 116, 37);
		panelHistory.add(btnDelete);
		
		ShowData();
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
