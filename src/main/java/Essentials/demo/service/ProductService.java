package Essentials.demo.service;

import Essentials.demo.domain.Product;
import java.util.List;

public interface ProductService {
    
    // Se obtiene un listado de products en un List
    public List<Product> getProducts(boolean activos);
    
    // Get a Category, based on the ID of a category
    public Product getProduct(Product product);

    // Insert a new category if the category ID is empty
    // Update a category if the category ID is NOT empty
    public void save(Product product);

    // Delete the category that has the ID passed as a parameter
    public void delete(Product product);
 
}