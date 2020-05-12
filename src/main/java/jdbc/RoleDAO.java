package jdbc;

import model.Role;
import model.User;

import java.util.List;

public interface RoleDAO {
    Role create(String name);
    Role update(Role role);
    Role read(String name);
    void delete(Role role);
    List<Role> findAllRoles();
    List<Role> findAllRolesByUser(User user);
}
