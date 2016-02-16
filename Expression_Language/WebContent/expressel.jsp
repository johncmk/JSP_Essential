<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
   <jsp:useBean id="member" class="com.javalec.ex.MemberInfo" scope="page"></jsp:useBean>
   <jsp:setProperty property="name" name="member" value="John Smith"/>
   <jsp:setProperty property="id" name="member" value="John"/>
   <jsp:setProperty property="pw" name="member" value="1010"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	Name: <jsp:getProperty property="name" name="member"/><br>
	ID: <jsp:getProperty property="id" name="member"/><br>
	Password: <jsp:getProperty property="pw" name="member"/><br>

	<hr />
	
	Name: ${ member.name }<br>
	ID: ${ member.id }<br>
	Password: ${ member.pw }<br>

</body>
</html>