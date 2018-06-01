<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="notice.model.vo.*" import="member.model.vo.*"%>
    
    <% Notice n = (Notice)request.getAttribute("notice"); %>
    <% ArrayList<NoticeComment> list = (ArrayList<NoticeComment>)request.getAttribute("comment"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>s
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
글번호 : <%= n.getNoticeNo()%><br>
글쓴이 : <%= n.getUserId()%><br>
작성일 : <%= n.getRegDate()%><br>
글제목 : <%= n.getSubject()%><br>
<textarea rows="20" readonly cols="100" style="resize: none"><%= n.getContents() %></textarea><br>
<h1>댓글</h1>
<form action="/noticeUpdateComment">
<% if((member)session.getAttribute("login")==null) {%>
<textarea onclick="login()" rows="5" cols="100" name="comment" placeholder="로그인한 사용자만 댓글작성 가능합니다." style="resize:none" readonly="readonly"></textarea>
<%}else{ %>
<textarea rows="5" cols="100" name="comment" placeholder="댓글작성좀;" style="resize:none"></textarea>
<%} %>
<input type="hidden" name="userid" value=<%= n.getUserId() %>>
<input type="hidden" name="noticeno" value=<%= n.getNoticeNo() %>>
<input type="submit" value="댓글작성">
</form>
<script type="text/javascript">
	function login(){
		alert("로그인을 먼저 진행해주세요");
		window.open("/views/member/login_popup.html","black_","width=300px,height=200px");
	}
	function modifyComment(cn) {
		this.document.getElementById("modifyComment"+cn).style="display:inline";
		this.document.getElementById("modifyBtn"+cn).style="display:none";
		document.getElementById("deleteComment"+cn).style="display:inline";
	}
</script>
<%for(NoticeComment nc : list){ %>
	작성자 : <%= nc.getUserid() %> / 작성일 : <%= nc.getRegDate() %><br>
	<label onclick="modifyComment(<%=nc.getCommentNo() %>);" id="modifyBtn<%=nc.getCommentNo() %>"><%= nc.getContent() %></label>
	<form action="/noticeUpdateComment" method="post" style="display:none;" id="modifyComment<%=nc.getCommentNo() %>">
	<input type="text" name="comment" id="comment<%=nc.getCommentNo() %>">
	<input type="hidden" name="userid" value=<%= n.getUserId() %> id="userid<%=nc.getCommentNo() %>">
	<input type="hidden" name="noticeno" value=<%= n.getNoticeNo() %> >
	<input type="hidden" name="commentno" value=<%= nc.getCommentNo() %>>
	<input type="submit" value="수정">
	</form>
	<form action="/noticeDeleteComment" style="display: none;" id="deleteComment<%= nc.getCommentNo()%>">
	<input type="hidden" name="noticeno" value=<%= n.getNoticeNo() %> >
	<input type="hidden" name="commentno" value=<%= nc.getCommentNo() %>>
	<input type="submit" value="삭제">
</form>
	<br>
<%} %>
<br>
<script type="text/javascript">
	function back(){
		location.href="/notice";
	}
</script>
<button onclick="back()">목록</button>
<form action="noticeUpdateReady" style="display: inline;">
<input type="hidden" name="noticeNo" value=<%= n.getNoticeNo() %>>
<input type="submit" value="수정">
</form>
<form action="/noticeDelete" style="display: inline;">
<input type="hidden" value="<%= n.getNoticeNo() %>" name="noticeNo">
<input type="submit" value="삭제">
</form>
<form action="/views/notice/noticeWrite.jsp" style="display: inline;">
<input type="submit" value="글쓰기">
</form>
</body>
</html>