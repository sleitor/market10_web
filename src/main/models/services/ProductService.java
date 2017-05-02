package main.models.services;

import main.models.DAO.ProductInterface;
import main.models.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Обработка товаров
 */

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductInterface productDAO;

    @Override
    public Collection<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public Product getByID(Long id) {
        return productDAO.getByID(id);
    }

    @Override
    public int create(Product product) {
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
