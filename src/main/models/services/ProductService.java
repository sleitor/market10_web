package main.models.services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.DAO.ProductInterface;
import main.models.entity.EntProduct;
import main.models.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Обработка товаров
 */

@Service
public class ProductService implements ProductServiceInterface {

    private ProductInterface productDAO;

    @Autowired
    public ProductService(ProductInterface productDAO) {
        this.productDAO = productDAO;
    }

    public ProductService() {

    }

    @Override
    public Collection<Product> getAll() {

        List<EntProduct> entProducts = (List<EntProduct>) productDAO.getAll();

        entProducts.sort(Comparator.comparingLong(EntProduct::getUuid));

        List<Product> products = new ArrayList<>();

        MapperFactory factory = new DefaultMapperFactory.Builder().build();

        BoundMapperFacade<EntProduct, Product> boundMapperFacade = factory.getMapperFacade(EntProduct.class, Product.class);

        entProducts.forEach(studentEntity -> products.add(boundMapperFacade.map(studentEntity)));

        return products;

//        return productDAO.getAll();
        //throw new NotImplementedException();
    }

    @Override
    public Product getByID(Long id) {
//        return productDAO.getByID(id);
        throw new NotImplementedException();
    }

    @Override
    public int create(Product product) {
//        return productDAO.create(product);
        throw new NotImplementedException();
    }

    @Override
    public void update(Product product) {
//        productDAO.update(product);
        throw new NotImplementedException();
    }

    @Override
    public void deleteByID(Long id) {
        productDAO.deleteByID(id);
    }
}
