package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;

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

}
