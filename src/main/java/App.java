import jdbc.RoleDAO;
import jdbc.UserDAO;
import jdbc.Utils;
import jdbc.impl.RoleDAOimpl;
import jdbc.impl.UserDAOimpl;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        RoleDAO roleDAO = new RoleDAOimpl();
        UserDAO userDAO = new UserDAOimpl();

    }
}
