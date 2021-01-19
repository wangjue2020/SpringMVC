<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4> Sucess Page</h4>
	[ReqeustScope]  time: ${requestScope.time }
	<br>
	[ReqeustScope]  user:${requestScope.user}
	<br>
	[RequestScope] school:${requestScope.school }
	<br>
	[SessionScope] user:${sessionScope.user}
	<br>
	[SessionScope] school:${sessionScope.school }
	<br>
</body>
</html>