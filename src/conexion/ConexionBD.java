package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // URL simplificada para evitar errores de denegación
    private static final String URL = "jdbc:mysql://bslq1szixi7rlqpghdj1-mysql.services.clever-cloud.com:3306/bslq1szixi7rlqpghdj1";
    private static final String USER = "u8hcsurqmxyg97oa";
    private static final String PASS = "6EpneIQnq1gYYdMcSlyr"; // COPIA Y PEGA OTRA VEZ DESDE LA WEB

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("¡CONEXIÓN EXITOSA!");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontró el Driver");
        } catch (SQLException e) {
            // Este print nos dirá si sigue siendo la contraseña
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return con;
    }
}