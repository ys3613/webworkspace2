<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int value = 999; %>
<%int value2 = 888; %>
<c:set var="a" value="<%=value %>"></c:set>
<c:set var="b" value="<%=value2 %>"></c:set>
<c:set var="num" value="100"></c:set>
<c:set var="num" value="200" scope="request"></c:set>
<c:set var="num" value="300" scope="session"></c:set>
값 출력 : ${pageScope.a}
${a} + ${b} = ${a+b}
<br><br>
<c:remove var="num"/>
${pageScope.num}<br>
${requestScope.num}<br>
${sessionScope.num}<br>
<c:out value="${data}" default="없음"></c:out>

<c:out value="<br>태그는 줄행입니다." escapeXml="false"></c:out>
<c:out value="<br>태그는 줄행입니다." escapeXml="true"></c:out>
<br>
<c:if test="${a>b}" var="result">
	value가 더 큽니다.
</c:if>
<c:set var="choice" value="2"></c:set>
<c:choose>
<c:when test="${choice==1}">1을 선택<br></c:when>
<c:when test="${choice==2}">2을 선택<br></c:when>
<c:otherwise>그외 선택<br></c:otherwise>
</c:choose>

<c:forEach begin="1" end="10" var="i">
${i}<br>
</c:forEach>

<c:forEach items="${list}" varStatus="m">
	이름 : ${m.name}
	나이 : ${m.age}
	주소 : ${m.addr}
</c:forEach>
</body>
</html>