<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="list" class="member.model.vo.Member" scope="request"></jsp:useBean>
<h1>정보출력</h1><br>
이름 : <jsp:getProperty property="name" name="list"/>
나이 : <jsp:getProperty property="age" name="list"/>
주소 : <jsp:getProperty property="addr" name="list"/>
</body>
</html>