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
	<h3>Registration Form</h3>
	<table>
		<form:form action="savedata" method="post" modelAttribute="emp"
			enctype="multipart/form-data">
			<tr>
				<td>name</td>
				<td><form:input path="name" placeholder="enter the name"
						title="enter the name" /></td>
			</tr>
			<tr>
				<td>email</td>
				<td><form:input path="email" placeholder="enter the email"
						title="enter the email" /></td>
			</tr>
			<tr>
				<td>password</td>
				<td><form:password path="password"
						placeholder="enter the password" title="enter the password" /></td>
			</tr>
			<tr>
				<td>Upload File</td>
				<td><input type="file" name="file"
					title="select the attachment "></td>
			</tr>
			<tr>
				<td><input type="submit" value="save"></td>
			</tr>

		</form:form>

	</table>
	<br>
	<h4>${msg}</h4>
	<br>
	<br>
	<a href="getEmpData?pageno=1">getAllData </a>
</body>
</html>