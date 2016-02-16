<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%!
	//These are treated as global type
	int i = 10;
	String str = "ABCDE";
%>

<%!
	public int sum(int a, int b) {
		return a+b;
}
%>

<%
	/*
	out.println("i = " + i + "<br />");
	out.println("str = " + str + "<br />");
	out.println("sum = " + sum(1,5) + "<br />");
	*/
%>

<%= i %> <br>
<%= str %> <br>
<%= sum(1,5) %> <br>

<% int[] arr = {1,2,3}; 
	out.println(Arrays.toString(arr));
%>

</body>
</html>