package jdbc.impl;

import jdbc.UserDAO;
import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    private final Connection connection;

    public UserDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(String name, String lastName, int age) {
        User user = new User(name, lastName, age);
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO users (Name, Lastname, age) VALUES ((?), (?), (?))")) {
            statement.setString(1, name);
            statement.setString(2,lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User read(String name, String lastName) {
        User user = new User();

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE name = (?) and lastname =(?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = (?), lastname = (?), age = (?) WHERE id = (?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setInt(4, user.getId());
            statement.execute();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = (?)")) {
            statement.setInt(1, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return null;
    }
}
