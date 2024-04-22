<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar</title>
</head>
<body>
	
	<h2 align="center">Llene los campo a solicitar</h2>
	
	<form action="SelectDetails" method="get">
		<table align="center">
			<tr>
				<td>Enter ID:</td>
				<td><input type="text" name="id" maxlength="6" size="35" /></td>
			</tr>

		</table>
		<br /> <input type="submit" value="Ver datos" />
	</form>
	<br />
	<input type="button" value="volver a inicio" onclick="window.location.href='index.jsp'" />
	
</body>
</html>