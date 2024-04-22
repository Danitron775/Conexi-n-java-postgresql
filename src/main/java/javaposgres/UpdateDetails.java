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


public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// INTENTAR CARGAR EL CONTROLADOR DE LA BASE DE DATOS
			Class.forName(DbUtil.driver);
		} catch (ClassNotFoundException e) {
			// EN CASO DE ERROR, IMPRIMIR EL MENSAJE DE ERROR
			System.out.println("Class not found " + e);
		}
		try {
			// OBTENER PARÁMETROS DE LA SOLICITUD HTTP
			String id = request.getParameter("id");
            String nombre = "";
            String apellido = "";
            String email="";
			
			// ESTABLECER CONEXIÓN CON LA BASE DE DATOS
			Connection cnn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
			System.out.println("connection successful");
			
			// CONSULTA SQL PARA VERIFICAR SI EL CORREO ELECTRÓNICO YA ESTÁ REGISTRADO
			PreparedStatement checkEmail = cnn.prepareStatement("SELECT COUNT(*) FROM estudiante WHERE est_email = ?");
            
            checkEmail.setString(1, email);
            
            ResultSet checkEmailResult = checkEmail.executeQuery();
            
            checkEmailResult.next();
            
            int emailCount = checkEmailResult.getInt(1);

            // SI EL CORREO ELECTRÓNICO YA ESTÁ REGISTRADO, LANZAR UNA EXCEPCIÓN
            if (emailCount > 0) {
            	
                throw new IllegalArgumentException("El correo electrónico ya está registrado.");
                
            }
            
            // CERRAR RECURSOS DE BASE DE DATOS
            checkEmail.close();
            checkEmailResult.close();
			
			// CONSULTA SQL PARA ACTUALIZAR LOS DETALLES DEL ESTUDIANTE
			PreparedStatement st = cnn.prepareStatement("update estudiante set est_nombre=?, est_apellido=?, est_email=? where est_id=?");

			st.setString(1, request.getParameter("nombre"));
			st.setString(2, request.getParameter("apellido"));
			st.setString(3, request.getParameter("email"));
			st.setInt(4, Integer.valueOf(id));

			// EJECUTAR LA ACTUALIZACIÓN
			st.executeUpdate();

			// CERRAR RECURSOS DE BASE DE DATOS
			st.close();
			cnn.close();

			// REDIRIGIR A UNA PÁGINA DE ÉXITO CON LOS DETALLES ACTUALIZADOS
			response.sendRedirect("Succes.jsp?msg=Update&id=" + id + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email);
			
		}catch (Exception e) {
			e.printStackTrace();
			// REDIRIGIR A UNA PÁGINA DE ERROR CON EL MENSAJE DE ERROR
			response.sendRedirect("Error.jsp?msg=" + e.getMessage());
		}
	}

}