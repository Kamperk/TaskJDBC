package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends Util implements UserService, UserDao{
    private UserDao user = UserDaoJDBCImpl.getUser();
    public void createUsersTable() throws SQLException {
        user.createUsersTable();
    }

    public void dropUsersTable() {
        user.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        user.saveUser(name, lastName, age);
        System.out.printf("User c именем - %s добавлен в базу данных", name);
        System.out.println();
    }

    public void removeUserById(long id) {
        user.removeUserById(id);
    }

    public List<User> getAllUsers() {
         return user.getAllUsers();
    }

    public void cleanUsersTable() {
        user.cleanUsersTable();
    }
}
