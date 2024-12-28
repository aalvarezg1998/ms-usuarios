package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import com.aalvarezg.ms_usuarios.infrastructure.exception.UsuarioAlreadyExitsException;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.UsuariosEntity;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioJpaAdapterTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    private UsuarioJpaAdapter usuarioJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioJpaAdapter = new UsuarioJpaAdapter(usuarioRepository);
    }

    @Test
    void saveUsuario_shouldSaveUsuario_whenUsuarioDoesNotExist() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNumeroDocumento("12345678");
        usuario.setCorreo("test@example.com");
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        UsuariosEntity usuariosEntity = new UsuariosEntity();
        usuariosEntity.setId(1L);
        usuariosEntity.setNumeroDocumento("12345678");
        usuariosEntity.setCorreo("test@example.com");
        usuariosEntity.setNombre("Juan");
        usuariosEntity.setApellido("Perez");

        when(usuarioRepository.findByNumeroDocumentoOrCorreo(usuario.getNumeroDocumento(), usuario.getCorreo()))
                .thenReturn(Optional.empty());
        when(usuarioRepository.save(Mockito.any(UsuariosEntity.class))).thenReturn(usuariosEntity);

        // Act
        Usuario savedUsuario = usuarioJpaAdapter.saveUsuario(usuario);

        // Assert
        assertNotNull(savedUsuario);
        assertEquals(usuario.getNumeroDocumento(), savedUsuario.getNumeroDocumento());
        assertEquals(usuario.getCorreo(), savedUsuario.getCorreo());
        verify(usuarioRepository, times(1))
                .findByNumeroDocumentoOrCorreo(usuario.getNumeroDocumento(), usuario.getCorreo());
        verify(usuarioRepository, times(1)).save(any(UsuariosEntity.class));
    }

    @Test
    void saveUsuario_shouldThrowException_whenUsuarioAlreadyExists() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNumeroDocumento("12345678");
        usuario.setCorreo("test@example.com");
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        UsuariosEntity existingUsuarioEntity = new UsuariosEntity();
        existingUsuarioEntity.setId(1L);
        existingUsuarioEntity.setNumeroDocumento("12345678");
        existingUsuarioEntity.setCorreo("test@example.com");

        when(usuarioRepository.findByNumeroDocumentoOrCorreo(usuario.getNumeroDocumento(), usuario.getCorreo()))
                .thenReturn(Optional.of(existingUsuarioEntity));

        // Act & Assert
        assertThrows(UsuarioAlreadyExitsException.class, () -> usuarioJpaAdapter.saveUsuario(usuario));
        verify(usuarioRepository, times(1))
                .findByNumeroDocumentoOrCorreo(usuario.getNumeroDocumento(), usuario.getCorreo());
        verify(usuarioRepository, never()).save(any(UsuariosEntity.class));
    }

}