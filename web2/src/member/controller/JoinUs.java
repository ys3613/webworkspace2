package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.memberService;
import member.model.vo.member;

/**
 * Servlet implementation class JoinUs
 */
@WebServlet("/joinus")
public class JoinUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinUs() {
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
		member m = new member();
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		m.setName(request.getParameter("userName"));
		m.setAge(Integer.parseInt(request.getParameter("userAge")));
		m.setPhone(request.getParameter("phone"));
		m.setEmail(request.getParameter("email"));
		m.setAddr(request.getParameter("userAddr"));
		m.setGender(request.getParameter("gender"));
		m.setHobby(request.getParameter("hobby"));
		m.setActivation("Y");
		
		int result = new memberService().joinUs(m);
		if(result == 1)
		{
			response.sendRedirect("/views/member/joinResult.jsp?result="+result);
		}
		else if(result == 0)
		{
			response.sendRedirect("/views/member/joinResult.jsp?result="+result);
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
