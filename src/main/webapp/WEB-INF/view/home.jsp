<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>luv2code Company Home Page</title>
</head>
<body>
	<h2>Bun venit pe aplicatia Additional Payment</h2>
	
	<hr>
	<!--  display user name and role -->
	<p>
		Utilizator: <security:authentication property="principal.username" />
		<br /><br />
		Privilegiu : <security:authentication property="principal.authorities" />
	</p>
	
	<hr>
	
	
	<security:authorize access="hasRole('MANAGER')">
	
	<!--  Add a link to point to /leaders.... this is for managers -->
	<p>
		<a href="${pageContext.request.contextPath }/leaders">LeaderShip Meeting</a>
		(Only for Manager peeps)
	</p>
	
	</security:authorize>
	

	<%-- <security:authorize access="hasRole('ADMIN')">

	<!--  add a link to point to /systems... this is only for admins -->
	<p>
		<a href="${pageContext.request.contextPath }/systems">IT Systems Meeting</a>
		(Only for Admin peeps)
	</p>
	</security:authorize> --%>
	
	
	<security:authorize access="hasRole('ADMIN')">
	
	<form action="uploadSubjects" method="get">
		<input type="submit" value="Actualizati materiile">
	
	</form>
	
	</security:authorize>
	
	<hr>

	<br />
	
	
	<form:form action="selectSubject" method="GET">
	<select name="tempSubject" id="tempSubject">
		<c:forEach var="tempSubject" items="${materii }">
	  			<option value="${tempSubject }">${tempSubject }</option>
	  		</c:forEach>
	</select>
	<br />
	<input type="submit" value="Continuati"/>
	</form:form>
	
	<hr>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			method="POST">
		
		<input type="submit" value="Logout" />
	</form:form>
	
	<form:form action="checkAdditionalHours" method="GET">
	<p>Verificati numarul de ore suplimentare pe care le aveti : </p>
	<input type="submit" value="Verificati" />
	</form:form>
	
	
</body>
</html>