<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar</title>
</head>
<body>

	<h2 align="center">Ingrese la información</h2>

	<form action="UpdateDetails" method="post">
		<table aling="center">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"
					value="<%=request.getParameter("id")%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" value="<%=request.getParameter("nombre")%>" maxlength="30" size="35" /></td>
			</tr>
			<tr>
				<td>Apellido:</td>
				<td><input type="text" name="apellido" value="<%=request.getParameter("apellido")%>" maxlength="40" size="35" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" value="<%=request.getParameter("email")%>" maxlength="100" size="35" /></td>
			</tr>
			<tr />
		</table>
		<br /> <input type="submit" value="Actualizar" />
	</form>
	<br />
	<input type="button" value="Volver a inicio" onclick="window.location.href='index.jsp'" />
</body>
</html>