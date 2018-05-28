<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<% int result = Integer.parseInt(request.getParameter("result")); 
	switch(result)
	{
	case 1:%> <h1> 회원탈퇴 성공</h1><br><a href="/index.jsp">메인 메뉴</a><%break;
	case 2:%> <h1> 회원탈퇴 실패</h1><br><a href="/views/member/loginSucces.jsp">이전 화면</a><%break;
	case 3:%> <h1> 비밀번호 불일치</h1><br><a href="/views/member/loginSucces.jsp">이전 화면</a><% break;
	}%>
	</center>
</body>
</html>