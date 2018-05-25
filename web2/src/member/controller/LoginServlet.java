package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import member.model.service.memberService;
import member.model.vo.member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		member m = null;
		// 1. 전송값에 한글이 있을 경우를 처리할 수 있도록 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 2. View엣 전송한 데이터를 받아 변수에 저장
		String Id = request.getParameter("userId");
		String Pw = request.getParameter("userPw");
		// 3. 비즈니스 로직 처리(Controller -> Service -> Dao -> DB 처리후 리턴)
		m = new memberService().selectOne(Id,Pw);
		// 4. 처리 결과에 따라 성공/실패 페이지 리턴
		if(m != null)
		{
			if(m.getActivation().equals("N") && !m.getUserId().equals("admin"))
			{
				m = null;
				response.sendRedirect("/views/member/loginNoActivation.jsp");
			}
			else if(m.getActivation().equals("Y")|| m.getUserId().equals("admin"))
			{
			HttpSession session = request.getSession();
			session.setAttribute("login", m);
			response.sendRedirect("/views/member/loginSucces.jsp");
			}
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
