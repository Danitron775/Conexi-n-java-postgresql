<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar</title>
<style>
    .invalid-input {
        border: 1px solid red;
    }
</style>
</head>
<body>

    <h2 align="center">Ingrese la información</h2>

    <form id="formulario" action="UpdateDetails" method="post">
        <table align="center">
            <tr>
                <td>ID:</td>
                <td><input type="text" name="id" value="<%=request.getParameter("id")%>" readonly="readonly"></td>
            </tr>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" id="nombre" name="nombre" value="<%=request.getParameter("nombre")%>" maxlength="30" size="35" /></td>
            </tr>
            <tr>
                <td>Apellido:</td>
                <td><input type="text" id="apellido" name="apellido" value="<%=request.getParameter("apellido")%>" maxlength="40" size="35" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" id="email" name="email" value="<%=request.getParameter("email")%>" maxlength="100" size="35" /></td>
            </tr>
            <tr />
        </table>
        <br /> 
        <input type="submit" value="Actualizar" disabled />
    </form>
    <br />
    <input type="button" value="Volver a inicio" onclick="window.location.href='index.jsp'" />

    <script type="text/javascript">
        // EXPRESIONES REGULARES PARA VALIDAR LOS CAMPOS
        const idRegex = /^[0-9]+$/;
        const nombreRegex = /^[a-zA-ZáéíóúÁÉÍÓÚ\s]+$/;
        const apellidoRegex = /^[a-zA-ZáéíóúÁÉÍÓÚ\s]+$/;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // OBTENER LOS ELEMENTOS DEL DOM UNA VEZ
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
            const nombreValido = validarCampo(nombreInput, nombreRegex);
            const apellidoValido = validarCampo(apellidoInput, apellidoRegex);
            const emailValido = validarCampo(emailInput, emailRegex);

            aplicarEstilo(nombreInput, nombreValido);
            aplicarEstilo(apellidoInput, apellidoValido);
            aplicarEstilo(emailInput, emailValido);

            const todosLosCamposValidos = nombreValido && apellidoValido && emailValido;

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
