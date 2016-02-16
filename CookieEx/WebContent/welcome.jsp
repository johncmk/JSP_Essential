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
	Cookie[] cookies = request.getCookies();

for(int i = 0; i < cookies.length; i++) {
	String id = cookies[i].getValue();
	if(id.equals("john")) {
		out.println("Welcome " + id);
	} 
}
%>

<a href="logout.jsp">Logout</a>

</body>
</html>