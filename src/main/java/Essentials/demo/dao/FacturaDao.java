package Essentials.demo.dao;

import Essentials.demo.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository <Factura,Long> {
     
}