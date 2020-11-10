package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Client;
import bean.Driver;
import bean.Order;

public class ClientDao {
	//�ж��Ƿ���ڸ��˺ź����룬��½������һ��booleanֵ
	public boolean isExist(String c_Id,String password) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from client where c_id=? and password=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,c_Id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
//				System.out.println("client ����");
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return false;
	}
	//�ͻ�ע�ᣬ��������
	public int insert(Client client) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		int code=0;
		String sql="insert into client(c_id,password) values(?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,client.getC_id());
			pstmt.setString(2,client.getPassword());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//������Ϣ��ѯ������һ��client
	public Client getClient(String theId) {
		Client client=new Client();
		
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from client where c_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,theId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				client.setC_id(rs.getString("c_id"));
				client.setPassword(rs.getString("password"));
				client.setPhonenum(rs.getString("phonenum"));
				client.setAge(rs.getInt("age"));
				client.setSex(rs.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return client;
	}
	//������Ϣ�޸�,����һ�����֣��ж��Ƿ�ɹ�
	public int changeClient(Client client) {
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="update client set password=?,phonenum=?,age=?,sex=? where c_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,client.getPassword());
			pstmt.setString(2,client.getPhonenum());
			pstmt.setInt(3,client.getAge());
			pstmt.setString(4,client.getSex());
			pstmt.setString(5,client.getC_id());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	
	//���ɶ��������뵽���ݱ�order��
	public int insertOrder(Order order) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		int code=0;
		String sql="insert into orders values(?,?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,null);
			pstmt.setString(2,order.getC_id());
			pstmt.setString(3,order.getD_id());
			pstmt.setString(4,order.getStart());
			pstmt.setString(5,order.getEnd());
			pstmt.setDate(6,order.getTime());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	
	//��ȡ�û����ж�����Ϣ������һ������
	public List<Order> getOrders(String c_id){
		List<Order> list=new ArrayList<>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from orders where c_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,c_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setO_id(rs.getLong("o_id"));
				order.setC_id(rs.getString("c_id"));
				order.setD_id(rs.getString("d_id"));
				order.setStart(rs.getString("start"));
				order.setEnd(rs.getString("end"));
				order.setTime(rs.getDate("time"));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return list;
	}
	//����o_id����c_id��d_id,�õ�һ��ֻ�����������Ե�order����
	public Order getTheIds(long o_id) {
		Order order=new Order();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select c_id,d_id from orders where o_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,o_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				order.setC_id(rs.getString("c_id"));
				order.setD_id(rs.getString("d_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return order;
	}
}
