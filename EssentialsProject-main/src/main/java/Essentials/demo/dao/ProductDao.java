package Essentials.demo.dao;

import Essentials.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository <Product,Long> {
    
}