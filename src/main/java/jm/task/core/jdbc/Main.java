package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        String testName = "Igor";
        String testLastName = "Perov";
        Byte testAge = 22;
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        User user = userService.getAllUsers().get(0);
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
    }
}
