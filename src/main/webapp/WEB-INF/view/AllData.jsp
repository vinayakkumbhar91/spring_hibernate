<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>${msg}</h4>
	<p><a href=convertPdf>Data into PDF</a> | <a href=convertExcel>Data into EXCEL</a> </p>
	<table border="1">
		<tr>
			<td>
				<form action="<s:url value="/search"/>" method="get">
					<input type="text" name="searchname" value="${param.searchname}">
					<input type="submit" value="find">
				</form>
			</td>
			<td>
				<form action="<s:url value="/deletemul"/>" method="get">
					<input type="submit" value="deleteall">
			</td>
		</tr>
		<tr>
			<th>select</th>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>File</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<c:forEach var="emp" items="${empdata}">
			<tr>
				<c:choose>
					<c:when test="${emp.id eq '0'}">
						<td><input type="checkbox" name="ids" value="0"></td>
					</c:when>
					<c:otherwise>
						<td><input type="checkbox" name="ids" value="${emp.id}"></td>
					</c:otherwise>
				</c:choose>
				<td>${emp.id}</td>
				<td>${emp.name}</td>
				<td>${emp.email}</td>
				<td><a href="downloadFile?id=${emp.id}">download</a></td>
				<td><a href="editEmp?id=${emp.id}">edit</a></td>
				<td><a href="deleteEmp?id=${emp.id}">delete</a></td>
			</tr>
		</c:forEach>
		</form>
	</table>
	<p>
		<c:forEach var="page" begin="1" end="${pages}">
			<a href="getEmpData?pageno=${page}">${page}</a>
		</c:forEach>
	</p>

	<h2>${count}</h2>
	<br>
	<a href="getformPage">Home</a>
</body>
</html>