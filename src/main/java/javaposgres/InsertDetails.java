package javaposgres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "InserDetails", value = "/InserDetails")
public class InsertDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			try {
				
				Class.forName(DbUtil.driver);
				
			} catch (ClassNotFoundException e) {
				System.out.println("Class not fund " + e);
			}
			
			try {
				
				
				// DECLARACIÓN DE VARIABLES A USAR
				String idParam = request.getParameter("id");
				String nombre = request.getParameter("nombre");
		        String apellido = request.getParameter("apellido");
		        String email = request.getParameter("email");
		        
		        // VERIFICAR SI EL CAMPO DE LA ID ESTÁ VACIO
		        if (idParam.isEmpty()) {
		            
		        	//CREA UN ERROR SI EL CAMPO DE LA ID ESTÁ VACIO
		            throw new IllegalArgumentException("El parámetro 'id' es nulo o está vacío.");
		        }
				
		        // CONVERTIR EL PARAMETRO A ENTERO
		        int id = Integer.parseInt(idParam);
				
		        // CONECTARSE A LA BASE DE DATOS
				Connection cnn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
				// MENSAJE AL CONECTARSE
				System.out.println("Conecction succelful");
				
				// SENTENCIA SQL PARA VERIFICAR SI EXISTE LA ID
				PreparedStatement checkId = cnn.prepareStatement("SELECT COUNT(*) FROM estudiante WHERE est_id = ?");

				// ENTREGAR EL VALOR DADO A LA SENTENCIA SQL
				checkId.setInt(1, id);

				// GUARDAR EL RESULTADO DE LA BASE DE DATOS EN UNA VARIABLE
				ResultSet checkIdResult = checkId.executeQuery();

				// VERIFICAR SI LA ID ESTÁ REPETIDA EN LA BASE DE DATOS
				if (checkIdResult.next()) {
				    int idCount = checkIdResult.getInt(1);
				    if (idCount > 0) {
				        // SI ESTÁ REPETIDA: CREAR UN ERROR
				        throw new IllegalArgumentException("La ID ya está registrada.");
				    }
				}

				// CERRAR RECURSOS
				checkIdResult.close();
				checkId.close();
				
				//SENTENCIA SQL PARA CONTAR LAS VECES EN LAS QUE HAY UN EMAIL
	            PreparedStatement checkEmail = cnn.prepareStatement("SELECT COUNT(*) FROM estudiante WHERE est_email = ?");
	            
	            // ENTREGAR EL VALOR DADO A LA SENTENCIA SQL
	            checkEmail.setString(1, email);
	            
	            // GUARDAR EL RESULTADO DE LA BASE DE DATOS EN UNA VARIABLE
	            ResultSet checkEmailResult = checkEmail.executeQuery();
	            
	            checkEmailResult.next();
	            
	            // VERIFICAR SI EL EMAIL ESTARÍA REPETIDO EN LA BASE DE DATOS
	            int emailCount = checkEmailResult.getInt(1);

	            if (emailCount > 0) {
	            	
	            	//SI ESTÁ REPETIDO: CREA UN ERROR
	                throw new IllegalArgumentException("El correo electrónico ya está registrado.");
	                
	            }
	            
	            // CERRAR CONEXIONES
	            checkEmail.close();
	            checkEmailResult.close();
	            
	            // SENTENCIA SQL PARA INSERTAR LOS DATOS DESPUES DE VERIFICAR SU VALIDES				
				PreparedStatement st = cnn.prepareStatement("INSERT INTO estudiante(est_nombre, est_apellido, est_email, est_id) VALUES(?, ?, ?, ?)");
				
				// ENTRAGAR LOS VALORES DADOS A LA BASE DE DATOS
				st.setString(1, nombre);
				st.setString(2, apellido);
				st.setString(3, email);
				st.setInt(4, id);
				
				// EN EL CASO EN EL QUE EL EMAIL ESTÉ VACIO CREA UN ERROR
				if (email.isEmpty()) {
					throw new IllegalArgumentException("Es necesario un correo");
				}
				
				st.executeUpdate();
				
				// CERRAR CONEXIONES
				st.close();
				cnn.close();
				
				// ENVAIR MENSAJE DE APROBACIÓN CON LOS NUEVOS DATOS INCERTADOS
				response.sendRedirect("Succes.jsp?msg=Insert&id=" + id + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email);
				
			}catch (Exception e) {
				
				e.printStackTrace();
				response.sendRedirect("Error.jsp?msg=" + e.getMessage());
				
			}
			
			
			
	}

}
