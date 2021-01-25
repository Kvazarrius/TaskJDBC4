package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnectionJDBC();

//что то я туплю совсем тут) ну вроде так..

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        String create = "CREATE TABLE sometable " +
                "(id INT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(30), " +
                " lastName VARCHAR (30), " +
                " Age VARCHAR (3), " +
                " PRIMARY KEY (id))";
        connection.setAutoCommit(false);
        try {
            connection.createStatement().execute(create);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public void dropUsersTable() throws SQLException {
        String drop = "DROP TABLE IF EXISTS sometable";
        connection.setAutoCommit(false);
        try {
            connection.createStatement().execute(drop);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String add = "INSERT INTO sometable (name, lastName, age) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public List<User> removeUserById(long id) throws SQLException {
        String remove = "DELETE FROM sometable WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String getAll = "SELECT * FROM sometable";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery(getAll);
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
        String clean = "TRUNCATE TABLE sometable";
        connection.setAutoCommit(false);
        try {
            connection.createStatement().execute(clean);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }