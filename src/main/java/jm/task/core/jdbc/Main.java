package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getSessionFactory();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Maxim", "Kapustin", (byte) 26);
        userService.saveUser("Anton", "Permyakov", (byte) 14);
        userService.saveUser("Roman", "Nozarenko", (byte) 21);
        userService.saveUser("Vladimir", "Buharov", (byte) 36);
        List<User> list = userService.getAllUsers();
        for(User s : list){
            System.out.println(s.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
