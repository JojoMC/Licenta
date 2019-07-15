<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>${tempSubject }</h2>
	
	<p> Va rugam sa selectati seria la care vreti sa va alegeti normele suplimentare : </p>
	
	
	<form action="selectSeries" method="GET">
		 <select name="tempSeries" id="tempSeries">
			  <option value="A">A</option>
			  <option value="B">B</option>
			  <option value="C">C</option>
			  <option value="D">D</option>
			  <option value="E">E</option>
			  <option value="F">F</option>
			  <option value="G">G</option>
		</select>
		<select name="tempSubject" id="tempSubject" style="display: none;">
		 	<option>${tempSubject }</option>
		</select>
		<br>
		<input type="submit" value="Selectati seria" />
	</form>
	
	
</body>
</html>