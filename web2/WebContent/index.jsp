<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("login")==null){%>
<h1>메인 페이지</h1>
<fieldset style="width:300px; height:100px;">
<legend>로그인</legend>
<form action="Login" method="post" style="display:inline;">
ID : <Input type="text" placeholder="Id를 입력하세요" name="userId"><br>
PW : <Input type="text" placeholder="Pw를 입력하세요" name="userPw"><br>
<input type="submit" value="로그인">
</form>
<a href="/views/member/memberJoin.html">회원가입</a>
<a href="">ID 찾기</a>
<a href="">PW 찾기</a>
</fieldset>
<%}else{%>
<% response.sendRedirect("/views/member/loginSucces.jsp"); %>
<%}%>
</body>
</html>