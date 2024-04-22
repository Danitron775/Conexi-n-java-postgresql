<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado</title>
<script defer>
	function update() {
    	let sid = document.getElementById('detalles').elements['stid'].value;
    	let nombre = document.getElementById('detalles').elements['stname'].value;
    	let apellido = document.getElementById('detalles').elements['stlast'].value;
    	let email = document.getElementById('detalles').elements['stemail'].value;
    	
    	window.location.href = "Update.jsp?id=" + sid + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email;
	}
</script>
</head>
<body>

	<h2 align="center">Detalles</h2>

	<form id=detalles>
		<input type="hidden" name="stid" value="<%=request.getParameter("id")%>">
		<input type="hidden" name="stname" value="<%=request.getParameter("nombre")%>">
		<input type="hidden" name="stlast" value="<%=request.getParameter("apellido")%>">
		<input type="hidden" name="stemail" value="<%=request.getParameter("email")%>">
			
		

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
		<br />
	</form>
	<br />
	<input type="button" value="Actualizar datos" onclick="update()" />
	<br />
	<br/>
	<input type="button" value="Volver a inicio" onclick="window.location.href='index.jsp'" />

</body>

</html>