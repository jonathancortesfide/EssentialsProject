package Essentials.demo.service;

import Essentials.demo.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    // Se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);
    
    // Get a Category, based on the ID of a category
    public Categoria getCategoria(Categoria categoria);

    // Insert a new category if the category ID is empty
    // Update a category if the category ID is NOT empty
    public void save(Categoria categoria);

    // Delete the category that has the ID passed as a parameter
    public void delete(Categoria categoria);
 
}