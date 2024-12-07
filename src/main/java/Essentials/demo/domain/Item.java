
package Essentials.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Product {
    private int cantidad; //Almacenar la cantidad de items de un producto

    public Item() {
    }

    public Item(Product producto) {
        super.setId(producto.getId());
        super.setCategory(producto.getCategory());
        super.setName(producto.getName());
        super.setDescription(producto.getDescription());
        super.setPrice(producto.getPrice());
        super.setQuantity(producto.getQuantity());
        super.setActive(producto.isActive());
        this.cantidad = 0;
    }
}