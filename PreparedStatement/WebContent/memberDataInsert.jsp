<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%!
	Connection conn;
	PreparedStatement p_stmt;
	ResultSet rs;
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
%>
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
	
	try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,uid,upw);
		int n;
		String query = "INSERT INTO memberforpre (id, pw, name, phone) values (?, ?, ?, ?)";
		p_stmt = conn.prepareStatement(query);
		
		p_stmt.setString(1,"john_id");
		p_stmt.setString(2,"1010");
		p_stmt.setString(3,"john");
		p_stmt.setString(4,"123-345-6789");
		n = p_stmt.executeUpdate();
		
		p_stmt.setString(1,"tom_id");
		p_stmt.setString(2,"1010");
		p_stmt.setString(3,"tom");
		p_stmt.setString(4,"124-445-4789");
		n = p_stmt.executeUpdate();
		
		p_stmt.setString(1,"jae_id");
		p_stmt.setString(2,"1010");
		p_stmt.setString(3,"jae");
		p_stmt.setString(4,"121-145-1789");
		n = p_stmt.executeUpdate();
		
		p_stmt.setString(1,"dom_id");
		p_stmt.setString(2,"1010");
		p_stmt.setString(3,"dom");
		p_stmt.setString(4,"126-645-6689");
		n = p_stmt.executeUpdate();
		
		out.println( (n == 1)? "Insert Successfully" : "Insert Failed");
		
	} catch ( Exception e ) {
		e.printStackTrace();
	} finally {
		try {
			if(rs != null) 
				rs.close();
			if(p_stmt != null)
				p_stmt.close();
			if(conn != null)
				conn.close();
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	
%>

</body>
</html>