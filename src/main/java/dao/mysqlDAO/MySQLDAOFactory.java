package dao.mysqlDAO;

import dao.DAOFactory.DAOFactory;
import dao.DAOFactory.ProductDAO;
import dao.DAOFactory.UserDAO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    public static final String DRIVER =
            "com.mysql.jdbc.Driver";
    public static final String DBURL =
            "jdbc:mysql://localhost/iteashop?" + "user=root&password=";

    @Override
    public Connection createConnection() {
        Connection conn = null;
        try {
            System.out.print("Loading driver... ");
            Class.forName(DRIVER).newInstance();
            System.out.println("Success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            System.out.print("Obtaining connection... ");
            conn = DriverManager.getConnection(DBURL);
            System.out.println("OK");
        } catch (SQLException ex) {
            System.out.println("Failed");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new MySQLProductDAO();
    }
}
