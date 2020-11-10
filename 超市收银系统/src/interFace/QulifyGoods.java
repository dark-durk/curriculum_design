package interFace;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class QulifyGoods extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QulifyGoods frame = new QulifyGoods();
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
	public QulifyGoods() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("����Ҫ�޸���Ʒ����Ʒ��ţ�");
		lblNewLabel.setBounds(40, 15, 234, 21);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(40, 39, 96, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("��Ʒ���ƣ�");
		lblNewLabel_1.setBounds(40, 89, 98, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("��Ʒ���ࣺ");
		lblNewLabel_2.setBounds(40, 125, 98, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("��Ʒ�۸�");
		lblNewLabel_3.setBounds(40, 161, 98, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("��Ʒ��λ��");
		lblNewLabel_4.setBounds(40, 197, 98, 21);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 86, 96, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 122, 96, 27);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(153, 158, 96, 27);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(153, 194, 96, 27);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	//������Ʒ����Ȳ�ѯ����Ҫ�޸ĵ���Ʒ��Ϣ����ӡ����Ӧ���ı����У�û�о���ʾ***********************	
		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.setBounds(166, 38, 84, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql= "select * from Goods where gID=?";
				Connection conn = DBConnection.getConn();
				PreparedStatement psmt = null;
				ResultSet rs = null;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, textField.getText());
					rs = psmt.executeQuery();
					if (rs.next() ){
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
						textField_4.setText(rs.getString(5));
					} else {
						JOptionPane.showMessageDialog(null, "��Ʒ�����ڣ�����������");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					DBConnection.closeConn(rs, psmt, conn);
				}
			}
		});
		
	//�޸���Ʒ��Ϣ	ֱ�����ı����н����޸ģ��޸ĺ󰴰�ťupdate��
		JButton btnNewButton_1 = new JButton("ȷ���޸�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "update Goods set gName=?,gType=?,gPrice=?,gUnit=? where gId=?";
				Connection conn = DBConnection.getConn();
				PreparedStatement psmt = null;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, textField_1.getText());
					psmt.setString(2, textField_2.getText());
					psmt.setBigDecimal(3, new BigDecimal(textField_3.getText()));
					psmt.setString(4, textField_4.getText());
					psmt.setString(5, textField.getText());
					psmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					DBConnection.closeConn(null, psmt, conn);
				}
			}
		});
		btnNewButton_1.setBounds(276, 97, 123, 29);
		contentPane.add(btnNewButton_1);
	//���ȡ��  ���ı�����Ϊ�հ�	
		JButton btnNewButton_2 = new JButton("ȡ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton_2.setBounds(276, 145, 123, 29);
		contentPane.add(btnNewButton_2);
	}
}
