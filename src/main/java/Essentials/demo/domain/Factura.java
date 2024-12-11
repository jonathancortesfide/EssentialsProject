package Essentials.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="Invoice")
public class Factura implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private Long customerId;
    private Date date;
    private double total;
    
    
    public Factura() {
    }

    public Factura(Long idUSuario) {
        this.customerId = idUSuario;
        this.date = Calendar.getInstance().getTime();
    }    
}