<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="notice.model.vo.*" %>
    
    <% Notice n = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/noticeUpdate" style="display: inline;">
글번호 : <%= n.getNoticeNo()%><br>
글쓴이 : <%= n.getUserId()%><br>
작성일 : <%= n.getRegDate()%><br>
글제목 : <input type="text" value="<%= n.getSubject()%>" name="subject"><br>
<textarea rows="20" cols="100" style="resize: none" name="contents"><%= n.getContents() %></textarea><br>
<script type="text/javascript">
	function back(){
		location.href="/notice";
	}
</script>

<input type="hidden" name="noticeNo" value=<%= n.getNoticeNo() %>>
<input type="hidden" name="userid" value=<%= n.getUserId() %>>
<input type="submit" value="확인">
<button onclick="back()">취소</button>
</form>
</body>
</html>