package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import com.aalvarezg.ms_usuarios.domain.spi.IPasswordEncoderPort;
import com.aalvarezg.ms_usuarios.domain.spi.IUsuaioPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioUseCaseTest {

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    @Mock
    private IUsuaioPersistencePort usuaioPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsuario_ShouldEncodePasswordAndSaveUser() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setNumeroDocumento("123456789");
        usuario.setCorreo("test@example.com");
        usuario.setClave("miPassword123");

        when(passwordEncoder.encode(usuario.getClave())).thenReturn("encodedPassword");
        when(usuaioPersistencePort.saveUsuario(usuario)).thenReturn(usuario);

        // When
        Usuario savedUsuario = usuarioUseCase.saveUsuario(usuario);

        // Then
        assertNotNull(savedUsuario);
        assertEquals("encodedPassword", savedUsuario.getClave());
        verify(passwordEncoder).encode("miPassword123");
        verify(usuaioPersistencePort).saveUsuario(usuario);
    }

}