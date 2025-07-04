package cl.duoc.perfulandia.controller;

import cl.duoc.perfulandia.model.MessageResponse;
import cl.duoc.perfulandia.service.UsuarioServicio;
import cl.duoc.perfulandia.service.dominio.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.getUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario encontrado = usuarioServicio.getUsuario(id);
        if (encontrado != null) {
            return ResponseEntity.ok(encontrado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearUsuario(
            @RequestBody Usuario request) {
        boolean agregado = usuarioServicio.agregarUsuario(request);
        if (!agregado) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Error: Usuario ya existente."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Usuario creado."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> reemplazarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario request) {
        boolean reemplazado = usuarioServicio.reemplazarUsuario(id, request);
        if (!reemplazado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse("Usuario fue reemplazado con éxito."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioServicio.eliminarUsuario(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Usuario eliminado con éxito."));
    }

}

