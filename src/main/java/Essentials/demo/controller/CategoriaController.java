package Essentials.demo.controller;

import org.springframework.ui.Model;
import Essentials.demo.domain.Categoria;
import Essentials.demo.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        System.out.println("inicio() method called");
        var categorias = categoriaService.getCategorias(false);
        log.info("Categories fetched: {}", categorias);
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getId()
                    + ", Category: " + categoria.getCategory()
                    + ", Description: " + categoria.getDescription());
        }
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{id}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }

}
