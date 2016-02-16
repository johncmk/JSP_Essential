<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <% if(session.getAttribute("ValidMem") != null) {%>
    	<<jsp:forward page="main.jsp"></jsp:forward>
    <% } %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form action="loginOk.jsp" method="post">
		ID: <input type="text" name="id" value="<% if(session.getAttribute("id") != null) out.println(session.getAttribute("id")); %>"><br>
		PASS: <input type="password" name="pw"><br>
		<input type="submit" value="login"> <br>
		<input type="button" value="register" onclick="javascript:window.location='join.jsp'">
	</form>
</body>
</html>