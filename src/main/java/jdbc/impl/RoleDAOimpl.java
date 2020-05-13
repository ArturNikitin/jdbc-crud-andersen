package jdbc.impl;

import jdbc.RoleDAO;
import jdbc.Utils;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOimpl implements RoleDAO {


    @Override
    public Role create(String name) {
        try(
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO roles (name) VALUES (?)")) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Role(name);
    }

    @Override
    public Role update(Role role) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try
                {
            connection = Utils.getConnection();
            statement = connection
                    .prepareStatement("UPDATE roles SET name = (?) WHERE id = (?)");
            connection.setAutoCommit(false);
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
        return role;
    }

    @Override
    public Role read(String name) {
        Role role = new Role();

        try(
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM roles WHERE NAME = (?)")) {
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
        try (
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM roles WHERE id = (?)")) {
            statement.setInt(1, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();

        try(
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM roles")) {
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
    public List<Role> getAllRolesByUser(User user) {
        List<Role> roles = new ArrayList<>();
        try(
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT r.id, r.name " +
                                "FROM (SELECT ur.role_id as urid FROM users_roles AS ur " +
                "INNER JOIN users as u ON ur.user_id = u.id WHERE u.id = (?)) as temp " +
                "INNER JOIN roles AS r ON temp.urid = r.id")) {
            statement.setInt(1, user.getId());
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (roles.isEmpty()) {
            return null;
        }
        return roles;
    }

    @Override
    public Role addUser(Role role, User user) {
        try(
                Connection connection = Utils.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO users_roles VALUES ((?), (?)) ")) {
            statement.setInt(1,user.getId());
            statement.setInt(2, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
