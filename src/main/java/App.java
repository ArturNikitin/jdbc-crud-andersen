import jdbc.RoleDAO;
import jdbc.UserDAO;
import jdbc.impl.RoleDAOimpl;
import jdbc.impl.UserDAOimpl;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String user = "postgres";
        String password = "1488";
        String url = "jdbc:postgresql://localhost:5432/user_role";

        final Connection connection = DriverManager.getConnection(url, user, password);
        RoleDAO roleDAO = new RoleDAOimpl(connection);
        UserDAO userDAO = new UserDAOimpl(connection);
        userDAO.getUsersByRole(roleDAO.read("actor")).forEach(x -> System.out.println(x.getName() + " " + x.getLastName()));
        connection.close();
    }
}
