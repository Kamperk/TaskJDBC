package jm.task.core.jdbc.util;import jm.task.core.jdbc.model.User;import jm.task.core.jdbc.service.UserServiceImpl;import java.sql.*;import java.util.List;public class Util {    private static final String URL = "jdbc:mysql://localhost:3306/UserJM?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";    private static final String USERNAME = "root";    private static final String PASSWORD = "Xxx412334!$";    public static Connection getConnection() {        Connection connection = null;        try {            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);        } catch (SQLException e) {            System.out.println("Не удалось подключиться к серверу");        }        return connection;    }    public static void main(String[] args){        UserServiceImpl userService = new UserServiceImpl();        userService.createUsersTable();        userService.saveUser("Maxim", "Kapustin", (byte) 26);        userService.saveUser("Anton", "Permyakov", (byte) 14);        userService.saveUser("Roman", "Nozarenko", (byte) 21);        userService.saveUser("Vladimir", "Buharov", (byte) 36);        List<User> list = userService.getAllUsers();        for(User s : list){            System.out.println(s.toString());        }        userService.cleanUsersTable();        userService.dropUsersTable();    }}