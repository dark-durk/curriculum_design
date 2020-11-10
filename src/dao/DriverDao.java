package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Driver;

public class DriverDao {
	//登陆判断，是否存在改用户
	public boolean isExist(String theId,String password) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from driver where d_id=? and password=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,theId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
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
	//注册
	public int insert(Driver driver) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		//返回值，判断是否成功
		int code=0;
		String sql="insert into driver(d_id,password,phonenum,name,age,sex,car_id,car_age) values(?,?,?,?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,driver.getD_id());
			pstmt.setString(2,driver.getPassword());
			pstmt.setString(3,driver.getPhonenum());
			pstmt.setString(4,driver.getName());
			pstmt.setInt(5,driver.getAge());
			pstmt.setString(6,driver.getSex());
			pstmt.setString(7,driver.getCar_id());
			pstmt.setDouble(8,driver.getCar_age());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//判断图片是否存在,true表示有
	public boolean isExistHeadshot(String d_id) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select headshot from driver where d_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,d_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1)==null) {
					return false;
				}else {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return false;
	}
	//插入头像
	public int updateHeadshot(String d_id,String fileName) {
		
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="update driver set headshot=? where d_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,fileName);
			pstmt.setString(2,d_id);
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//返回一个driver的信息
	public Driver getDriver(String d_id) {
		Driver driver=new Driver();

		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from driver where d_id like ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,d_id+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				driver.setD_id(rs.getString("d_id"));
				driver.setPassword(rs.getString("password"));
				driver.setPhonenum(rs.getString("phonenum"));
				driver.setName(rs.getString("name"));
				driver.setAge(rs.getInt("age"));
				driver.setSex(rs.getString("sex"));
				driver.setCar_id(rs.getString("car_id"));
				driver.setCar_age(rs.getDouble("car_age"));
				driver.setHeadshot(rs.getString("headshot"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return driver;
	}
	//返回所有司机信息的集合
	public List<Driver> getAllDriver(){
		List<Driver> list=new ArrayList<>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from driver";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Driver driver=new Driver();
				driver.setD_id(rs.getString("d_id"));
				driver.setCar_id(rs.getString("car_id"));
				driver.setPassword(rs.getString("password"));
				driver.setPhonenum(rs.getString("phonenum"));
				driver.setName(rs.getString("Name"));
				driver.setAge(rs.getInt("age"));
				driver.setSex(rs.getString("sex"));
				driver.setHeadshot(rs.getString("headshot"));
				list.add(driver);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return list;
	}
	//根据司机id删除某个司机
	public void deletDriver(String d_id) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="delete from driver where d_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,d_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
	}
}
