package notice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import oracle.sql.DATE;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/noticeUpdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		Notice n = new Notice();
		n.setNoticeNo(Integer.parseInt(request.getParameter("noticeNo")));
		n.setUserId(request.getParameter("userid"));
		n.setSubject(request.getParameter("subject"));
		n.setContents(request.getParameter("contents"));
		
		System.out.println("서브젝트 : " + request.getParameter("subject"));
		System.out.println("콘텐 : " + request.getParameter("contents"));
		

			int result = new NoticeService().updateNotice(n);
			if(result ==1)
			{
				response.sendRedirect("/notice");
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
