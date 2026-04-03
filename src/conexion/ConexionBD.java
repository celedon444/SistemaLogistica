package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    // Añadimos parámetros de zona horaria y SSL para evitar errores comunes en la nube
    private static final String URL = "jdbc:mysql://bslq1szixi7rlqpghdj1-mysql.services.clever-cloud.com:3306/bslq1szixi7rlqpghdj1?useSSL=false&serverTimezone=UTC";
    private static final String USER = "u8hcsurqmxyg97oa";
    private static final String PASS = "6EpneIQnq1gYYdMcSlyr"; 

    public static Connection conectar() {
        Connection con = null;
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Intentar conectar
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(">>> Conexión establecida con Clever Cloud");
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se encontró el Driver de MySQL. Verifica la librería Connector/J.");
        } catch (SQLException e) {
            // Si falla la red o la base de datos está caída
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.\n" 
                    + "Verifica tu conexión a internet o los parámetros de acceso.");
            System.out.println("Detalle SQL: " + e.getMessage());
        }
        return con;
    }
}