<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="file.model.vo.*" %>
    <% ArrayList<DataFile2> list = (ArrayList<DataFile2>)request.getAttribute("list");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<body>
<table class="table table-bordered" style="width:800px;">
<thead>
<tr>
<th>파일이름</th><th>파일사이즈</th><th>업로더</th><th>업로드 시간</th><th>다운로드</th><th>삭제</th>
</tr>
<tbody>
<%for(DataFile2 df : list){ %>
<tr>
<td><%= df.getBeforeFileName() %></td><td><%= df.getFileSize() %></td><td><%= df.getFileUser() %></td><td><%= df.getUploadTime() %></td>
<td><form action="/fileDown" method="post">
<input type="hidden" name="beforefileName" value="<%= df.getBeforeFileName() %>">
<input type="hidden" name="uploadTime" value="<%= df.getUploadTime() %>">
<input class="btn btn-default" type="submit" value="다운로드"></form></td>
<td><form action="/fileRemove" method="post"><input class="btn btn-default" type="submit" value="파일삭제"></form></td>
</tr>
<%} %>
</tbody>
</table>
    <script src="/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>