package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String create = "CREATE TABLE sometable " +
                "(id INT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(30), " +
                " lastName VARCHAR (30), " +
                " Age VARCHAR (3), " +
                " PRIMARY KEY (id))";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(create)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS sometable";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(drop)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String add = "INSERT INTO sometable (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(add)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM sometable WHERE id=?";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String getAll = "SELECT * FROM sometable";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(getAll);
             ResultSet resultSet = preparedStatement.executeQuery(getAll)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String SQLQueryCleanUsersTable = "TRUNCATE TABLE sometable";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(SQLQueryCleanUsersTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
