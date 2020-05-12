package jdbc.impl;

import jdbc.RoleDAO;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOimpl implements RoleDAO {
    private final Connection connection;

    public RoleDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Role create(String name) {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO roles (name) VALUES (?)")) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Role(name);
    }

    @Override
    public Role update(Role role) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE roles SET name = (?) WHERE id = (?)")) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role read(String name) {
        Role role = new Role();

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles WHERE NAME = (?)")) {
            statement.setString(1, name);
            final ResultSet set = statement.executeQuery();
            if (set.next()) {
                role.setId(set.getInt(1));
                role.setName(set.getString(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public void delete(Role role) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM roles WHERE id = (?)")) {
            statement.setInt(1, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles")) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public List<Role> findAllRolesByUser(User user) {
        return null;
    }
}
