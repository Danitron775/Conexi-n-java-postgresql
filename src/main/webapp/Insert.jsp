<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar</title>
<style>
    .invalid-input {
        border: 1px solid red;
    }
</style>
</head>
<body>

	<h2 align="center">Complete los datos del estudiante</h2>
	<br>
	
	<form id="formulario" action="InsertDetails" method="post">
	
		<table align="center">
	
			<tr>
				<td><label for="id">id:</label></td>
				<td><input type="text" id="id" name="id" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td><label for="nombre">Nombre:</label></td>
				<td><input type="text" id="nombre" name="nombre" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td><label for="apellido">Apellido:</label></td>
				<td><input type="text" id="apellido" name="apellido" maxlength="50" size="35" /></td>
			</tr>
			<tr>
				<td><label for="email">Email:</label></td>
				<td><input type="text" id="email" name="email" maxlength="100" size="35" /></td>
			</tr>
		
		</table>
		
		<br>
		<input type="submit" value="Insertar datos" disabled/>
	
	</form>
	
	
	
	<br>
	<input type="button" value="Regresar" onclick="window.location.href='index.jsp'"/>
	
<script type="text/javascript">
//EXPRESIONES REGULARES PARA VALIDAR LOS CAMPOS
const idRegex = /^[0-9]+$/;
const nombreRegex = /^[a-zA-ZáéíóúÁÉÍÓÚ\s]+$/;
const apellidoRegex = /^[a-zA-ZáéíóúÁÉÍÓÚ\s]+$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// OBTENER LOS ELEMENTOS DEL DOM UNA VEZ
const idInput = document.getElementById('id');
const nombreInput = document.getElementById('nombre');
const apellidoInput = document.getElementById('apellido');
const emailInput = document.getElementById('email');
const submitButton = document.querySelector('input[type="submit"]');

// ESCUCHAR EL EVENTO KEYUP PARA ACTUALIZAR LA VALIDACIÓN DE LOS CAMPOS
document.addEventListener('keyup', validar);

// AGREGAR UN EVENT LISTENER PARA VALIDAR LOS CAMPOS ANTES DE ENVIAR EL FORMULARIO
document.getElementById("formulario").addEventListener("submit", function(event) {
    // VALIDAR LOS CAMPOS NUEVAMENTE ANTES DE ENVIAR EL FORMULARIO
    if (!validarCampos()) {
        // CANCELAR EL ENVÍO DEL FORMULARIO SI LOS CAMPOS NO SON VÁLIDOS
        event.preventDefault();
    }
});

function validar() {
    const idValido = validarCampo(idInput, idRegex);
    const nombreValido = validarCampo(nombreInput, nombreRegex);
    const apellidoValido = validarCampo(apellidoInput, apellidoRegex);
    const emailValido = validarCampo(emailInput, emailRegex);

    aplicarEstilo(idInput, idValido);
    aplicarEstilo(nombreInput, nombreValido);
    aplicarEstilo(apellidoInput, apellidoValido);
    aplicarEstilo(emailInput, emailValido);

    const todosLosCamposValidos = idValido && nombreValido && apellidoValido && emailValido;

    // HABILITAR O DESHABILITAR EL BOTÓN DE ENVIAR SEGÚN LA VALIDEZ DE LOS CAMPOS
    submitButton.disabled = !todosLosCamposValidos;

    return todosLosCamposValidos;
}


//FUNCIONES AUXILIARES
function validarCampo(input, regex) {
    return regex.test(input.value);
}

function aplicarEstilo(input, valido) {
    input.classList.toggle('invalid-input', !valido);
}

function validarCampos() {
    return validar();
}
</script>

</body>
</html>
