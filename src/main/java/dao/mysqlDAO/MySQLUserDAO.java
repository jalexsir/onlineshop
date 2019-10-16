package dao.mysqlDAO;

import dao.DAOFactory.UserDAO;
import entity.User;
import util.EncryptPassw;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {
    MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
    private final static String SELECT_QUERY_AUTH = "SELECT * FROM users WHERE login = ? AND password = ?";
    private final static String CHECK_EXIST_LOGIN = "SELECT * FROM users WHERE login = ?";
    private final static String INSERT_QUERY_REGISTER = "INSERT INTO users(login, password, name, region, gender, comment)"
            + " VALUES(?, ?, ?, ?, ?, ?)";
    private final static String SELECT_QUERY_ALLUSERS = "SELECT * FROM users";
    private final static String UPDATE_QUERY_USER = "UPDATE users SET name=?, region =?, gender=?, comment=?  WHERE login = ?";


    public MySQLUserDAO() {
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = mySQLDAOFactory.createConnection();
        ResultSet rs = null;
        ArrayList<User> list = new ArrayList<>();
        try (Statement selectStmt = connection.createStatement()) {
            rs = selectStmt.executeQuery(SELECT_QUERY_ALLUSERS);
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("login"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("region"), rs.getBoolean("gender"),
                        rs.getString("comment")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }
            if (connection != null) {
                mySQLDAOFactory.closeConnection(connection);
            }
        }
        return list;
    }

    @Override
    public User getUserByLogin(String login) {
        Connection connection = mySQLDAOFactory.createConnection();
        ResultSet rs = null;
        User user = null;
        try (PreparedStatement prepSt = connection.prepareStatement(CHECK_EXIST_LOGIN)) {
            prepSt.setString(1, login);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("login"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("region"), rs.getBoolean("gender"),
                        rs.getString("comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }
            if (connection != null) {
                mySQLDAOFactory.closeConnection(connection);
            }
        }
        return user;
    }

    @Override
    public User getUserByLoginAndPass(String login, String password) {
        Connection connection = mySQLDAOFactory.createConnection();
        User user = null;
        ResultSet rs = null;
        try (PreparedStatement prepSt = connection.prepareStatement(SELECT_QUERY_AUTH)) {
            prepSt.setString(1, login);
            prepSt.setString(2, EncryptPassw.toHashWithSalt(password));
            rs = prepSt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("login"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("region"), rs.getBoolean("gender"),
                        rs.getString("comment"));
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                mySQLDAOFactory.closeConnection(connection);
            }
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = mySQLDAOFactory.createConnection();
        boolean success = false;
        try (PreparedStatement prepSt = connection.prepareStatement(UPDATE_QUERY_USER)) {
            prepSt.setString(1, user.getName());
            prepSt.setString(2, user.getRegion());
            prepSt.setBoolean(3, user.isGender());
            prepSt.setString(4, user.getComment());
            prepSt.setString(5, user.getLogin());

            prepSt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                mySQLDAOFactory.closeConnection(connection);
            }
        }
        return success;
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = mySQLDAOFactory.createConnection();
        boolean success = false;
        try (PreparedStatement prepSt = connection.prepareStatement(INSERT_QUERY_REGISTER)) {
            prepSt.setString(1, user.getLogin());
            prepSt.setString(2, EncryptPassw.toHashWithSalt(user.getPassword()));
            prepSt.setString(3, user.getName());
            prepSt.setString(4, user.getRegion());
            prepSt.setBoolean(5, user.isGender());
            prepSt.setString(6, user.getComment());

            prepSt.executeUpdate();
            success = true;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                mySQLDAOFactory.closeConnection(connection);
            }
        }
        return success;
    }


}
