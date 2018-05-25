package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.memberDao;
import member.model.vo.member;

public class memberService {

	public member selectOne(String id, String pw) {
		// TODO Auto-generated method stub
		Connection conn = null;
		member m = null;
		
		conn = JDBCTemplate.connection(conn);
		m = new memberDao().selectOne(conn, id, pw);
		JDBCTemplate.close(conn);
		
		return m;
	}

	public ArrayList<member> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<member> list = null;
		
		conn = JDBCTemplate.connection(conn);
		list = new memberDao().selectAll(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int updateActivation(String id, String activation) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int result =0;
		conn = JDBCTemplate.connection(conn);
		result = new memberDao().updateActivation(conn, id, activation);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int joinUs(member m) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int result = 0;
		conn = JDBCTemplate.connection(conn);
		result = new memberDao().joinUs(conn, m);
		JDBCTemplate.close(conn);
		return result;
	}

}
