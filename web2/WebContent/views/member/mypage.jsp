<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="member.model.vo.*" %>
    <% request.setCharacterEncoding("UTF-8");
    	member m = (member)request.getAttribute("mypageInfo");
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<fieldset>
<legend>회원 가입 정보</legend>
<form action="/updateinfo" method="post">
	ID : <input type="text" name="userId" value=<%= m.getUserId() %>><br>
	PW : <input type="password" name="userPwd" value=<%= m.getUserPwd() %>><br>
	PW_re : <input type="password" name="userPwd_re" value=<%= m.getUserPwd() %>><br>
	Name : <input type="text" name="userName" value=<%= m.getName() %>><br>
	Age : <input type="text" name="userAge" value=<%= m.getAge() %>><br>
	Email : <input type="email" name="email" value=<%= m.getEmail() %>><br>
	Phone : <input type="text" name="phone" value=<%= m.getPhone() %>><br>
	Addr : <input type="text" name="userAddr" value=<%= m.getAddr() %>><br>
	Gender : <input type="radio" name="gender" value="M" <%if(m.getGender().equals("M")){%> checked="checked"<%} %>>남
				<input type="radio" name="gender" value="F" <%if(m.getGender().equals("F")){%> checked="checked"<%} %>>여<br>
	Hobby : <input type="text" name="hobby" value=<%= m.getHobby() %>><br>
	<input type="submit" value="수정">
	<button type="button" onclick="back();">돌아가기</button>
</form>
</fieldset>
<script type="text/javascript">
function back() {
	history.go(-1);
}
</script>

</body>
</html>