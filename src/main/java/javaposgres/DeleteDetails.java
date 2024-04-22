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


public class DeleteDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Class.forName(DbUtil.driver);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not fund " + e);
		}
		
		try {
			
			// CONSEGUIR LA ID DADA
	        String idParam = request.getParameter("id");
	        
	        // VERIFICAR SI EL CAMPO DE LA ID ESTÁ VACIO
	        if (idParam.isEmpty()) {
	            // CREAR UN ERROR EN EL CASO DE ESTÁR VACIA
	            throw new IllegalArgumentException("El parámetro 'id' es nulo o está vacío.");
	        }
			
	        // CONVERTIR LA ID EN ENTERO Y CREAR VARIABLES DEL USUARIO A CAPTURAR
	        int id = Integer.parseInt(idParam);
            String nombre = "";
            String apellido = "";
            String email="";
			
            // CREAR LA CONEXIÓN CON LA BASE DE DATOS
			Connection cnn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
			System.out.println("Conection suceful");
			
			// SENTENCIA CONSULTA SQL PARA CAPTURAR LA ID DADA
			PreparedStatement checkId = cnn.prepareStatement("SELECT est_id FROM estudiante WHERE est_id=?");
            checkId.setInt(1, id);
            ResultSet checkIdResult = checkId.executeQuery();
            
            // VARIFICAR SI LA ID EXITE EN LA BASE DE DATOS
            boolean idExists = checkIdResult.next();

            if (!idExists) {
            	
            	// EN CASO DE NO EXISTIR SE CREA UN ERROR
                throw new IllegalArgumentException("La ID especificada no existe.");
            }
			
            checkId.close();
            checkIdResult.close();
            
            // SENTENCIA SQL PARA ACTUALIZAR EL VALOR DE ESTADO DEL USUARIO A "FALSE" PARA BUENAS PRACTICAS            
			PreparedStatement st = cnn.prepareStatement("UPDATE estudiante SET est_estado= false WHERE est_id=?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
			st.close();
			
			// SENTENCIA SQL PARA CAPTURAR LOS VALORES MODIFICADOS
			PreparedStatement consulta = cnn.prepareStatement("SELECT est_id, est_nombre, est_apellido, est_email FROM estudiante WHERE est_id=?");
			consulta.setInt(1, id);
			ResultSet rs = consulta.executeQuery();
			
			// CAPTURAR DATO A DATO
			while (rs.next()) {
                nombre = rs.getString("est_nombre");
                apellido = rs.getString("est_apellido");
                email = rs.getString("est_email");
                
            }
			
			consulta.close();
			
			cnn.close();
			
			// ENVIAR LOS DATOS MODIFICADOS CON MENSAJE DE PROCESO EXITOSO
			response.sendRedirect("Succes.jsp?msg=Delete&id=" + id + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email);
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			// EN CASO DE EEROR, REDIRECCIONA A OTRA PAGINA EXPLICANDO EL ERROR
			response.sendRedirect("Error.jsp?msg=" + e.getMessage());
			
		}
		
	}

}
