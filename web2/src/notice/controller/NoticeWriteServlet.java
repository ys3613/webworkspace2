package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/writeNotice")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		member m = (member) session.getAttribute("login");
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		Notice n = new Notice();

		n.setUserId(m.getUserId());
		n.setSubject(subject);
		n.setContents(contents);
		if (m.getUserId().equals("admin")) {
			int result = new NoticeService().noticeWrite(n);

			if (result == 1) {
				response.sendRedirect("/notice");
			} else {
				response.sendRedirect("/views/notice/Error.jsp");
			}
		} else {
			response.sendRedirect("/views/notice/Error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
