package dao.DAOFactory;

import entity.Product;


import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProducts();

    public List<Product> getProductsByCategory(int cat);

    public Product getProductById(int id);
}
