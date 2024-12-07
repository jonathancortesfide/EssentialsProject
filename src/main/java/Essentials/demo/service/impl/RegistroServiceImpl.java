package Essentials.demo.service.impl;

import Essentials.demo.domain.Usuario;
import Essentials.demo.service.RegistroService;
import Essentials.demo.service.UsuarioService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;  //creado en semana 4...

    @Override
    public Model activar(Model model, String username, String clave) {
        Usuario usuario = 
                usuarioService.getUsuarioPorUsernameYPassword(username, 
                        clave);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute(
                    "titulo", 
                    messageSource.getMessage(
                            "registro.activar", 
                            null,  Locale.getDefault()));
            model.addAttribute(
                    "mensaje", 
                    messageSource.getMessage(
                            "registro.activar.error", 
                            null, Locale.getDefault()));
        }
        return model;
    }

    @Override
    public void activar(Usuario usuario, MultipartFile imagenFile) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));

        if (!imagenFile.isEmpty()) {
            usuario.setActive(true);
        }
        usuarioService.save(usuario, true);
    }

    @Override
    public Model crearUsuario(Model model, Usuario usuario){
        String mensaje;
        if (!usuarioService.
                existeUsuarioPorUsernameOEmail(
                        usuario.getUsername(), 
                        usuario.getEmail())) {
            String clave = demeClave();
            usuario.setPassword(clave);
            usuario.setActive(false);
            usuarioService.save(usuario, true);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.activacion.ok", 
                            null, 
                            Locale.getDefault()),
                    usuario.getEmail());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.email", 
                            null, 
                            Locale.getDefault()),
                    usuario.getUsername(), usuario.getEmail());
        }
        model.addAttribute(
                "titulo", 
                messageSource.getMessage(
                        "registro.activar", 
                        null, 
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje", 
                mensaje);
        return model;
    }

    @Override
    public Model recordarUsuario(Model model, Usuario usuario){
        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOEmail(
                usuario.getUsername(), 
                usuario.getEmail());
        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setPassword(clave);
            usuario2.setActive(false);
            usuarioService.save(usuario2, false);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.recordar.ok", 
                            null, 
                            Locale.getDefault()),
                    usuario.getEmail());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo", 
                            null, 
                            Locale.getDefault()),
                    usuario.getUsername(), usuario.getEmail());
        }
        model.addAttribute(
                "titulo", 
                messageSource.getMessage(
                        "registro.activar", 
                        null, 
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje", 
                mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }

    //Ojo cómo le lee una informacion del application.properties
    // @Value("${servidor.http}")
    private String servidor;

    private void enviaCorreoActivar(Usuario usuario, String clave){
        String mensaje = messageSource.getMessage(
                "registro.correo.activar", 
                null, Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getName(), 
                usuario.getLastName(), servidor, 
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.activacion", 
                null, Locale.getDefault());
    }

    private void enviaCorreoRecordar(Usuario usuario, String clave){
        String mensaje = messageSource.getMessage(""
                + "registro.correo.recordar", 
                null, 
                Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getName(), 
                usuario.getLastName(), servidor, 
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.recordar", 
                null, Locale.getDefault());
    }
}