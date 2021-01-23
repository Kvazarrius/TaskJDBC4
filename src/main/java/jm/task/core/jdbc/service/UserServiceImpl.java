package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDao one = new UserDaoJDBCImpl();

    public void createUsersTable() {
        one.createUsersTable();
    }

    public void dropUsersTable() {
        one.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        one.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        one.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return one.getAllUsers();
    }

    public void cleanUsersTable() {
        one.cleanUsersTable();
    }
}
