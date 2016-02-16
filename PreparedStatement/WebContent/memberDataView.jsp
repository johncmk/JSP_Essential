<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.Statement" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%!
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	String query = "Select * from memberforpre";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,uid,upw);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			
			out.println("ID: "+id+", pw: "+pw+", name: "+name+", phone: "+phone+ "<br>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
%>

</body>
</html>