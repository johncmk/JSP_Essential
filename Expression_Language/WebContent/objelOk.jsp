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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	%>

	ID: <%= id %> <br>
	PW: <%= pw %> <br>

	<hr/>

	ID: ${param.id } <br>
	PW: ${ param.pw} <br>
	ID_deggar[]: ${param["id"] } <br>
	PW_deggar[]: ${param["pw"] } <br>

	<hr>

	applcationScope: ${ applicationScope.applicaion_name } <br>
	sessionScope: ${sessionScope.session_name } <br>
	pageScope: ${ pageScope.page_name} <br>
	requestScope: ${ requestScope.requst_name} <br>

	<hr>
	
	context default parameter <br>
	${initParam.con_name} <br>
	${initParam.con_id} <br>
	${initParam.con_pw} <br>

</body>
</html>