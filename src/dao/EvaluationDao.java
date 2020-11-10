package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Evaluation;
import bean.Evaluation;

public class EvaluationDao {
	//插入一条没有回复的评价
	public int insertEva(Evaluation eva) {
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="insert into evaluation(e_id,o_id,time,evaluations) values(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,0);
			pstmt.setLong(2,eva.getO_id());
			pstmt.setDate(3,eva.getTime());
			pstmt.setString(4, eva.getEvaluations());
			code=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return code;
	}
	//获取评价表的所有信息，包括回复，返回一个集合
	public List<Evaluation> getAllEvaluation(){
		List<Evaluation> list=new ArrayList<>();
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from Evaluation";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Evaluation Evaluation=new Evaluation();
				Evaluation.setE_id(rs.getLong("e_id"));
				Evaluation.setO_id(rs.getLong("o_id"));
				Evaluation.setTime(rs.getDate("time"));
				Evaluation.setEvaluations(rs.getString("evaluations"));
				Evaluation.setReply(rs.getString("reply"));
				list.add(Evaluation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyJdbcUtil.closeConn(null, pstmt, conn);
		}
		return list;
	}
	//根据e_id插入对应的reply
	public int insertReply(long e_id,String reply) {
		int code=0;
		Connection conn=MyJdbcUtil.getConn();
		PreparedStatement pstmt=null;
		String sql="update evaluation set reply=? where e_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,reply);
			pstmt.setLong(2,e_id);
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
