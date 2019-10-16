package dao.DAOFactory;

import dao.mysqlDAO.MySQLDAOFactory;

import java.sql.Connection;

public abstract class DAOFactory {
    public abstract UserDAO getUserDAO();

    public abstract ProductDAO getProductDAO();

    public abstract Connection createConnection();

    public abstract void closeConnection(Connection connection);

    public static DAOFactory getInstance(int type) {
        switch (type) {
            case 1:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
