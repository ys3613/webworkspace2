package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import member.model.service.memberService;
import member.model.vo.member;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deletemember")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		member m = (member)session.getAttribute("login");
		String userPwd = request.getParameter("userPwd");
		if(userPwd.equals(m.getUserPwd()))
		{
			int result = new memberService().deleteMember(m.getUserId(), m.getUserPwd());
			if(result==1)
			{
				session.invalidate();
				response.sendRedirect("/views/member/deleteResult.jsp?result="+result);
			}
			else if(result==0)
			{
				response.sendRedirect("/views/member/deleteResult.jsp?result="+result);
			}
		}
		else
		{
			int result = 2;
			response.sendRedirect("/views/member/deleteResult.jsp?result="+result);
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
