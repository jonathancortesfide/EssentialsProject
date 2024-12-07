package Essentials.demo.controller;

import org.springframework.ui.Model;
import Essentials.demo.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import Essentials.demo.service.ProductService;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var products = productService.getProducts(false);
        for (Product prod : products) {
            System.out.println("ID: " + prod.getId()
                    + ", Name: " + prod.getName());
        }
        model.addAttribute("products", products);
        model.addAttribute("totalProducts", products.size());
        return "/product/listado";
    }

    @GetMapping("/nuevo")
    public String productNuevo(Product product) {
        return "/product/modifica";
    }

    @PostMapping("/guardar")
    public String productGuardar(Product product) {
        productService.save(product);
        return "redirect:/product/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String productEliminar(Product product) {
        productService.delete(product);
        return "redirect:/product/listado";
    }

    @GetMapping("/modificar/{id}")
    public String productModificar(Product product, Model model) {
        product = productService.getProduct(product);
        model.addAttribute("product", product);
        return "/product/modifica";
    }
}
