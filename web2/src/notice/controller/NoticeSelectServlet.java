package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class NoticeSelectServlet
 */
@WebServlet("/noticeSelect")
public class NoticeSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectServlet() {
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
		
		Notice n = new NoticeService().noticeSelect(noticeNo);
		ArrayList<NoticeComment> list = new NoticeService().noticeComment(noticeNo);	// 댓글
		if(n != null)
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeSelect.jsp");
			request.setAttribute("notice", n);
			request.setAttribute("comment", list);
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
