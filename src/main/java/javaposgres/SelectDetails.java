package javaposgres;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName(DbUtil.driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found " + e);
		}
		try {
			
			// OBTENER EL PARÁMETOR 'ID' DE LA SOLICITUD HTTP
            String idParam = request.getParameter("id");
            // VERIFICAR SI EL PARÁMETRÓ ESTÁ VACIO
            if (idParam.isEmpty()) {
                throw new IllegalArgumentException("El parámetro 'id' es nulo o está vacío.");
            }

            // CONVERTIR EL PARAMETRO 'ID' EN UN ENTERO
            int id = Integer.parseInt(idParam);
            String nombre = "", apellido = "", email = "";

            // ESTABLECER LA CONEXIÓN A LA BASE DE DATOS
            Connection cnn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            System.out.println("connection successful");

            // CONSULTA SQL PARA VERIFICAR EL ESTADO DEL ESTUDIANTE
            PreparedStatement comprobar = cnn.prepareStatement("SELECT est_estado FROM estudiante WHERE est_id=?;");
            comprobar.setInt(1, id);
            ResultSet respuesta = comprobar.executeQuery();

            boolean estado = false;

            // VERIFICAR SI SE ENCONTRÓ UN RESULTADO EN LA CONSULTA
            if (respuesta.next()) {
                estado = respuesta.getBoolean("est_estado");
            }

            // SI EL ESTADO ES 'FALSE' CREAR UNA EXCEPCIÓN
            if (!estado) {
                throw new IllegalArgumentException("El parámetro no existe");
            }

            // CERRAR RECURSOS DE BASE DE DATOS
            comprobar.close();
            respuesta.close();


            // CONSULTA SQL PARA OBTENER LOS DETALLES DEL ESTUDIANTE
            PreparedStatement st = cnn.prepareStatement("SELECT * FROM estudiante WHERE est_id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            // OBTENER DETALLES DEL ESTUDIANTE EN VARIABLES
            while (rs.next()) {

                nombre = rs.getString(1);
                apellido = rs.getString(2);
                email = rs.getString(3);
            }

            // CERRAR RECURSOS DE BASE DE DATOS
            rs.close();
            st.close();
            cnn.close();

            // REDIRIGIR LA RESPUESTA A UNA PÁGINA JSP CON LOS DETALLES DEL ESTUDIANTE
            response.sendRedirect("Result.jsp?id=" + id + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email);

        } catch (Exception e) {
            e.printStackTrace();
            
            // REDIRIGIR A UNA PÁGINA DE ERROR EN CASO DE EXCEPCIÓN
            response.sendRedirect("Error.jsp?msg=" + e.getMessage());
        }


    }

}