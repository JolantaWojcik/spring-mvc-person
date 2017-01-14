<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	function orderPersonBy(criteria){
		document.getElementById('sortByCriteria').value = criteria;
		document.getElementById('orderByPersonForm').submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person view</title>
</head>
<body>
	<table>
		<tr bgcolor="silver">
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderPersonBy('name')">Imie</td>
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderPersonBy('surname')">Nazwisko</td>
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderPersonBy('age')">Wiek</td>
		</tr>
		<c:forEach var="p" items="${persons}">
			<tr>
				<td>${p.name}</td>
				<td>${p.surname}</td>
				<td>${p.age}</td>
			</tr>
		</c:forEach>
	</table>

	<form action="/spring-mvc-example/person/" method="post">
		<table>
			<tr>
				<td>Wprowadz imie</td>
				<td><input type="text" name="name" /></td>
			</tr>

			<tr>
				<td>Wprowadz nazwisko</td>
				<td><input type="text" name="surname" /></td>
			</tr>

			<tr>
				<td>Wprowadz wiek</td>
				<td><input type="text" name="age" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Dodaj" /></td>
			</tr>
		</table>
	</form>
	
	<form id="orderByPersonForm" action="/spring-mvc-example/person/" method="get">
		<input type="hidden" id="sortByCriteria" name="sortByCriteria" value="id"/>
	</form>
</body>
</html>