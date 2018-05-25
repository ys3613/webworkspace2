package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.member;

public class memberDao {

	public member selectOne(Connection conn, String id, String pw) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		member m = null;
		String query="select* from member where userId=? and userPwd=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				m = new member();
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setName(rset.getString("USERNAME"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddr(rset.getString("ADDRESS"));
				m.setGender(rset.getString("GENDER"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				m.setActivation(rset.getString("activation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return m;
	}

	public ArrayList<member> selectAll(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select* from member";
		ArrayList<member> list = new ArrayList<member>();
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				member m = new member();
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setName(rset.getString("USERNAME"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddr(rset.getString("ADDRESS"));
				m.setGender(rset.getString("GENDER"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				m.setActivation(rset.getString("activation"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(stmt);
		return list;
	}

	public int updateActivation(Connection conn, String id, String activation) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		member m = null;
		String query="update member set activation=? where userid=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, activation);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
			if(result>0)
			{
				conn.commit();
				result = 1;
			}
			else
			{
				conn.rollback();
				result = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		return result;
	}

	public int joinUs(Connection conn, member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query="insert into member values(?,?,?,?,?,?,?,?,?,SYSDATE,?)";
		

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getUserId() );
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddr());
			pstmt.setString(8, m.getGender());
			pstmt.setString(9, m.getHobby());
			pstmt.setString(10, m.getActivation());
			
			result = pstmt.executeUpdate();
			
			if(result>0)
			{
					conn.commit();
					result = 1;
			}
			else
			{
					conn.rollback();
					result =0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		
			
		return result;
	}

}
