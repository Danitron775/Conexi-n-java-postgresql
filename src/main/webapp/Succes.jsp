<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exitoso</title>
</head>
<body>
	<form action="index.jsp">
		<h3>
			<%=request.getParameter("msg")%>
			 Operación exitosa!
		</h3>
		
		<table align="center">
			<tr>
				<td>ID:</td>
				<td><%=request.getParameter("id")%></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><%=request.getParameter("nombre")%></td>
			</tr>
			<tr>
				<td>Apellido:</td>
				<td><%=request.getParameter("apellido")%></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><%=request.getParameter("email")%></td>
			</tr>
		</table>
		
		<br /> <input type="submit" value="Inicio" />

	</form>

</body>
</html>