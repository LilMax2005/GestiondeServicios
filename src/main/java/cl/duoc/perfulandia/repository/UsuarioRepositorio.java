package cl.duoc.perfulandia.repository;

import cl.duoc.perfulandia.service.dominio.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UsuarioRepositorio {
    private static final List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.addAll(
                List.of(
                        Usuario.builder()
                                .nombre("Juan")
                                .apellido("Perez")
                                .email("juanperez@gmail.com")
                                .telefono("999769992")
                                .id(Long.valueOf("1"))
                                .build(),
                        Usuario.builder()
                                .nombre("Rodrigo")
                                .apellido("Rojas")
                                .email("r.rojas@gmail.com")
                                .telefono("956434211")
                                .id(Long.valueOf("2"))
                                .build(),
                        Usuario.builder()
                                .nombre("Brayan")
                                .apellido("Maldonado")
                                .email("mb1877@gmail.com")
                                .telefono("998552313")
                                .id(Long.valueOf("3"))
                                .build(),
                        Usuario.builder()
                                .nombre("Jorge")
                                .apellido("Valdes")
                                .email("j.valdes.j@gmail.com")
                                .telefono("908671020")
                                .id(Long.valueOf("4"))
                                .build(),
                        Usuario.builder()
                                .nombre("Ramiro")
                                .apellido("Prato")
                                .email("prato.ramiro@gmail.com")
                                .telefono("912993461")
                                .id(Long.valueOf("5"))
                                .build()
                )
        );

    }

    public static List<Usuario> findAll() {
        return usuarios;
    }

    public static Usuario findByid(Long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void actualizarUsuario(Usuario toReplace, Usuario request) {
        int index = usuarios.indexOf(toReplace);
        usuarios.set(index, request);
    }

    public static void eliminarUsuario(Usuario encontrado) {
        usuarios.remove(encontrado);
    }
}
