package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class NoticeUpdateReadyServlet
 */
@WebServlet("/noticeUpdateReady")
public class NoticeUpdateReadyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateReadyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		HttpSession session = request.getSession(false);
		member m = (member)session.getAttribute("login");
		Notice n = new NoticeService().noticeSelect(noticeNo);	//	공지사항
		
		
		if(n != null && m.getUserId().equals("admin"))
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeModify.jsp");
			request.setAttribute("notice", n);
			view.forward(request, response);
		}
		else
		{
			response.sendRedirect("/views/notice/Error.jsp");
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
