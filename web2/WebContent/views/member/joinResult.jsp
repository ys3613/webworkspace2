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
<%if(request.getParameter("result").equals("1"))
	{%>
	<H1>회원가입 성공</H1>
	<%}
	else if(request.getParameter("result").equals("0"))
	{%>
	<H1>회원가입 실패</H1>
	<%}%>
	<br><br>
	<a href="/index.jsp">초기 화면으로</a>
</center>
</body>
</html>