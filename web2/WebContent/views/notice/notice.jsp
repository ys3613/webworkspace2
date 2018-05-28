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
	<td><%=n.getNoticeNo() %></td><td><%=n.getSubject() %></td><td><%=n.getContents() %></td>
	</tr>
<%}%>
</table>
<%= pageNavi %>
</center>
</body>
</html>