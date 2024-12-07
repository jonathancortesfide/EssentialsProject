package Essentials.demo.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long idUsuario;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String image;
    private boolean active;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Rol> roles;
}
