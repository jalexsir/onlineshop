package dao.mysqlDAO;

import dao.DAOFactory.ProductDAO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductDAO implements ProductDAO {

    MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
    private final static String SELECT_PRODUCT_BY_CATEGORY = "SELECT * FROM products WHERE category = ?";
    private final static String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final static String SELECT_ALL_PRODUCTS = "SELECT * FROM products";


    @Override
    public List<Product> getAllProducts() {
        ResultSet rs = null;
        Connection connection = mySQLDAOFactory.createConnection();

        ArrayList<Product> list = new ArrayList<>();
        try (Statement selectStmt = connection.createStatement()) {
            rs = selectStmt.executeQuery(SELECT_ALL_PRODUCTS);
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("price"),
                        rs.getInt("category")));
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
    public List<Product> getProductsByCategory(int cat) {
        ResultSet rs = null;
        Connection connection = mySQLDAOFactory.createConnection();

        ArrayList<Product> list = new ArrayList<>();
        try (PreparedStatement prepSt = connection.prepareStatement(SELECT_PRODUCT_BY_CATEGORY)) {
            prepSt.setInt(1, cat);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("price"),
                        rs.getInt("category")));
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
        }
        if (connection != null) {
            mySQLDAOFactory.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Product getProductById(int id) {
        ResultSet rs = null;
        Product product = null;
        Connection connection = mySQLDAOFactory.createConnection();
        try (PreparedStatement prepSt = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("price"),
                        rs.getInt("category"));
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
        return product;
    }
}
