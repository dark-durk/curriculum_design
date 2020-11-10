package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Maintenance;

public class MaintenanceDao {
	//插入维修信息，主键自增
		public int insertMaint(Maintenance m) {
			int code=0;
			Connection conn=MyJdbcUtil.getConn();
			PreparedStatement pstmt=null;
			String sql="insert into maintenance values(?,?,?,?)";
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setLong(1,0);
				pstmt.setString(2,m.getCar_id());
				pstmt.setString(3,m.getCause());
				pstmt.setDate(4,m.getTime());
				code=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MyJdbcUtil.closeConn(null, pstmt, conn);
			}
			return code;
		}
}
