package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
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
		String currentPage = request.getParameter("currentPage");
		String searchPage = request.getParameter("searchPage");
		System.out.println(searchPage);
		PageData pd = null;
		if(searchPage == null)
		{
			System.out.println("null이다");
			if(request.getParameter("currentPage")==null) 
				currentPage="1";
			pd = new NoticeService().noticeAll(Integer.parseInt(currentPage));
			
		}
		else
		{
			System.out.println("null이 아니다");
			if(request.getParameter("currentPage")==null) 
				currentPage="1";
			pd = new NoticeService().noticeAll(Integer.parseInt(currentPage),searchPage);
		}
		
		
		if(pd!=null)
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/notice.jsp");
			request.setAttribute("pageData", pd);
			view.forward(request, response);
		}
		else
		{
			response.sendRedirect("/views/notice/Error.html");
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
