package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Car;
import bean.Maintenance;

public class CarDao {
	
	//��˾��ע���ʱ��ͽ�������ĳ��ƺŲ��룬void
	public void insertCar(String car_id,String d_id) {
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="insert into car(car_id,d_id) values(?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,car_id);
			pstmt.setString(1,d_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
	}
	//���ݵ�½��d_id��ȡ�����е�car��Ϣ
	public Car getCarInfo(String d_id) {
		Car car=new Car();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from car where d_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,d_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				car.setBrand(rs.getString("brand"));
				car.setPurchaseDate(rs.getDate("purchaseDate"));
				car.setColor(rs.getString("color"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return car;
	}
	
	//���³�����Ϣ�����ƺź�ӵ���߲��ܸ�
	public int updateCar(Car car) {
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="update car set purchaseDate=?,brand=?,color=? where d_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setDate(1,car.getPurchaseDate());
			pstmt.setString(2,car.getBrand());
			pstmt.setString(3,car.getColor());
			pstmt.setString(4,car.getD_id());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//����һ��������Ϣ�ļ���
	public List<Car> getAllCar(){
		List<Car> list=new ArrayList<>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from car";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Car car=new Car();
				car.setCar_id(rs.getString("car_id"));
				car.setD_id(rs.getString("d_id"));
				car.setBrand(rs.getString("brand"));
				car.setPurchaseDate(rs.getDate("purchaseDate"));
				car.setColor(rs.getString("color"));
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return list;
	}
}
