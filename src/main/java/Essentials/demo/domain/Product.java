package Essentials.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="Product")
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private boolean active;
    @Column(name="image")   
    private String image;
    
    @ManyToOne
    @JoinColumn(name="CategoryId")
    Categoria category;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, int quantity, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
    }

    
}