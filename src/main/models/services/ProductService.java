package main.models.services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.DAO.ProductInterface;
import main.models.entity.EntProduct;
import main.models.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Обработка товаров
 */

@Service
public class ProductService implements ProductServiceInterface {

    private static MapperFactory factory = new DefaultMapperFactory.Builder().build();

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

//        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<EntProduct, Product> boundMapperFacade = factory.getMapperFacade(EntProduct.class, Product.class);

        entProducts.forEach(studentEntity -> products.add(boundMapperFacade.map(studentEntity)));
        return products;

//        return productDAO.getAll();
        //throw new NotImplementedException();
    }

    @Override
    public Product getByID(Long id) {

        EntProduct entProduct = productDAO.getByID(id);
        BoundMapperFacade<EntProduct, Product> boundMapperFacade = factory.getMapperFacade(EntProduct.class, Product.class);

        return boundMapperFacade.map(entProduct);
    }

    @Override
    public int create(Product product) {

        BoundMapperFacade<Product, EntProduct> boundMapperFacade = factory.getMapperFacade(Product.class, EntProduct.class);
        EntProduct entProduct = boundMapperFacade.map(product);
        return productDAO.create(entProduct);
    }

    @Override
    public void update(Product product) {
        BoundMapperFacade<Product, EntProduct> boundMapperFacade = factory.getMapperFacade(Product.class, EntProduct.class);
        productDAO.update(boundMapperFacade.map(product));

    }

    @Override
    public void deleteByID(Long id) {
        productDAO.deleteByID(id);
    }
}
