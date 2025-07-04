package cl.duoc.perfulandia.service;


import cl.duoc.perfulandia.repository.UsuarioRepositorio;
import cl.duoc.perfulandia.service.dominio.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    public List<Usuario> getUsuario() {
        return UsuarioRepositorio.findAll();
    }

    public Usuario getUsuario(Long id) {
        return UsuarioRepositorio.findByid(id);
    }

    public boolean agregarUsuario(Usuario usuario) {
        Long id = usuario.getId();
        Usuario encontrado = UsuarioRepositorio.findByid(id);
        if (encontrado != null) {
            return false;
        }
        UsuarioRepositorio.agregarUsuario(usuario);
        return true;
    }

    public boolean reemplazarUsuario(Long id, Usuario request) {
        Usuario encontrado = UsuarioRepositorio.findByid(id);
        if (encontrado == null) {
            return false;
        }
        UsuarioRepositorio.actualizarUsuario(encontrado, request);
        return true;
    }

    public boolean eliminarUsuario(Long id) {
        Usuario encontrado = UsuarioRepositorio.findByid(id);
        if (encontrado == null) {
            return false;
        }
        UsuarioRepositorio.eliminarUsuario(encontrado);
        return true;
    }
}
