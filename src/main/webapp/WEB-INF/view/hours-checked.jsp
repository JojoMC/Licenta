<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html SYSTEM "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>Salutare ${username}</p>
<br>
<p>Numarul tau de ore suplimentare este :  ${hours } </p>
<p>Iti mai poti lua inca  ${availableHours } ore.</p>
<br/><br/>

	<%-- <form:form action="asdadsa"  method="GET" id="myForm">
		<table border="1" width="100%">
			<tr>
				<th>Id norma</th>
				<th>Nume subiect</th>
				<th>Faculate</th>
				<th>Tip de studii</th>
				<th>Limba</th>
				<th>An</th>
				<th>Serie</th>
				<th>Ore/saptamana curs sem 1</th>
				<th>Ore/saptamana applicatii sem 1</th>
				<th>Ore/saptamana curs sem 2</th>
				<th>Ore/saptamana applicatii sem 2</th>
				<th>Ore detinute</th>
			</tr>
			
			<c:forEach var="tempNorm" items="${normsList }">
				<tr>
					<th><input type="radio" /></th>
					<th>${tempNorm.id }</th>
					<th>${tempNorm.subjectName }</th>
					<th>${tempNorm.faculty }</th>
					<th>${tempNorm.studiesType }</th>
					<th>${tempNorm.language }</th>
					<th>${tempNorm.year }</th>
					<th>${tempNorm.series }</th>
					<th>${tempNorm.hoursPerWeekCourse1 }</th>
					<th>${tempNorm.hoursPerWeekApplication1 }</th>
					<th>${tempNorm.hoursPerWeekCourse2 }</th>
					<th>${tempNorm.hoursPerWeekApplication2}</th>
					<th>TBD</th>
				</tr>			
			</c:forEach>
		</table>
	
	</form:form> --%>

</body>
</html>