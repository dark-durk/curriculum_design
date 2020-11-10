package interFace;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;

import jdbc.DBConnection;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	private static String uAccount;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setTitle("��½");
		frame.setBounds(100, 100, 800, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�˻���");
		lblNewLabel.setBounds(201, 134, 81, 21);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(347, 131, 190, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("���룺");
		label.setBounds(201, 207, 81, 21);
		frame.getContentPane().add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(347, 204, 194, 27);
		passwordField.setEchoChar('*');
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("����Ա��½");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login()) {
					StaffGUI staff = new StaffGUI();
					staff.setVisible(true);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "�û��������벻��ȷ", "������Ϣ", JOptionPane.ERROR_MESSAGE);
					System.out.println("��½ʧ�ܣ�");
				}
			}

		});
		button.setBounds(201, 299, 131, 29);
		frame.add(button);
		
		JButton button_1 = new JButton("����Ա��½");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login()) {
					Manager manager= new Manager();
					manager.setVisible(true);
					//�̶��ָ����ķָ��ߣ�������setVisible���������
					Manager.splitPane.setDividerLocation(0.15);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "�û��������벻��ȷ", "������Ϣ", JOptionPane.ERROR_MESSAGE);
					System.out.println("��½ʧ�ܣ�");
				}
			}
		});
		button_1.setBounds(413, 299, 131, 29);
		frame.getContentPane().add(button_1);
	}
	
//	�������ݿ���֤�˺������Ƿ���ȷ�����ز���ֵ**************************************
	public boolean login() {
		    uAccount=textField.getText();
			String uPassword=new String(passwordField.getText());
			Connection conn = DBConnection.getConn();
			PreparedStatement psmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM User WHERE uAccount = ? AND uPassword = ?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, uAccount);
				psmt.setString(2, uPassword);
				rs = psmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.closeConn(rs, psmt, conn);
			}
			return false;
	}
//��̬������ȡ��½������˺�**************************
	public static String getuAccount() {
		return uAccount;
	}
}
