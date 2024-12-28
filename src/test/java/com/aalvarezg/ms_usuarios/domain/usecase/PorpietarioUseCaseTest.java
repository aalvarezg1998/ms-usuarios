package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.api.IUsuarioServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PorpietarioUseCaseTest {
    @InjectMocks
    private PorpietarioUseCase propietarioUseCase;
    @Mock
    private IUsuarioServicePort usuarioServicePort;

    @BeforeEach
    public void setUp() {
        usuarioServicePort = mock(IUsuarioServicePort.class);
        propietarioUseCase = new PorpietarioUseCase(usuarioServicePort);
    }

    @Test
    void testCrearPropietario_ShouldReturnSavedUsuario_WhenValid() {
        // Given
        Usuario validUsuario = new Usuario();
        validUsuario.setNumeroDocumento("123456789");
        validUsuario.setCorreo("test@example.com");
        validUsuario.setCelular("+1234567890");
        validUsuario.setFechaNacimiento(LocalDate.now().minusYears(20));

        when(usuarioServicePort.saveUsuario(validUsuario)).thenReturn(validUsuario);

        // When
        Usuario result = propietarioUseCase.crearPropietario(validUsuario);

        // Then
        assertNotNull(result);
        assertEquals(validUsuario, result);
        verify(usuarioServicePort).saveUsuario(validUsuario);
    }

    @Test
    void testCrearPropietario_ShouldThrowException_WhenNotAdult() {
        // Given
        Usuario notAdultUsuario = new Usuario();
        notAdultUsuario.setNumeroDocumento("123456789");
        notAdultUsuario.setCorreo("test@example.com");
        notAdultUsuario.setCelular("+1234567890");
        notAdultUsuario.setFechaNacimiento(LocalDate.now().minusYears(16)); // Under 18

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propietarioUseCase.crearPropietario(notAdultUsuario);
        });
        assertEquals("El usuario debe ser mayor de edad.", exception.getMessage());
    }

    @Test
    void testCrearPropietario_ShouldThrowException_WhenDocumentoInvalido() {
        // Given
        Usuario invalidDocumentoUsuario = new Usuario();
        invalidDocumentoUsuario.setNumeroDocumento("abc123");
        invalidDocumentoUsuario.setCorreo("test@example.com");
        invalidDocumentoUsuario.setCelular("+1234567890");
        invalidDocumentoUsuario.setFechaNacimiento(LocalDate.now().minusYears(20));

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propietarioUseCase.crearPropietario(invalidDocumentoUsuario);
        });
        assertEquals("El documento de identidad no es válido.", exception.getMessage());
    }

    @Test
    void testCrearPropietario_ShouldThrowException_WhenEmailInvalido() {
        // Given
        Usuario invalidEmailUsuario = new Usuario();
        invalidEmailUsuario.setNumeroDocumento("123456789");
        invalidEmailUsuario.setCorreo("invalid-email");
        invalidEmailUsuario.setCelular("+1234567890");
        invalidEmailUsuario.setFechaNacimiento(LocalDate.now().minusYears(20));

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propietarioUseCase.crearPropietario(invalidEmailUsuario);
        });
        assertEquals("El correo no tiene un formato válido.", exception.getMessage());
    }

    @Test
    void testCrearPropietario_ShouldThrowException_WhenTelefonoInvalido() {
        // Given
        Usuario invalidTelefonoUsuario = new Usuario();
        invalidTelefonoUsuario.setNumeroDocumento("123456789");
        invalidTelefonoUsuario.setCorreo("test@example.com");
        invalidTelefonoUsuario.setCelular("invalid-phone");
        invalidTelefonoUsuario.setFechaNacimiento(LocalDate.now().minusYears(20));

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propietarioUseCase.crearPropietario(invalidTelefonoUsuario);
        });
        assertEquals("El teléfono no es válido.", exception.getMessage());
    }

}