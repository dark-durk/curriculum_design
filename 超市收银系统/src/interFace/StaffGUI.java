package interFace;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;

import entity.OrderDetail;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;

import jdbc.DBConnection;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.FlowLayout;

public class StaffGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtgID;
	private JTextField txtSumPrice;
	private JTable table;
	
	private final List<String> TITLE=Arrays.asList("编号","商品编号","商品名","商品种类","单位","售价","数量","金额");
	private Vector<Vector<Object>> dataModel=new Vector<Vector<Object>>();
	private int count=1;
	
	private String uAccount;
	private String uName;
	private String sex;
	private String uNum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffGUI frame = new StaffGUI();
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
	public StaffGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//面板上方设置系统名称***************************		
		JPanel panel = new JPanel();
		panel.setBounds(15, 0, 721, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("超市收银系统");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(269, 15, 185, 36);
		panel.add(lblNewLabel);
//设置选项卡面板******************************		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 68, 721, 333);
		contentPane.add(tabbedPane);
		
		JPanel p1=new JPanel();
		tabbedPane.addTab("收银",p1);
		
		JPanel p2=new JPanel();
		tabbedPane.addTab("个人信息",p2);
		p2.setLayout(null);
		
 
	//购物表格	********************************************
		Vector<String> title=new Vector<String>(TITLE);
		DefaultTableModel model=new DefaultTableModel(dataModel,title) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		JTable table=new JTable(model);
		p1.add(table.getTableHeader());
		p1.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(132, 5, 452, 402);
		p1.add(scrollPane);
		
//收银界面添加商品那一部分设计************************		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 416, 737, 53);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblgID = new JLabel("商品编号");
		lblgID.setBounds(15, 15, 81, 21);
		panel_2.add(lblgID);
		
		txtgID = new JTextField();
		txtgID.setBounds(98, 12, 152, 27);
		panel_2.add(txtgID);
		txtgID.setColumns(10);

//员工添加商品*************************	**************
		JButton btnAddg = new JButton("添加商品");
		btnAddg.setBounds(265, 11, 113, 29);
		panel_2.add(btnAddg);
		btnAddg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gID=txtgID.getText();
				OrderDetail order=new OrderDetail();
				Connection conn=DBConnection.getConn();
				PreparedStatement psmt=null;
				ResultSet rs=null;
				String sql="select * from Goods where gID=?";
				
				try {
					psmt=conn.prepareStatement(sql);
					psmt.setString(1, gID);
					rs=psmt.executeQuery();
					if(rs.next()) {
						Vector<Object> v = new Vector<Object>();
						v.add(count);
						v.add(rs.getString("gID"));
						v.add(rs.getString("gName"));
						v.add(rs.getString("gType"));
						v.add(rs.getString("gUnit"));
						v.add(rs.getBigDecimal("gPrice"));
						v.add(1.0);
						v.add(rs.getBigDecimal("gPrice").multiply(new BigDecimal( v.get(6).toString())));
						dataModel.add(v);
						
						count++;
					}else {
						JOptionPane.showMessageDialog(null, "商品编号不存在","错误提示",JOptionPane.ERROR_MESSAGE);
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
		
				
//员工结算金额*********************************************	
		txtSumPrice = new JTextField();
		txtSumPrice.setBounds(594, 12, 96, 27);
		panel_2.add(txtSumPrice);
		txtSumPrice.setColumns(10);
		
		JButton btnSettle = new JButton("结算");
		btnSettle.setBounds(410, 11, 74, 29);
		panel_2.add(btnSettle);
		
		JLabel lblSumPrice = new JLabel("总金额：");
		lblSumPrice.setBounds(521, 15, 58, 21);
		panel_2.add(lblSumPrice);
//结算按钮监听器**********************************************		
		btnSettle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal gsPrice=new BigDecimal("0.00");
				for(int i=0;i<dataModel.size();i++) {
					Object obj = dataModel.get(i).get(7);
					gsPrice=gsPrice.add(new BigDecimal(obj.toString()));
				}
				txtSumPrice.setText(String.valueOf(gsPrice));
				String gsID=getOrderID();
				String uAccount=Login.getuAccount();
				
				Date now=new Date();
				Timestamp gsDate=new Timestamp(now.getTime());
				int gsNum=dataModel.size();
				
				Connection conn=DBConnection.getConn();
				PreparedStatement psmt=null;
				PreparedStatement psmt2=null;
				try {
					///sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法 
					conn.setAutoCommit(false);
					String sql1="insert into goodsSales(gsID,uAccount,gsDate,gsNum,gsPrice) values(?,?,?,?,?)";
					psmt=conn.prepareStatement(sql1);
					psmt.setString(1, gsID);
					psmt.setString(2,uAccount);
					psmt.setTimestamp(3,gsDate);
					psmt.setInt(4,gsNum);
					psmt.setBigDecimal(5,gsPrice);
					psmt.executeUpdate();
					
					String sql2="insert into gsDetail(gsdID,gsID,gID,gName,gType,gUnit,gPrice,gsdNum,gsdPrice) values(?,?,?,?,?,?,?,?,?)";
					psmt2=conn.prepareStatement(sql2);
					for(int i=0;i<dataModel.size();i++) {
						int gsdID=(int) dataModel.get(i).get(0);
						String gID=(String) dataModel.get(i).get(1);
						String gName=(String) dataModel.get(i).get(2);
						String gType=(String) dataModel.get(i).get(3);
						String gUnit=(String)dataModel.get(i).get(4);
						BigDecimal gPrice=new BigDecimal(dataModel.get(i).get(5).toString());
						Double gsdNum=Double.parseDouble(dataModel.get(i).get(6).toString());
						BigDecimal gsdPrice=new BigDecimal(dataModel.get(i).get(7).toString());
						
						psmt2.setString(2,gsID);
						psmt2.setInt(1,gsdID);
						psmt2.setString(3,gID);
						psmt2.setString(4,gName);
						psmt2.setString(5,gType);
						psmt2.setString(6,gUnit);
						psmt2.setBigDecimal(7,gPrice);
						psmt2.setDouble(8,gsdNum);
						psmt2.setBigDecimal(9,gsdPrice);
						psmt2.executeUpdate();
					}
					///
					conn.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						psmt2.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DBConnection.closeConn(null, psmt, conn);
				}
			}//actionListener
		});
		
	//一个swing界面的无法缺少的按钮	
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(578, 43, 123, 29);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		p1.add(btnNewButton);
//收银界面相关操作按钮*********************************************	
		//修改商品购买数量********************************
		JButton btnAlt = new JButton("改数量");
		btnAlt.setBounds(578, 87, 123, 29);
		p1.add(btnAlt);
		btnAlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//先执行语句再执行内部方法体
				QulifyBuyNum frame1=new QulifyBuyNum();
				frame1.setVisible(true);
				frame1.getBtn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int ID=Integer.parseInt(frame1.getTxtID().getText());
						String strNum=frame1.getTxtNum().getText();
						BigDecimal price=new BigDecimal(String.valueOf(0.0));
						if(ID<=dataModel.size()) {
						price=new BigDecimal(dataModel.get(ID-1).get(5).toString()).multiply(new BigDecimal(strNum));
						dataModel.get(ID-1).setElementAt(strNum,6);
						dataModel.get(ID-1).setElementAt(price,7);
						}else
							JOptionPane.showMessageDialog(null,"编号不存在");
						//System.out.println(dataModel.get(Integer.parseInt(strID)-1).setElementAt(strNum,6));
						table.validate();
						table.updateUI();
					}
				});
			}
		});
		//删除购买的某一商品**********************
		JButton btnDel = new JButton("删单品");
		btnDel.setBounds(578, 131, 123, 29);
		p1.add(btnDel);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1) {
					dataModel.remove(selectedRow);
					count=count-1;
					for(int i=selectedRow;i<dataModel.size();i++) {
						int num=(int)dataModel.get(i).get(0);
						num=num-1;
						dataModel.get(i).setElementAt(num,0);
					}
				}
				table.validate();
				table.updateUI();
			}
		});
		//将表格清零*************************************
		JButton btnClear = new JButton("清零");
		btnClear.setBounds(578, 175, 123, 29);
		p1.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showConfirmDialog(null, "确定删除整单吗?","删除整单",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					dataModel.removeAllElements();
					count=1;
					txtSumPrice.setText("");
				}
				table.validate();
				table.updateUI();
			}
		});
		
//员工个人信息管理***********************
		getUserInfo();
		JLabel lblNewLabel_1 = new JLabel("员工编号：");
		lblNewLabel_1.setBounds(284, 29, 81, 21);
		p2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(Login.getuAccount());
		lblNewLabel_2.setBounds(432, 29, 81, 21);
		p2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("员工姓名：");
		lblNewLabel_3.setBounds(284, 77, 81, 21);
		p2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(uName);
		lblNewLabel_4.setBounds(432, 77, 81, 21);
		p2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("员工性别：");
		lblNewLabel_5.setBounds(284, 126, 81, 21);
		p2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(sex);
		lblNewLabel_6.setBounds(432, 126, 81, 21);
		p2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("联系电话：");
		lblNewLabel_7.setBounds(284, 178, 81, 21);
		p2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(uNum);
		lblNewLabel_8.setBounds(432, 178, 81, 21);
		p2.add(lblNewLabel_8);
//员工个人电话号码修改******************
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.setBounds(533, 174, 123, 29);
		p2.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uNum = JOptionPane.showInputDialog(null, "请输入新号码", "电话号码", JOptionPane.QUESTION_MESSAGE);
				if (uNum == null || uNum.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入号码", "提示信息", JOptionPane.ERROR_MESSAGE);
				} else {
					String uAccount = Login.getuAccount();
					String sql = "update User set uNum=? where uAccount=?";
					Connection conn = DBConnection.getConn();
					PreparedStatement psmt = null;
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, uNum);
						psmt.setString(2, uAccount);
						psmt.executeUpdate();
						lblNewLabel_8.setText(uNum);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBConnection.closeConn(null, psmt, conn);
					}
				}
			}
		});
		
//员工个人信息密码修改*************************		
		JButton btnNewButton_3 = new JButton("更改密码");
		btnNewButton_3.setBounds(284, 234, 123, 29);
		p2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uPass1 = JOptionPane.showInputDialog(null, "请输入新密码");
				String uPass2 = JOptionPane.showInputDialog(null, "请再次输入新密码");
				
				if(uPass1.equals(uPass2)&&uPass1!=null&&uPass2!=null){
					String uAccount = Login.getuAccount();
					String sql = "update User set uPassword=? where uAccount=?";
					Connection conn = DBConnection.getConn();
					PreparedStatement psmt = null;
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, uPass1);
						psmt.setString(2, uAccount);
						psmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"修改成功！");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBConnection.closeConn(null, psmt, conn);
					}
				}else
					JOptionPane.showMessageDialog(null,"两次密码输入不一致！");
			}
		});
		
		
	}//public StaffGUI
//获取员工个人除密码外的信息**************************	
	public void getUserInfo() {
		//先获取登陆界面的员工账号
		String uAccount=Login.getuAccount();
		String sql="select * from User where uAccount=?";
		Connection conn=DBConnection.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, uAccount);
			rs=psmt.executeQuery();
			while(rs.next()) {
				uAccount=rs.getString("uAccount");
				uName=rs.getString("uName");
				sex=rs.getString("uSex");
				uNum=rs.getString("uNum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConn(rs, psmt, conn);
		}
	}
	
//随机生成订单号（时间yyMMddHHmmss+随机生成的两位数0-9组成)
	public String getOrderID() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		String date=sdf.format(new Date());
		String result="";
		Random rand=new Random();
		for(int i=0;i<2;i++) {
			result+=rand.nextInt(10);
		}
		return date+result;
	}
}
