package ui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/FUNCIONARIOS"; // cambia por tu DB
    private static final String USER = "ALEXISCQ"; // cambia si es necesario
    private static final String PASS = "ALEXISCQ"; // cambia si es necesario

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
