package main.models.services;

import main.models.DAO.ProductDAO;
import main.models.DAO.ProductInterface;
import main.models.pojo.Product;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Обработка товаров
 */
public class ProductService implements ProductServiceInterface {
    private static ProductInterface productDAO = new ProductDAO();

    @Override
    public Collection<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public Product getByID(Long id) {
        return productDAO.getByID(id);
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void deleteByID(Long id) {
        productDAO.deleteByID(id);
    }
}
