package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import bean.Complaint;

public class ComplaintDao {
	public int insertComp(Complaint comp) {
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="insert into complaint(cp_id,c_id,d_id,complaints) values(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,0);
			pstmt.setString(2,comp.getC_id());
			pstmt.setString(3,comp.getD_id());
			pstmt.setString(4,comp.getComplaints());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//统计司机被投诉的次数
	public Map<String,Integer> countComp(){
		Map<String,Integer> count=new HashMap<String,Integer>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select driver.d_id,count(complaints) from driver left join complaint on driver.d_id=complaint.d_id "
				+ "group by driver.d_id "
				+ "order by count(complaints) desc";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String d_id=rs.getString(1);
				int num=rs.getInt(2);
				count.put(d_id, num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return count;
	}
}
