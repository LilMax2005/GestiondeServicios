package perfulandia.Test;

import cl.duoc.perfulandia.repository.UsuarioRepositorio;
import cl.duoc.perfulandia.service.UsuarioServicio;
import cl.duoc.perfulandia.service.dominio.Usuario;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TestUsuarioServicio {

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private MockedStatic<UsuarioRepositorio> repoMock;

    Usuario usuario1, usuario2;

    @BeforeEach
    void init() {
        repoMock = mockStatic(UsuarioRepositorio.class);

        usuario1 = new Usuario(); usuario1.setId(1L); usuario1.setNombre("Pedrito"); usuario1.setApellido("Lopez");
        usuario2 = new Usuario(); usuario2.setId(2L); usuario2.setNombre("Benja"); usuario2.setApellido("Silva");
    }

    @AfterEach
    void close() { repoMock.close(); }

    @Test
    void listarUsuarios() {
        repoMock.when(UsuarioRepositorio::findAll).thenReturn(List.of(usuario1, usuario2));

        assertThat(usuarioServicio.getUsuario()).hasSize(2);
        repoMock.verify(UsuarioRepositorio::findAll, times(1));
    }

    @Test
    void buscarUsuario() {
        repoMock.when(() -> UsuarioRepositorio.findByid(1L)).thenReturn(usuario1);

        assertThat(usuarioServicio.getUsuario(1L)).isSameAs(usuario1);
    }

    @Test
    void agregarUsuario() {
        repoMock.when(() -> UsuarioRepositorio.findByid(3L)).thenReturn(null);

        Usuario nuevo = new Usuario(); nuevo.setId(3L);
        assertThat(usuarioServicio.agregarUsuario(nuevo)).isTrue();
        repoMock.verify(() -> UsuarioRepositorio.agregarUsuario(nuevo));
    }

    @Test
    void agregarUsuarioDuplicado() {
        repoMock.when(() -> UsuarioRepositorio.findByid(1L)).thenReturn(usuario1);

        assertThat(usuarioServicio.agregarUsuario(usuario1)).isFalse();
        repoMock.verify(() -> UsuarioRepositorio.findByid(1L));
    }

    @Test
    void actualizarUsuario() {
        repoMock.when(() -> UsuarioRepositorio.findByid(1L)).thenReturn(usuario1);

        Usuario req = new Usuario(); req.setNombre("Ana+");
        assertThat(usuarioServicio.reemplazarUsuario(1L, req)).isTrue();
        repoMock.verify(() -> UsuarioRepositorio.actualizarUsuario(usuario1, req));
    }

    @Test
    void eliminarUsuario() {
        repoMock.when(() -> UsuarioRepositorio.findByid(9L)).thenReturn(null);

        assertThat(usuarioServicio.eliminarUsuario(9L)).isFalse();
        repoMock.verify(() -> UsuarioRepositorio.findByid(9L));
    }
}
