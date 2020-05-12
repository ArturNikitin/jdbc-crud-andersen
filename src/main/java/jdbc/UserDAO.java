package jdbc;

import model.Role;
import model.User;

import java.util.List;

public interface UserDAO {
    User create(String name, String lastName, int age);
    User read(String name, String lastName);
    User update(User user);
    void delete(User user);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    User addRole(User user, Role role);

}
