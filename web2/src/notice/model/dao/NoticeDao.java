package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {

//	public ArrayList<Notice> noticeAll(Connection conn) {
//		// TODO Auto-generated method stub
//		Statement stmt = null;
//		ResultSet rset = null;
//		String query = "select* from notice";
//		ArrayList<Notice> list = new ArrayList<Notice>();
//		
//		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
//			while(rset.next())
//			{
//				Notice n = new Notice();
//				n.setNoticeNo(rset.getInt("noticeno"));
//				n.setSubject(rset.getString("subject"));
//				n.setContents(rset.getString("contents"));
//				n.setUserId(rset.getString("userid"));
//				n.setRegDate(rset.getDate("regdate"));
//				list.add(n);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		JDBCTemplate.close(rset);
//		JDBCTemplate.close(stmt);
//		return list;
//	}

	public ArrayList<Notice> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 페이지 계산
		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		//끝 페이지 계산
		int end = currentPage*recordCountPerPage;
		System.out.println(start +"/" + end);
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select* from(select notice.*, row_number() over(order by noticeno desc) as num from notice) where num between ?  and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,start);
			pstmt.setInt(2,end);
			
			rset = pstmt.executeQuery();
			while(rset.next())
			{
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	public ArrayList<Notice> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
			String searchPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 페이지 계산
		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		//끝 페이지 계산
		int end = currentPage*recordCountPerPage;
		System.out.println(start +"/" + end);
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select* from(select notice.*, row_number() over(order by noticeno desc) as num from notice where subject like ?) where num between ?  and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,'%'+searchPage+'%');
			pstmt.setInt(2,start);
			pstmt.setInt(3,end);
			
			
			rset = pstmt.executeQuery();
			while(rset.next())
			{
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int recordTotalCount = 0;
		String query = "select count(*) as totalCount from notice";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				recordTotalCount = rset.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		int pageTotalCount = 0;
		if(recordTotalCount%recordCountPerPage != 0)
		{
			pageTotalCount = recordTotalCount/recordCountPerPage +1;
		}
		else
		{
			pageTotalCount = recordTotalCount/recordCountPerPage;
		}
		
		if(currentPage<1) {
			currentPage=1;
		}
		else if(currentPage>pageTotalCount)
		{
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage -1;
		
		if(endNavi>pageTotalCount)
		{
			endNavi=pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi==1)
		{
			needPrev = false;
		}
		if(endNavi==pageTotalCount)
		{
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev)
		{
			System.out.println("//"+startNavi);
			sb.append("<a href='/notice?currentPage="+(startNavi-1)+"'> < </a>");
		}
		
		for(int i=startNavi;i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a href='/notice?currentPage="+i+"'><B>"+i+"</B></a>");
			}
			else
			{
				sb.append("<a href='/notice?currentPage="+i+"'>"+i+"</a>");
			}
		}
		
		if(needNext)
		{
			sb.append("<a href='/notice?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String searchPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int recordTotalCount = 0;
		String query = "select count(*) as totalCount from notice where subject like ?";
		try {
			System.out.println("searchPage");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, '%'+searchPage+'%');
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				recordTotalCount = rset.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		int pageTotalCount = 0;
		if(recordTotalCount%recordCountPerPage != 0)
		{
			pageTotalCount = recordTotalCount/recordCountPerPage +1;
		}
		else
		{
			pageTotalCount = recordTotalCount/recordCountPerPage;
		}
		
		if(currentPage<1) {
			currentPage=1;
		}
		else if(currentPage>pageTotalCount)
		{
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage -1;
		
		if(endNavi>pageTotalCount)
		{
			endNavi=pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi==1)
		{
			needPrev = false;
		}
		if(endNavi==pageTotalCount)
		{
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev)
		{
			System.out.println("//"+startNavi);
			sb.append("<a href='/notice?currentPage="+(startNavi-1)+"&searchPage=\"+searchPage+\"'> < </a>");
		}
		
		for(int i=startNavi;i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a href='/notice?currentPage="+i+"&searchPage=\"+searchPage+\"'><B>"+i+"</B></a>");
			}
			else
			{
				sb.append("<a href='/notice?currentPage="+i+"&searchPage="+searchPage+"'>"+i+"</a>");
			}
		}
		
		if(needNext)
		{
			sb.append("<a href='/notice?currentPage="+(endNavi+1)+"&searchPage=\"+searchPage+\"'> > </a>");
		}
		
		return sb.toString();
	}

	public Notice noticeSelect(Connection conn, int noticeNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String query ="select* from notice where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return n;
	}

	public int updateNotice(Connection conn, Notice n) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set subject = ? ,contents = ? where noticeno =? and userid = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getSubject());
			pstmt.setString(2, n.getContents());
			pstmt.setInt(3, n.getNoticeNo());
			pstmt.setString(4, n.getUserId());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	public int noticeWrite(Connection conn, Notice n) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO notice VALUES(SEQ_NOTICE.NEXTVAL,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getSubject());
			pstmt.setString(2, n.getContents());
			pstmt.setString(3, n.getUserId());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	public int noticeDelete(Connection conn, int noticeno) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete notice where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeno);
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	public ArrayList<NoticeComment> noticeComment(Connection conn, int noticeNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select* from noticecomment where noticeno=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset=pstmt.executeQuery();
			while(rset.next())
			{
				NoticeComment nc = new NoticeComment();
				nc.setCommentNo(rset.getInt("commentno"));
				nc.setNoticeNo(rset.getInt("noticeno"));
				nc.setContent(rset.getString("content"));
				nc.setUserid(rset.getString("userid"));
				nc.setRegDate(rset.getDate("regdate"));
				list.add(nc);
			}
			System.out.println("pstmt"+pstmt);
			System.out.println("rset"+rset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int updateComment(Connection conn, NoticeComment nc) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into noticecomment values(SEQ_noticecomment.NEXTVAL,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNoticeNo());
			pstmt.setString(2, nc.getContent());
			pstmt.setString(3, nc.getUserid());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	public int updateComment(Connection conn, NoticeComment nc, int commentno) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update noticecomment set content=? where commentno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getContent());
			pstmt.setInt(2, commentno);
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	public int deleteComment(Connection conn, int commentno) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete noticecomment where commentno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentno);
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result =1;
				conn.commit();
			}
			else
			{
				result = 0;
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("result :" +result);
		return result;
	}

	

}
