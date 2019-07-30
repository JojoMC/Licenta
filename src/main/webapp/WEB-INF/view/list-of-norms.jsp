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

	
		
		<form:form action="confirmNorms" method="GET" id="myForm">		
		 <table  border="1">
				 <tr>
				 	<th>Interes norma</th>
				 	<th>Ore curs dorite</th>
				 	<th>Ore aplicatii dorite</th>
				 	<th>Ore curs disponibile</th>
				 	<th>Ore aplicatii disponibile</th>
				 	<th>Titlu</th>
				 	<th>disponibilitate</th>
				 	<th>Nume materie</th>
				 	<th>Facultate</th>
				 	<th>Tip studii</th>
				 	<th>Limba predare</th>
				 	<th>An</th>
				 	<th>Seria</th>
				 	<th>Ore curs saptamana sem 1</th>
				 	<th>Ore applicatii saptamana sem 1</th>
				 	<th>Ore curs saptamana sem 2</th>
				 	<th>Ore applicatii saptamana sem 2</th>
				 </tr>
		 
		 		<c:forEach var="tempNorm" items="${requiredNormList }">
		  			<tr >
		  			
		  			<c:choose>
    					<c:when test="${tempNorm.availability == 'vacant'}">
        					<th width="7%">
        					<fieldset id="radio">
                     			<label><input type="radio" id="ceva.${tempNormId }" name="ceva" value="${tempNorm.id }" class="${ tempNorm.id}.checkBox" onChange="myFunction(${tempNorm.id },this)"></label>
		  					</fieldset>
		  					</th>
		  					<th><input type="text" name="takenCourseHours" value="0" id="1.${tempNorm.id }" class="textInput" disabled></th>
			  				<th><input type="text" name="takenApplicationHours" value="0" id="2.${tempNorm.id }" class="textInput" disabled></th>
			  				<script>

			  					 function myFunction(id, element) {
			  						
									console.log(id);
									console.log('1.'+id);
									var elementId = id + 'textInputCourse';
									if(document.getElementById('1.'+id).disabled && element.checked) {
										var variables = document.getElementsByClassName("textInput");
										for(var i=0; i<variables.length; i++) {
											console.log(variables[i]);
											variables[i].disabled=true;
										}
										document.getElementById('1.'+id).disabled=false;
										document.getElementById('2.'+id).disabled=false;
										
									}
									else {
										document.getElementById('1.'+id).disabled=true;
										document.getElementById('2.'+id).disabled=true;
									} 
									console.log(element);
	
			  					} 
			  				</script>
    					</c:when>
    					<c:otherwise>
    						<th width="7%">
        						<label>ocupata</label>
        					</th>
        					<th>-</th>
        					<th>-</th>
    					</c:otherwise>
					</c:choose>
		  		
<%-- 		  			
	  					<th>${tempNorm.id }</th>
		  				<th>${tempNorm.normNumber }</th> --%>
		  				<th>${tempNorm.availableHoursCourse }</th>
		  				<th>${tempNorm.availableHoursApplication }</th>
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