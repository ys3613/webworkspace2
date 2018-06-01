<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="notice.model.vo.*" import="member.model.vo.*" %>
    <% member m = (member)session.getAttribute("login"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(m.getUserId().equals("admin")){ %>
<form action="/writeNotice" method="post">
글제목 : <input type="text" name="subject"><br>
<textarea rows="20" cols="100" style="resize: none" name="contents"></textarea><br>
<input type="submit" value="확인">
<%}else{ %>
<center><h1>관리자가 아닙니다.</h1></center>
<%} %>
</form>
</body>
</html>