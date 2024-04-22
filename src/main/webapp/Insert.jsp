<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar</title>
</head>
<body>

	<h2 align="center">Complete los datos del estudiante</h2>
	<br>
	
	<form action="InserDetails" method="post">
	
		<table align="center">
	
			<tr>
				<td>id:</td>
				<td><input type="text" name="id" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td>Apellido :</td>
				<td><input type="text" name="apellido" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" maxlength="100" size="35" /></td>
			</tr>
		
		</table>
		
		<br>
		<input type="submit" value="Insertar datos"/>
	
	</form>
	
	
	
	<br>
	<input type="button" value="Regresar" onclick="window.location.href='index.jsp'"/>
	
	

</body>
</html>