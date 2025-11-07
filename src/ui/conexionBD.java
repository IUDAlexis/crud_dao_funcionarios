package src.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/recursos_humanos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "tu_usuario";
    private static final String PASS = "tu_contraseña";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // driver no encontrado
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    
}
}
