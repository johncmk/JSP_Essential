<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%!
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String driver ="oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "tiger";
	String query = "select * from member";
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
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				
				out.println("id = " + id);
				out.println("password = " + pw);
				out.println("name = " + name);
				out.println("phone = " + phone);
			}			
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					rs.close();
				if(conn != null)
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
			}
		}
	%>

</body>
</html>