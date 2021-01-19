<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="springmvc/testMap">test Map</a>
	<br><br>
	<a href="springmvc/testModelAndView">test ModelAndView</a>
	<br><br>
	<a href="springmvc/testServletAPI" >test ServletApi as parameters</a>
	<br><br>
	<form action="springmvc/testPojo" method="post">
		username:<input type="text" name="username"/>
		<br>
		password:<input type="password" name="password"/>
		<br>
		age: <input type="text" name="age"/>
		<br>
		Gender:<input type="radio" name="gender" value="Female"/>Female <input type="radio" name="gender" value="Male"/>Male
		<br>
		City:<input type="text" name="address.city"/>
		<br>
		Province:<select name="address.province" >
					<option value="ON"> Ontario</option>
					<option value="QU"> Quebec</option>
				</select>
		<br>
		<input type="submit" value="submit">
	</form>
	<br><br>
	<a href="springmvc/testCookieValue">test CookieValue</a>
	<br><br>
	<a href="springmvc/testRequestHeader">test RequestHeader</a>
	<br><br>
	<a href="springmvc/testRequestParam?username=root&password=admin">test RequestParam</a>
	<br><br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="Test Rest PUT"/>
	</form>
	<br>
	<br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="Test Rest DELETE"/>
	</form>
	<br>
	<br>
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="Test Rest POST"/>
	</form>
	<br>
	<br>
	<a href="springmvc/testRest/1" >Test Rest Get</a> <br> <br>
	<form action="springmvc/testMethod" method="POST">
		<input type="submit" value="springmvc/testMethod"/>
	</form>
	<a href="helloword"> hello world</a><br><br>
	<a href="springmvc/testRequestMapping"> springmvc/testRequestMapping</a><br><br>
	<a href="springmvc/testMethod"> springmvc/testMethod </a><br><br>
	<a href="springmvc/testParamsAndHeaders?username=admin&age=11"> springmvc/testParamsAndHeaders </a><br><br>
	<a href="springmvc/testPathVariable/1&admin">springmvc/testPathVariable</a><br><br>
	
</body>
</html>