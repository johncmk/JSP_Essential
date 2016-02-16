<%@ page import="java.util.Enumeration" %>
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
		Enumeration enumeration = session.getAttributeNames();
		while(enumeration.hasMoreElements()) {
			String s_name = enumeration.nextElement().toString();
			String s_val = (String)session.getAttribute(s_name);
			
			if(s_val.equals("john")) {
				out.println("Welcome " + s_val + "<br>");
			}
		}
	%>
	<a href="logout.jsp">Logout</a>

</body>
</html>