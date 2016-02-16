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
	Object obj1 = session.getAttribute("s_name");
	String mySesssionName = (String)obj1;
	out.println(mySesssionName + "<br>");

	Object obj2 = session.getAttribute("s_num");
	Integer myNum = (Integer)obj2;
	out.println(myNum + "<br>");
	
	out.println("******************************<br>");
	
	String s_name;
	String s_val;
	Enumeration enumeration = session.getAttributeNames();
	while(enumeration.hasMoreElements()) {
		s_name = enumeration.nextElement().toString();
		s_val = session.getAttribute(s_name).toString();
		out.println("s_name " + s_name + "<br>");
		out.println("s_val " + s_val + "<br>");
	}
	
	out.println("******************************<br>");

	String session_id = session.getId();
	out.println("session_id " + session_id + "<br>");
	int session_inter = session.getMaxInactiveInterval();
	out.println("session_inter "+ session_inter + "<br>");
	
	out.println("*********************************** <br>");
	
	session.removeAttribute("s_name");
	Enumeration enumeration_1 = session.getAttributeNames();
	while(enumeration_1.hasMoreElements()) {
		s_name = enumeration_1.nextElement().toString();
		s_val = session.getAttribute(s_name).toString();
		out.println("s_name " + s_name + "<br>");
		out.println("s_val " + s_val + "<br>");
	}
	
	out.println("*********************************** <br>");
	out.println(request.isRequestedSessionIdValid()? "session valid" : "session invalid");
	
%>
</body>
</html>