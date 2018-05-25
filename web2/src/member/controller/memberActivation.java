package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.memberService;
import member.model.vo.member;

/**
 * Servlet implementation class memberActivation
 */
@WebServlet("/memberActivation")
public class memberActivation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberActivation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int result = 0;
		request.setCharacterEncoding("UTF-8");
		String activation = request.getParameter("activation");
		System.out.println(activation);
		if(activation.toUpperCase().equals("Y"))
		{
			activation = "N";
		}
		else if(activation.toUpperCase().equals("N"))
		{
			activation = "Y";
		}
		String id = request.getParameter("id");
		
		
		result = new memberService().updateActivation(id, activation);
		if(result==1)
		{
			ArrayList<member> list = null;
			request.setCharacterEncoding("UTF-8");
			list = new memberService().selectAll();
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/member/allMember.jsp");
//			request.setAttribute("allmember", list);
//			dispatcher.forward(request, response);
			response.sendRedirect("/allmember");
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
