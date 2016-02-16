<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	out.println(request.getServerName()+"<br>");
	out.println(request.getServerPort()+"<br>");
	out.println(request.getMethod()+"<br>");
	out.println(request.getProtocol()+"<br>");
	out.println("URL = " + request.getRequestURL()+"<br>");
	out.println("URI = " + request.getRequestURI()+"<br>");
%>

</body>
</html>