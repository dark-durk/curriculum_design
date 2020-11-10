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

public class AddGoods extends JFrame {

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
					AddGoods frame = new AddGoods();
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
	public AddGoods() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品编号：");
		lblNewLabel.setBounds(72, 15, 81, 21);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(187, 15, 96, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("商品名称：");
		lblNewLabel_1.setBounds(72, 51, 81, 21);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("商品种类：");
		lblNewLabel_2.setBounds(72, 87, 81, 21);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("商品价格：");
		lblNewLabel_3.setBounds(72, 123, 81, 21);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("商品单位：");
		lblNewLabel_4.setBounds(72, 159, 81, 21);
		contentPane.add(lblNewLabel_4);

		textField_1 = new JTextField();
		textField_1.setBounds(187, 48, 96, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(187, 84, 96, 27);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(187, 120, 96, 27);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(187, 159, 96, 27);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

//增加商品*********************************
		//增加前判断输入的商品编号或商品名是否存在，不存在将文本框中的内容插入到Goods表
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into Goods(gID,gName,gType,gPrice,gUnit) values(?,?,?,?,?)";
				String sql1 = "select * from Goods where gID=? or gName=?";
				Connection conn = DBConnection.getConn();
				PreparedStatement psmt = null;
				PreparedStatement psmt1 = null;
				ResultSet rs = null;
				try {
					psmt1 = conn.prepareStatement(sql1);
					psmt1.setString(1, textField.getText());
					psmt1.setString(2, textField_1.getText());
					rs = psmt1.executeQuery();
					if (rs.next() == false) {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, textField.getText());
						psmt.setString(2, textField_1.getText());
						psmt.setString(3, textField_2.getText());
						psmt.setBigDecimal(4, new BigDecimal(textField_3.getText()));
						psmt.setString(5, textField_4.getText());
						psmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "添加成功");
					} else {
						JOptionPane.showMessageDialog(null, "商品已存在！请重新输入");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					DBConnection.closeConn(rs, psmt1, conn);
					try {
						if (psmt != null)
							psmt.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(72, 195, 96, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton_1.setBounds(209, 195, 86, 29);
		contentPane.add(btnNewButton_1);
	}

}
