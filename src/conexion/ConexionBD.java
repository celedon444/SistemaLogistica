package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {


    private static final String URL = "jdbc:mysql://bslq1szixi7rlqpghdj1-mysql.services.clever-cloud.com:3306/bslq1szixi7rlqpghdj1";
    private static final String USER = "u8hcsurqmxyg97oa";
    private static final String PASS = "6EpneIQnq1gYYdMcSlyr"; 

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontro el driver");
        } catch (SQLException e) {
            // Este print nos dirá si sigue siendo la contraseña
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}