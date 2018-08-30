<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Edit Form</h3>
	<table>
		<form:form action="updateData" method="post" modelAttribute="emp">
			<tr>
				<td>Id</td>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<td>name</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>email</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr style="display: none">
				<td>password</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="save"></td>
			</tr>

		</form:form>

	</table>
</body>
</html>