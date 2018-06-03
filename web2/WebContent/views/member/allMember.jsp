<%@page import="java.util.ArrayList"%>
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
<script type="text/javascript">
function prevPage() {
	location.href="/views/member/loginSucces.jsp";
}</script>
<!--   <c:set var="allmember" value="${allmember}" scope="session"></c:set>-->
<!--
<% request.setCharacterEncoding("UTF-8");
	ArrayList<member> list = (ArrayList<member>)request.getAttribute("allmember");%>
-->
	<table border="2px solid black">
	<tr>
	<th>ID</th><th>이름</th><th>나이</th><th>이메일</th><th>휴대폰</th><th>주소</th><th>성별</th><th>취미</th><th>가입날짜</th><th>활성여부</th>
	</tr>
<!--
	<%
	for(member m : list)
	{%>
-->
    <c:forEach items="${allmember}" varStatus="allmember">
	<tr>
<!--	<td><%= m.getUserId() %></td><td><%= m.getName() %></td><td><%= m.	getAge() %></td><td><%= m.getEmail() %></td>-->
<!--
        <td><%= m.getPhone() %></td><td><%= m.getAddr() %></td><td><%= m.getGender() %></td><td><%= m.getHobby() %></td>
	<td><%= m.getEnrolldate() %></td>
-->
        <td>${allmember.getuserId}</td><td>${allmember.getName}</td><td>${allmember.getAge}</td><td>${allmember.getEmail}</td>
        <td>${allmember.getPhone}</td><td>${allmember.getAddr}</td><td>${allmember.getGender}</td><td>${allmember.getHobby}</td>
	<td>${allmember.getEnrolldate}</td>
	<td>
	<form action="/memberActivation" method="post">
	<input type="hidden" value="<%= m.getActivation()%>" name="activation">
	<input type="hidden" value="<%= m.getUserId()%>" name="id">
	<input type="submit" value="<%= m.getActivation() %>">
	</form>
	</td>
	</tr>
        </c:forEach>
<!--	<%}%>-->
	</table>
	<br>
	<button onclick="prevPage();">이전 페이지로</button>
	
</body>
</html>