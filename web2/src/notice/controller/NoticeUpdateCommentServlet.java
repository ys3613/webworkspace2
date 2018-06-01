package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class NoticeUpdateCommentServlet
 */
@WebServlet("/noticeUpdateComment")
public class NoticeUpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		NoticeComment nc = new NoticeComment();
		nc.setUserid(request.getParameter("userid"));
		nc.setNoticeNo(Integer.parseInt(request.getParameter("noticeno")));
		nc.setContent(request.getParameter("comment"));
		if(request.getParameter("commentno") == null)
		{
			int result = new NoticeService().updateComment(nc);
			if(result==1)
			{
				response.sendRedirect("/noticeSelect?noticeNo="+nc.getNoticeNo());
//				RequestDispatcher view = request.getRequestDispatcher("/noticeSelect?noticeNo="+nc.getNoticeNo());
			}
			else
			{
				response.sendRedirect("/views/notice/Error.jsp");
			}
		}
		else
		{
			int commentno  = Integer.parseInt(request.getParameter("commentno"));
			System.out.println("commentno : "+commentno);
			int result = new NoticeService().updateComment(nc,commentno);
			if(result==1)
			{
				response.sendRedirect("/noticeSelect?noticeNo="+nc.getNoticeNo());
//				RequestDispatcher view = request.getRequestDispatcher("/noticeSelect?noticeNo="+nc.getNoticeNo());
			}
			else
			{
				response.sendRedirect("/views/notice/Error.jsp");
			}
		}
		
				
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
