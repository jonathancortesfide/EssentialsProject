package Essentials.demo.service;

import Essentials.demo.domain.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {

    public Model activar(Model model, String usuario, String clave);

    public Model crearUsuario(Model model, Usuario usuario);
    
    public void activar(Usuario usuario, MultipartFile imagenFile);
    
    public Model recordarUsuario(Model model, Usuario usuario);
}
