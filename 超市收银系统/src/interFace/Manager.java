package interFace;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import file.Order;
import jdbc.DBConnection;

import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

public class Manager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	protected static JSplitPane splitPane;
	
	private final List<String> TITLE=Arrays.asList("��Ʒ���","��Ʒ����","��Ʒ����","��Ʒ�ۼ�","��λ");
	private Vector<Vector<Object>> dataModel=new Vector<Vector<Object>>();
	
	private Vector<String> titleOrder=new Vector<String>(Arrays.asList("������","����Ա","ʱ��","����","�ܽ��"));
	private Vector<Vector<Object>> dataOrder=new Vector<Vector<Object>>();
	
	private Vector<String> titleOrders = new Vector<>(
			Arrays.asList("���", "������", "��Ʒ���", "��Ʒ��", "��Ʒ����", "��λ", "�ۼ�", "��������", "���"));
	private Vector<Vector<Object>> dataOrders = new Vector<Vector<Object>>();
	private JTextField txtuAccount;
	private JTextField txtuName;
	private JTextField txtuPassword;
	private JTextField txtuSex;
	private JTextField txtuNum;
	private JTextField txtQulifyuID;
	private JTextField txtStart;
	private JTextField txtEnd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
					frame.setVisible(true);
					splitPane.setDividerLocation(0.15);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Manager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
//********************************		
		JPanel panel = new JPanel();
		panel.setBounds(15, 0, 721, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��������ϵͳ");
		lblNewLabel.setBounds(269, 15, 185, 36);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		panel.add(lblNewLabel);
//***************************************		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 51, 783, 368);
		panel.add(tabbedPane);
//��Ʒ�������******************************		
		 splitPane = new JSplitPane();
		tabbedPane.addTab("��Ʒ����",splitPane);
		//splitPane.setEnabled(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		splitPane.setLeftComponent(panel_1);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		//panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("��������Ʒ��Ż���Ʒ����");
		lblNewLabel_1.setBounds(15, 18, 170, 21);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(214, 15, 170, 27);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("��ѯ");
		btnNewButton.setBounds(439, 14, 92, 29);
		panel_2.add(btnNewButton);


	//������Ʒ���***********************		
				Vector<String> title=new Vector<>(TITLE);
				DefaultTableModel model=new DefaultTableModel(dataModel,title);
				JTable table=new JTable(model);
				panel_2.add(table.getTableHeader());
				panel_2.add(new JScrollPane(table));
				
	//��ѯ��Ʒ*****************************	
				//������Ʒ��Ż���Ʒ����ѯ,�����µ�ģ��dataModel��
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from Goods where gID=? or gName=?";
				String gIDorName=textField.getText();
				Connection conn=DBConnection.getConn();
				PreparedStatement psmt=null;
				ResultSet rs=null;
				try {
					psmt=conn.prepareStatement(sql);
					psmt.setString(1,gIDorName);
					psmt.setString(2,gIDorName);
					rs=psmt.executeQuery();
					if(rs.next()) {
						dataModel.removeAllElements();
						Vector<Object> goods=new Vector<Object>();
						goods.add(rs.getString("gID"));
						goods.add(rs.getString("gName"));
						goods.add(rs.getString("gType"));
						goods.add(rs.getString("gUnit"));
						goods.add(rs.getBigDecimal("gPrice"));
						dataModel.add(goods);
					}else {
						JOptionPane.showMessageDialog(null,"����Ʒ�����ڣ�����������","��ѯ��Ʒ", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DBConnection.closeConn(rs, psmt, conn);
				}
				table.validate();
				table.updateUI();
			}
		});
	//������Ʒ******************************
		//������AddGoods
		JButton btnAddg = new JButton("������Ʒ");
		btnAddg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoods add=new AddGoods();
				add.setVisible(true);
			}
		});
		btnAddg.setBounds(0, 57, 105, 43);
		panel_1.add(btnAddg);
	//ɾ����Ʒ    ************************************	
		//ѡ����Ӧ�ı���У���ȡ��Ӧ�е�gID�����±�Goods����������ģ��dataModel
		JButton btnDelg = new JButton("ɾ����Ʒ");
		btnDelg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1) {
					String sql="delete from Goods where gID=?";
					Connection conn=DBConnection.getConn();
					PreparedStatement psmt=null;
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setString(1,dataModel.get(selectedRow).get(0).toString());
						psmt.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						DBConnection.closeConn(null, psmt, conn);
					}
					dataModel.remove(selectedRow);
				}else {
					JOptionPane.showMessageDialog(null, "δѡ����Ʒ");
				}
				table.validate();
				table.updateUI();
			}
		});
		btnDelg.setBounds(0, 107, 105, 43);
		panel_1.add(btnDelg);
		
	//�޸���Ʒ��Ϣ******************************************
		//������QulifyGoods
		JButton btnQfyg = new JButton("�޸���Ʒ");
		btnQfyg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QulifyGoods qfyGoods=new QulifyGoods();
				qfyGoods.setVisible(true);
			}
		});
		btnQfyg.setBounds(0, 157, 105, 43);
		panel_1.add(btnQfyg);
	//��ʾ������Ʒ��Ϣ*************************************	
		JButton btnShowg = new JButton("�����Ʒ");
		btnShowg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from Goods";
				dataModel.removeAllElements();
				Connection conn=DBConnection.getConn();
				PreparedStatement psmt=null;
				ResultSet rs=null;
				try {
					psmt=conn.prepareStatement(sql);
					rs=psmt.executeQuery();
					while(rs.next()) {
						Vector<Object> goods=new Vector<Object>();
						goods.add(rs.getString("gID"));
						goods.add(rs.getString("gName"));
						goods.add(rs.getString("gType"));
						goods.add(rs.getString("gUnit"));
						goods.add(rs.getBigDecimal("gPrice"));
						dataModel.add(goods);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DBConnection.closeConn(rs, psmt, conn);
				}
				table.validate();
				table.updateUI();
			}
		});
		btnShowg.setBounds(0, 206, 105, 43);
		panel_1.add(btnShowg);
		

//Ա���������****************************		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.14);
		tabbedPane.addTab("Ա������", splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		splitPane_1.setRightComponent(panel_4);
		panel_4.setLayout(null);
		
		JLabel lbluAccount = new JLabel("Ա���˺ţ�");
		lbluAccount.setBounds(123, 54, 96, 21);
		panel_4.add(lbluAccount);
		
		JLabel lbluName = new JLabel("Ա��������");
		lbluName.setBounds(123, 103, 96, 21);
		panel_4.add(lbluName);
		
		JLabel lbluPassword = new JLabel("Ա�����룺");
		lbluPassword.setBounds(123, 139, 96, 21);
		panel_4.add(lbluPassword);
		
		JLabel lbluSex = new JLabel("Ա���Ա�");
		lbluSex.setBounds(123, 183, 96, 21);
		panel_4.add(lbluSex);
		
		JLabel lbluNum = new JLabel("��ϵ�绰��");
		lbluNum.setBounds(123, 230, 96, 21);
		panel_4.add(lbluNum);
		
		txtuAccount = new JTextField();
		txtuAccount.setBounds(256, 51, 96, 27);
		panel_4.add(txtuAccount);
		txtuAccount.setColumns(10);
		
		txtuName = new JTextField();
		txtuName.setColumns(10);
		txtuName.setBounds(256, 93, 96, 27);
		panel_4.add(txtuName);
		
		txtuPassword = new JTextField();
		txtuPassword.setColumns(10);
		txtuPassword.setBounds(256, 136, 96, 27);
		panel_4.add(txtuPassword);
		
		txtuSex = new JTextField();
		txtuSex.setColumns(10);
		txtuSex.setBounds(256, 183, 96, 27);
		panel_4.add(txtuSex);
		
		txtuNum = new JTextField();
		txtuNum.setColumns(10);
		txtuNum.setBounds(256, 227, 181, 27);
		panel_4.add(txtuNum);
		
		JLabel lblQulifyuID = new JLabel("������Ա����ţ�");
		lblQulifyuID.setBounds(39, 285, 164, 21);
		panel_4.add(lblQulifyuID);
		
		txtQulifyuID = new JTextField();
		txtQulifyuID.setBounds(256, 282, 96, 27);
		panel_4.add(txtQulifyuID);
		txtQulifyuID.setColumns(10);
//��ѯ�ı��������Ա�������Ϣ�����ھʹ�ӡ����Ӧ���ı�����*******************************************		
		JButton btnQulifyuID = new JButton("��ѯ");
		btnQulifyuID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtQulifyuID.getText().equals(""))
					JOptionPane.showMessageDialog(null,"������Ա����ţ�");
				else {
					ResultSet rs=qulify(txtQulifyuID.getText());
					try {
						if(rs.next()) {
							txtuAccount.setText(rs.getString(1));
							txtuName.setText(rs.getString(2));
							txtuPassword.setText(rs.getString(3));
							txtuSex.setText(rs.getString(4));
							txtuNum.setText(rs.getString(5));
						}else
							JOptionPane.showMessageDialog(null,"Ա�������ڣ�");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}		
		});
		btnQulifyuID.setBounds(399, 281, 123, 29);
		panel_4.add(btnQulifyuID);
//ɾ���Ѵ��ڵ�Ա��*************************************************		
		JButton btnDels = new JButton("ɾ��Ա��");
		btnDels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uAccount=JOptionPane.showInputDialog(null,"������Ա���˺ţ�");
				if(uAccount==null) {
					return ;
				}else if(uAccount.equals("")) {
					JOptionPane.showMessageDialog(null,"������Ա����ţ�");
				}else if(uAccount!=null&&!uAccount.equals("")) {
					try {
						if(qulify(uAccount).next()) {
							String sql="delete from User where uAccount=?";
							Connection conn=DBConnection.getConn();
							PreparedStatement psmt=null;
							try {
								psmt=conn.prepareStatement(sql);
								psmt.setString(1, uAccount);
								psmt.executeUpdate();
								JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}finally {
								DBConnection.closeConn(null, psmt, conn);
							}
						}else
							JOptionPane.showMessageDialog(null,"��Ա�������ڣ�");
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDels.setBounds(0, 111, 105, 43);
		panel_3.add(btnDels);
		
//	���Ա����Ϣ*******************************************************************	
		JButton btnAdds = new JButton("����Ա��");
		btnAdds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtuAccount.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��¼��Ա����Ϣ");
				} else {
					int flag = JOptionPane.showConfirmDialog(null, "ȷ����Ӹ�Ա����", "���Ա��", JOptionPane.YES_NO_OPTION);
					if (flag == JOptionPane.YES_OPTION) {
						try {
							if (qulify(txtuAccount.getText()).next()==false) {
								String sql = "insert into User(uAccount,uName,uPassword,uSex,uNum) values(?,?,?,?,?)";
								Connection conn = DBConnection.getConn();
								PreparedStatement psmt = null;
								try {
									psmt = conn.prepareStatement(sql);
									psmt.setString(1, txtuAccount.getText());
									psmt.setString(2, txtuName.getText());
									psmt.setString(3, txtuPassword.getText());
									psmt.setString(4, txtuSex.getText());
									psmt.setString(5, txtuNum.getText());
									psmt.executeUpdate();
									JOptionPane.showMessageDialog(null, "��ӳɹ���");
									txtuAccount.setText("");
									txtuName.setText("");
									txtuPassword.setText("");
									txtuSex.setText("");
									txtuNum.setText("");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} finally {
									DBConnection.closeConn(null, psmt, conn);
								}
							} else
								JOptionPane.showMessageDialog(null, "��Ա���Ѵ��ڣ�");
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else
						return;
				}
			}
		});
		btnAdds.setBounds(0, 59, 105, 43);
		panel_3.add(btnAdds);
//�޸�Ա����Ϣ*****************************************************		
		JButton btnQfys = new JButton("�޸���Ϣ");
		btnQfys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "update User set uName=?,uPassword=?,uSex=?,uNum=? where uAccount=?";
				Connection conn = DBConnection.getConn();
				PreparedStatement psmt = null;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(5, txtuAccount.getText());
					psmt.setString(1, txtuName.getText());
					psmt.setString(2, txtuPassword.getText());
					psmt.setString(3, txtuSex.getText());
					psmt.setString(4, txtuNum.getText());
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
		btnQfys.setBounds(0, 164, 105, 43);
		panel_3.add(btnQfys);
		

//  ��ѯ���ۼ�¼************************************		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setResizeWeight(0.14);
		tabbedPane.addTab("���ݲ�ѯ",splitPane_2);
		
		JPanel panel_5 = new JPanel();
		splitPane_2.setLeftComponent(panel_5);

		JPanel panel_6 = new JPanel();
		splitPane_2.setRightComponent(panel_6);
		
		JLabel lblStart = new JLabel("��ʼʱ�䣺");
		lblStart.setBounds(69, 15, 81, 21);
		panel_5.add(lblStart);
		
		txtStart = new JTextField();
		txtStart.setBounds(165, 12, 131, 27);
		panel_5.add(txtStart);
		txtStart.setColumns(10);
		
		JLabel lblEnd = new JLabel("����ʱ�䣺");
		lblEnd.setBounds(339, 15, 81, 21);
		panel_5.add(lblEnd);
		
		txtEnd = new JTextField();
		txtEnd.setBounds(435, 12, 148, 27);
		panel_5.add(txtEnd);
		txtEnd.setColumns(10);
		
		JButton btnQfyOrder = new JButton("ȷ��");
		btnQfyOrder.setBounds(604, 11, 81, 29);
		panel_5.add(btnQfyOrder);

//���������굥���****************************
		
		DefaultTableModel model2 = new DefaultTableModel(dataOrders, titleOrders) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		JTable table2 = new JTable(model2);
		panel_6.add(table2.getTableHeader());
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(151, 64, 452, 402);
		panel_6.add(scrollPane_1);

//�������ʱ���ڵ��ܽ��********************************		
		JLabel lblNo = new JLabel("");
		lblNo.setBounds(379, 18, 81, 21);
		panel_6.add(lblNo);
		
		JLabel lblSumPrice = new JLabel("0.00");
		lblSumPrice.setBounds(379, 18, 81, 21);
		panel_6.add(lblSumPrice);
		JButton btnSumPrice = new JButton("�ܽ�");
		btnSumPrice.setBounds(241, 14, 123, 29);
		panel_6.add(btnSumPrice);
		btnSumPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String start=txtStart.getText();
				String end=txtEnd.getText();

				String sql = "select sum(gsPrice) from goodsSales where gsDate between ? and ?";
				if (!start.equals("") && !end.equals("")) {
					Timestamp t1 = Timestamp.valueOf(start);
					Timestamp t2 = Timestamp.valueOf(end);
					Connection conn = DBConnection.getConn();
					PreparedStatement psmt = null;
					ResultSet rs = null;
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setTimestamp(1, t1);
						psmt.setTimestamp(2, t2);
						rs = psmt.executeQuery();
						if (rs.next()) {
							lblSumPrice.setText(String.valueOf(rs.getDouble(1)));
						}else
							JOptionPane.showMessageDialog(null, "��ʱ���û��������Ʒ");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBConnection.closeConn(rs, psmt, conn);
					}
				} else
					JOptionPane.showMessageDialog(null, "������ʱ���");
			}
		});
		
//�������۶������*********************************		
        //isCellEditable(int row,int column)����ʹ����ѡ�в��ܱ༭
		
		DefaultTableModel model1 = new DefaultTableModel(dataOrder, titleOrder) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		JTable table1 = new JTable(model1);
		panel_5.add(table1.getTableHeader());
		JScrollPane scrollPane = new JScrollPane(table1);
		panel_5.add(scrollPane);
		
//����0��0�п�ʼ����������ͷ		
		//������ϸ��˫����������ʾ������ϸ��Ϣ************************************************************
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedRow = table1.getSelectedRow();
					//��ȡѡ����
					if (selectedRow != -1) {
						//�����ԭ����������
						dataOrders.removeAllElements();
						//��ȡѡ���еĶ�����
						String gsID = table1.getValueAt(selectedRow, 0).toString();
						String sql = "select * from gsDetail where gsID=?";
						Connection conn = DBConnection.getConn();
						PreparedStatement psmt = null;
						ResultSet rs = null;
						try {
							psmt = conn.prepareStatement(sql);
							psmt.setString(1, gsID);
							rs = psmt.executeQuery();
							while (rs.next()) {
								Vector<Object> orders = new Vector<Object>();
								orders.add(rs.getInt(1));
								orders.add(rs.getString(2));
								orders.add(rs.getString(3));
								orders.add(rs.getString(4));
								orders.add(rs.getString(5));
								orders.add(rs.getString(6));
								orders.add(rs.getBigDecimal(7));
								orders.add(rs.getDouble(8));
								orders.add(rs.getBigDecimal(9));
								dataOrders.add(orders);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							DBConnection.closeConn(rs, psmt, conn);
						}

						table2.validate();
						table2.updateUI();
					}
				}
			}
		});
	
//ʱ���ѯ��ť���Զ�������в���*************************************************************
		btnQfyOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///��dataOrder����
				dataOrder.removeAllElements();
				String start=txtStart.getText();
				String end=txtEnd.getText();
				Timestamp t1 = Timestamp.valueOf(start);
				Timestamp t2 = Timestamp.valueOf(end);
				
				Connection conn=DBConnection.getConn();
				PreparedStatement psmt=null;
				ResultSet rs=null;
				//ʱ�䷶Χ��between and
				String sql = "select * from goodsSales where gsDate between ? and ?";
				try {
				psmt = conn.prepareStatement(sql);
				psmt.setTimestamp(1, t1);
				psmt.setTimestamp(2, t2);
				rs = psmt.executeQuery();
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "��ʱ���û��������Ʒ");
				} else {
					do {
						//����do while���
						Vector<Object> order=new Vector<Object>();
						order.add(rs.getString(1));
						order.add(rs.getString(2));
						order.add(rs.getTimestamp(3));
						order.add(rs.getInt(4));
						order.add(rs.getBigDecimal(5));
						dataOrder.add(order);
					}while(rs.next());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBConnection.closeConn(rs, psmt, conn);
			}
				table1.validate();
				table1.updateUI();
		}
		});
		
	//��ӡ�������ļ���************************************
				JPanel p=new JPanel();
				tabbedPane.add("��ӡ",p);
				p.setLayout(null);
				
//				JTextArea textArea = new JTextArea();
//				textArea.setBounds(64, 73, 665, 244);
//				p.add(textArea);
		
				JButton btnWrite = new JButton("��ӡ");
				btnWrite.setBounds(73, 29, 123, 29);
				p.add(btnWrite);
				btnWrite.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fileName = "D:\\javaʵ��\\IO\\order.txt";
						Order.writeFile(fileName);
					}
				});
				
	}
//�ж�Ա������Ƿ���ڣ����ؽ����************************************************
	public ResultSet qulify(String uAccount) {
		Connection conn=DBConnection.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select * from User where uAccount=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,uAccount);
			rs=psmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
