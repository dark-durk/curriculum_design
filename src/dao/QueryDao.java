package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Driver;
import bean.Evaluation;

public class QueryDao {
	public List<String> getAllEvaOfDriver(String d_id){
		//ÆÀÂÛ¼¯ºÏ
		List<String> eva=new ArrayList<>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select evaluations,evaluation.o_id from evaluation where evaluation.o_id in(select o_id from orders where d_id =?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,d_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String e=rs.getString(1);
				eva.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(rs, pstmt, conn);
		}
		return eva;
	}
}
