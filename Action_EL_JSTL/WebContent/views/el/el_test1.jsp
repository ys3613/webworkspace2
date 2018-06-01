<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>el 사용</h1>
이름 : ${el_test1.jspmember.name}<br>
나이 : ${requestScope.member.age }<br> 
주소 : ${requestScope.member.addr }<br>
</body>
</html>