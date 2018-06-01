package member.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;

/**
 * Servlet implementation class EL_Test1Servlet
 */
@WebServlet("/el_test3")
public class EL_Test3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EL_Test3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		HashMap<String, Member> map = new HashMap<String,Member>();
//		map.put("member1",new Member("홍길동",20,"경기도"));
//		map.put("member2",new Member("김말똥",30,"서울시"));
		
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("홍길동",20,"경기도"));
		list.add(new Member("김말똥",30,"서울시"));
	
		RequestDispatcher view = request.getRequestDispatcher("/views/el/el_test3.jsp");
		request.setAttribute("members", list);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
