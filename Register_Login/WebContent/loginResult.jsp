<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%!
	String name,
			id,
			pw;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	name = (String)session.getAttribute("name");
	id = (String)session.getAttribute("id");
	pw = (String)session.getAttribute("pw");
%>

MR.<%= name %> Welcome! <br>
<a href="modify.jsp">Edit User</a>

</body>
</html>