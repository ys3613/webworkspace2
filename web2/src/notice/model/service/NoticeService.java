package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.memberDao;
import member.model.vo.member;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
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

}
