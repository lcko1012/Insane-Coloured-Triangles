package Input_Output_View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectData.ConnectMySQL;
import Input_Output.In_Out;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InOutView extends JFrame {

	private JPanel contentPane;
	private JTextField tfIn;
	private JTextField tfOut;
	private JTable table;
	private ArrayList<In_Out> list;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InOutView frame = new InOutView();
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
	public InOutView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Input");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(77, 34, 56, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Output");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(77, 79, 56, 21);
		contentPane.add(lblNewLabel_1);
		
		tfIn = new JTextField();
		tfIn.setBounds(173, 36, 267, 22);
		contentPane.add(tfIn);
		tfIn.setColumns(10);
		
		tfOut = new JTextField();
		tfOut.setColumns(10);
		tfOut.setBounds(173, 79, 267, 22);
		contentPane.add(tfOut);
		
		list = new ConnectMySQL().getInOut();
		
		
		JButton btnResult = new JButton("Result");
		
		//Event of "Result" button
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get input from text box
				In_Out io = new In_Out();
				io.setInput(tfIn.getText());
				io.Triangle();
				io.setDTime(Calendar.getInstance().getTime());
				tfOut.setText(io.getOutput());
				list.add(io);
				if(new ConnectMySQL().addInOut(io) == true) {
					JOptionPane.showMessageDialog(rootPane, "Add Success!");
				}
				else JOptionPane.showMessageDialog(rootPane, "Not Success!");
				ShowResult();
			}
		});
		btnResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnResult.setBounds(77, 147, 107, 35);
		contentPane.add(btnResult);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfIn.setText("");
				tfOut.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(242, 147, 107, 35);
		contentPane.add(btnReset);
		
		JButton btnUpdate = new JButton("Update");
		
		//Event of "Update" button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				
				//Get input and output from selected row on table
				In_Out io1 = new In_Out();
				io1.setInput(model.getValueAt(k, 1).toString());
				io1.setOutput(model.getValueAt(k, 2).toString());
				try {
					io1.setDTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(model.getValueAt(k, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				// Calculate with new input
				In_Out io2 = new In_Out();
				io2.setInput(tfIn.getText());
				io2.Triangle();
				io2.setDTime(Calendar.getInstance().getTime());
				tfOut.setText(io2.getOutput());
				
				
				if(new ConnectMySQL().updateInOut(io1, io2) == true) {
					JOptionPane.showMessageDialog(rootPane, "Update Success!");
				}
				else JOptionPane.showMessageDialog(rootPane, "Not Success!");
				
				//Update list
				list = new ConnectMySQL().getInOut();
				
				//Refresh data in table
				model.setRowCount(0);
				
				//Show Updated data
				ShowAllResult();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(409, 147, 107, 35);
		contentPane.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 256, 462, 243);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		//Show selected data on text box
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int k = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				tfIn.setText(model.getValueAt(k, 1).toString());
				tfOut.setText(model.getValueAt(k, 2).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Input", "Output","Datetime"
			}
		));
		scrollPane.setViewportView(table);
		ShowAllResult();
	}
	
	// Show new data
	public void ShowResult() {
		In_Out io = list.get(list.size() - 1);
		model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {
				list.size(), io.getInput(), io.getOutput(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io.getDTime())
		});
	}
	
	// Show all data from database
	public void ShowAllResult() {
		model = (DefaultTableModel) table.getModel();
		for(int i = 0; i < list.size(); i++) {
			In_Out io = list.get(i);
			model.addRow(new Object[] {
					i + 1, io.getInput(), io.getOutput(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(io.getDTime())
			});
		}
		table.setModel(model);
	}
}
