<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar estudiante</title>
</head>
<body>
	
	<h2 align="center">Rellene los campos a eliminar</h2>
	
	<form action="DeleteDetails" method="post">
		<table align="center">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id" maxlength="6" size="25" /></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Eliminar" />
	</form>
	<br />

	<input type="button" value="Volver a inicio"onclick="window.location.href='index.jsp'" />

</body>
</html>