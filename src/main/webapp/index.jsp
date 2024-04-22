<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<h2 align="center">Administrador de estudiantes</h2>
	<br/>
	
	<table align="center">
	
		<tr>
		
		 <td>Para insertar registro: </td>
		 <td><input type="button" value="Agregar" onclick="window.location.href='Insert.jsp'"/></td>
		
		</tr>
		
		<tr>
		
		 <td>Para eliminar registro: </td>
		 <td><input type="button" value="Eliminar" onclick="window.location.href='Delete.jsp'"/></td>
		
		</tr>
		
		<tr>
		
		 <td>Para ver registros: </td>
		 <td><input type="button" value="Ver todo" onclick="window.location.href='Select.jsp'"/></td>
		
		</tr>
	
	</table>
</body>
</html>