package Essentials.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    private String category;
    private String description;
    
    @OneToMany
    @JoinColumn(name="CategoryId")
    List<Product> productos;

    public Categoria() {
    }

    public Categoria(String category, String description) {
        this.category = category;
        this.description = description;
    }
}