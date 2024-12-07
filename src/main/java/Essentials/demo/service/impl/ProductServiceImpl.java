package Essentials.demo.service.impl;

import Essentials.demo.domain.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Essentials.demo.dao.ProductDao;
import Essentials.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts(boolean activos) {
        var lista = productDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Product product) {
        return productDao.findById(product.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productDao.delete(product);
    }
}
