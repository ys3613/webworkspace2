<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	#infoBtn{
		cursor:pointer;
		text-decoration: underline;
		color:blue;
	}
</style>
<body>
<script type="text/javascript">
	function myInfo() {
		document.getElementById("myInfo").style="display:inline";
	}
</script>
<center>
<h1>로그인 성공</h1><br>
<% member m = (member)session.getAttribute("login"); %>
<!-- <a href="/mypage">마이페이지</a><br> -->
<label onclick="myInfo();" id="infoBtn">마이페이지</label>
<form action="/mypage" method="post" style="display:none;" id="myInfo">
<label style="color:red;">비밀번호 입력 :</label>
<input type="password" name="userPwd">
<input type="submit" value="확인">
</form>
<a href="/logout">로그아웃</a><br>
<a href="">회원탈퇴</a><br>
<%if(m.getUserId().equals("admin")){%>
<a href="/allmember">전체회원조회</a>
<%}%>
</center>
</body>
</html>