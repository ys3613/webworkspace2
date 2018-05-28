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
 * Servlet implementation class updateInfoServlet
 */
@WebServlet("/updateinfo")
public class updateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		member m = new member();
		request.setCharacterEncoding("UTF-8");
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		m.setName(request.getParameter("userName"));
		m.setAge(Integer.parseInt(request.getParameter("userAge")));
		m.setEmail(request.getParameter("email"));
		m.setPhone(request.getParameter("phone"));
		m.setGender(request.getParameter("gender"));
		m.setHobby(request.getParameter("hobby"));
		m.setAddr(request.getParameter(""));
		m.setActivation("Y");
		
		int result = new memberService().updateInfo(m);
		if(result==1)
		{
			response.sendRedirect("/views/member/loginSucces.jsp");
		}
		else
		{
			response.sendRedirect("/views/member/loginFail.jsp");
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
