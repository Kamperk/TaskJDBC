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
        PreparedStatement statement = null;
        try {
            statement = Util.getConnection().prepareStatement("CREATE table User\n" +
                    "(  ID INT AUTO_INCREMENT primary key,\n" +
                    "   Name VARCHAR(45),\n" +
                    "   Lastname VARCHAR(45),\n" +
                    "   Age TINYINT(3)\n" +
                    ")");
            statement.execute();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                    Util.getConnection().close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void dropUsersTable() {
        PreparedStatement statement = null;
        try {
            statement = Util.getConnection().prepareStatement("DROP TABLE User");
            statement.execute();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    Util.getConnection().close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO UserJM.User (Name, Lastname, Age) VALUES (?, ?, ?)";
            statement = Util.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    Util.getConnection().close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM User\n" +
                    "where ID = ?;";
            statement = Util.getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    Util.getConnection().close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }

        }
    }

    public List<User> getAllUsers() {
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "SELECT * FROM USER";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getNString(2));
                user.setLastName(resultSet.getNString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
        }
        return list;
    }

    public void cleanUsersTable() {
        PreparedStatement statement = null;
        try {
            String sql = "TRUNCATE TABLE USER ";
            statement = Util.getConnection().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    Util.getConnection().close();
                }
            } catch (SQLException e) {
                System.out.printf("Ошибка закрытия");
            }
        }
    }
}
