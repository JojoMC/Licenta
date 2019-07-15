<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html SYSTEM "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Seria ${tempSeries }</h1>

<br>
<p>Normele disponibile la seria ${tempSeries } pentru materia ${tempSubject } sunt urmatoarele : </p>

		
		
		<form:form action="confirmNorms" method="GET">
		
		 <table style="width:100%" border="1">
		 
		 		<c:forEach var="tempNorm" items="${requiredNormList }">
		  			<tr >
		  			
		  			<c:choose>
    					<c:when test="${tempNorm.availability == 'vacant'}">
        					<th width="7%">
                     			<label><input type="checkbox" id='ceva' name="ceva" value="${tempNorm.id }"></label>
		  					</th>
    					</c:when>
    					<c:otherwise>
    						<th width="7%">
        						<label>ocupata</label>
        					</th>
    					</c:otherwise>
					</c:choose>
		  			
		  			
		  			<!-- https://stackoverflow.com/questions/9140633/how-to-change-the-value-of-a-check-box-onclick-using-jquery -->
		  			
		  				<th>${tempNorm.id }</th>
		  				<th>${tempNorm.normNumber }</th>
		  				<th>${tempNorm.positionName }</th>
		  				<th>${tempNorm.availability}</th>
		  				<th>${tempNorm.subjectName}</th>
		  				<th>${tempNorm.faculty}</th>
		  				<th>${tempNorm.studiesType }</th>
		  				<th>${tempNorm.language}</th>
		  				<th>${tempNorm.year}</th>
		  				<th>${tempNorm.series}</th>
		  				<th>${tempNorm.hoursPerWeekCourse1 }</th>
		  				<th>${tempNorm.hoursPerWeekApplications1 }</th>
		  				<th>${tempNorm.hoursPerWeekCourse2 }</th>
		  				<th>${tempNorm.hoursPerWeekApplications2 }</th>
		  			</tr>
		  		</c:forEach>
		</table> 
		<input type="submit" value="Continuati" />

		</form:form>


</body>
</html>