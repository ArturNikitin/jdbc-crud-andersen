package jdbc;

import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    Role create(String name);
    Role update(Role role) throws SQLException;
    Role read(String name);
    void delete(Role role);
    List<Role> findAllRoles();
    List<Role> getAllRolesByUser(User user);
    Role addUser(Role role, User user);
}
