<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>

<%!
	String driver ="oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String u_id = "scott";
	String u_pw = "tiger";

	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String name,
			id,
			pw,
			phone1,
			phone2,
			phone3,
			gender;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%
		id = (String)session.getAttribute("id");
		
		String query = "select * from member where id = '"+id+"'";
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url,u_id,u_pw);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			name = rs.getString("name");
			pw = rs.getString("pw");
			phone1 = rs.getString("phone1");
			phone2 = rs.getString("phone2");
			phone3 = rs.getString("phone3");
			gender = rs.getString("gender");
		}
	%>

	<form action="ModifyOk" method="post">
		�̸� : <input type="text" name="name" size="10" value=<%=name %>><br />
		���̵� : <input type="text" name="id" size="10" value=<%=id %> readonly><br />
		��й�ȣ : <input type="text" name="pw" size="10"><br />
		��ȭ��ȣ : <select name="phone1">
			<option value="010">010</option>
			<option value="016">016</option>
			<option value="017">017</option>
			<option value="018">018</option>
			<option value="019">019</option>
			<option value="011">011</option>
		</select> - 
		<input type="text" name="phone2" size="5" value=<%=phone2 %>> - <input type="text" name="phone3" size="5" value=<%=phone3 %>> <br />
		<%
			if(gender.equals("man")) {
		%>
		�������� : <input type="radio" name="gender" value="man" checked="checked">�� &nbsp;<input type="radio" name="gender" value="woman">�� <br />
		<%
			} else {
		%>
		�������� : <input type="radio" name="gender" value="man" >�� &nbsp;<input type="radio" name="gender" value="woman" checked="checked">�� <br />
		<%
			}
		%>
		<input type="submit" value="��������"> <input type="reset" value="���">
	</form>

</body>
</html>