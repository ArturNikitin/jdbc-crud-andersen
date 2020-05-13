package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    private static Connection connection;
    private static final String USER = "postgres";
    private static final String PASSWORD = "1488";
    private static final String URL = "jdbc:postgresql://localhost:5432/user_role";

    public static Connection getConnection() throws SQLException {
        return connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
