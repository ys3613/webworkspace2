<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h1> 패스워드를 변경하신지 90일이 지났습니다.</h1>
<h1> 바꾸십쇼</h1>

<form action="/updateinfo">
비밀번호 : <input type="password" name="userPwd"><br>
비밀번호 확인 : <input type="password" name="userPwd_re"><br>
<input type="submit" value="변경">
</form>
</center>
</body>
</html>