package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final UserDao user = new UserDaoJDBCImpl();
    Connection connection = Util.getConnection();
    private UserDaoJDBCImpl() {
    }
    public static UserDao getUser(){
        return user;
    }

    public void createUsersTable() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("CREATE table User\n" +
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
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void dropUsersTable() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DROP TABLE User");
            statement.execute();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            String sql ="INSERT INTO UserJM.User (Name, Lastname, Age) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
            connection.rollback();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM User\n" +
                    "where ID = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
            try{
                connection.rollback();
            }catch (SQLException p){
                System.out.print("Ошибка отката");
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
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
            Statement statement = connection.createStatement();
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
            connection.setAutoCommit(false);
            String sql = "TRUNCATE TABLE USER;";
            statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.print("Ошибка соеденения");
            try{
                connection.rollback();
            }catch (SQLException p){
                System.out.print("Ошибка отката");
            }
        } finally {
            try {
                if (statement != null)
                {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.print("Ошибка закрытия");
            }
        }
    }
}
