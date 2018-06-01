<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="notice.model.vo.*" import="java.util.*" %>
    <% PageData pd = (PageData)request.getAttribute("pageData");
    	ArrayList<Notice> list = pd.getNoticeList();
    	String pageNavi = pd.getPageNavi();%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<table border="1px solid black">
<tr>
	<th>No.</th><th>제목</th><th>내용</th>
	</tr>
<% for(Notice n : list){%>
	<tr>
	<td><%=n.getNoticeNo() %></td><td><a href="/noticeSelect?noticeNo=<%=n.getNoticeNo() %>"><%= n.getSubject() %></a></td><td><%=n.getContents() %></td>
	</tr>
<%}%>
</table>
<%= pageNavi %>
<br>
<form action="/notice" method="post">
<input type="text" name="searchPage">
<input type="submit" value="검색"> 
</form>
<br>
<form action="/views/notice/noticeWrite.jsp" style="display: inline;">
<input type="submit" value="글쓰기">
</form>
</center>
</body>
</html>