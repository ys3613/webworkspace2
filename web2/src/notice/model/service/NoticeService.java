package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.memberDao;
import member.model.vo.member;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.PageData;

public class NoticeService {

	public PageData noticeAll(int currentPage) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		// Service에서는 DAO에 2가지 요철을 진행 해야 함
		// 1. 현재 페이지 리스트
		// 2. 현재 중심으로 만들어지는 navi 리스트
		conn = JDBCTemplate.connection(conn);
		ArrayList<Notice> list =new NoticeDao().getCurrentPage(conn,currentPage, recordCountPerPage);
		String pageNavi = new NoticeDao().getPageNavi(conn,currentPage,recordCountPerPage, naviCountPerPage);
		PageData pd = null;
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
		pd.setNoticeList(list);
		pd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		return pd;
		
		
//		conn = JDBCTemplate.connection(conn);
//		ArrayList<Notice> list = new NoticeDao().noticeAll(conn);
//		JDBCTemplate.close(conn);
//		
//		return list;
	}

	public PageData noticeAll(int currentPage, String searchPage) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		// Service에서는 DAO에 2가지 요철을 진행 해야 함
		// 1. 현재 페이지 리스트
		// 2. 현재 중심으로 만들어지는 navi 리스트
		conn = JDBCTemplate.connection(conn);
		ArrayList<Notice> list =new NoticeDao().getCurrentPage(conn,currentPage, recordCountPerPage, searchPage);
		String pageNavi = new NoticeDao().getPageNavi(conn,currentPage,recordCountPerPage, naviCountPerPage, searchPage);
		PageData pd = null;
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
		pd.setNoticeList(list);
		pd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		return pd;
	}

	public Notice noticeSelect(int noticeNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		
		Notice n = new NoticeDao().noticeSelect(conn, noticeNo);
		
		return n;
	}

	public int updateNotice(Notice n) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		System.out.println(n.getSubject());
		int result = new NoticeDao().updateNotice(conn, n);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int noticeWrite(Notice n) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new NoticeDao().noticeWrite(conn, n);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int noticeDelete(int noticeno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new NoticeDao().noticeDelete(conn, noticeno);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<NoticeComment> noticeComment(int noticeNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn =JDBCTemplate.connection(conn);
		System.out.println("conn"+conn);
		ArrayList<NoticeComment> list = new NoticeDao().noticeComment(conn, noticeNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public int updateComment(NoticeComment nc) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new NoticeDao().updateComment(conn, nc);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateComment(NoticeComment nc, int commentno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new NoticeDao().updateComment(conn, nc,commentno);
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteComment(int commentno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new NoticeDao().deleteComment(conn, commentno);
		JDBCTemplate.close(conn);
		
		return result;
	}

}
