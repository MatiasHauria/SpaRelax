package Persistencia;
import java.sql.*;
/**
 *
 * @author matute
 */
public class Conexion {
    static String url = "jdbc:mariadb://localhost:3306/sparelax";
    static String user = "root";
    static String password = "";
    
    public static Connection establecerConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa.");
        } catch (SQLException s) {
            s.getMessage();
        }
        return con;
    }
    
}
