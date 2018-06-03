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
	window.onload = function(){
		if(opener!=null)
			{
			opener.location.reload();
			window.close();
			}
	}
	function myInfo() {
		document.getElementById("myInfo").style="display:inline";
	}
	function deletemember() {
		document.getElementById("deletemember").style="display:inline";
	}
</script>
<center>
<h1>로그인 성공</h1><br>
<!--<% member m = (member)session.getAttribute("login"); %>-->
<!-- <a href="/mypage">마이페이지</a><br> -->
<label onclick="myInfo();" id="infoBtn">마이페이지</label>
<form action="/mypage" method="post" style="display:none;" id="myInfo">
<label style="color:red;">비밀번호 입력 :</label>
<input type="password" name="userPwd">
<input type="submit" value="확인">
</form>
<a href="/logout">로그아웃</a><br>
<label onclick="deletemember();" id="infoBtn">회원탈퇴</label>
<form action="/deletemember" method="post" style="display:none;" id="deletemember">
<label style="color:red;">비밀번호 입력 :</label>
<input type="password" name="userPwd">
<input type="submit" value="확인">
</form>
<br>
<a href="/views/file/upload.html">업로드</a>
<a href="/filelist">다운로드</a>
<br>
<a href="/views/file/upload2.html">업로드2</a>
<a href="/filelist2">다운로드2</a>
<br>
    <c:if test="${login.getUserId eq 'admin'}">
          <a href="/allmember">전체회원조회</a>
    </c:if>
<!--
<%if(m.getUserId().equals("admin")){%>
<a href="/allmember">전체회원조회</a>
<%}%>
-->
</center>
<br>
<a href="/notice">공지사항</a>
</body>
</html>