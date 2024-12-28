package com.aalvarezg.ms_usuarios.application.handler;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;
import com.aalvarezg.ms_usuarios.domain.api.IPropietarioServicePort;
import com.aalvarezg.ms_usuarios.domain.api.IRolesServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropietarioHandlerTest {
    @InjectMocks
    private PropietarioHandler propietarioHandler;
    @Mock
    private IPropietarioServicePort propietarioServicePort;
    @Mock
    private IRolesServicePort rolesServicePort;

    @BeforeEach
    public void setUp() {
        propietarioServicePort = mock(IPropietarioServicePort.class);
        rolesServicePort = mock(IRolesServicePort.class);
        propietarioHandler = new PropietarioHandler(propietarioServicePort, rolesServicePort);
    }

    @Test
    void testCrearPropietario_ShouldReturnPropietarioResponseDTO_WhenValidInput() {
        // Given
        PropietarioRequestDTO requestDTO = new PropietarioRequestDTO();
        requestDTO.setNumeroDocumento("123456789");
        requestDTO.setCorreo("test@example.com");
        requestDTO.setCelular("+1234567890");
        requestDTO.setFechaNacimiento(LocalDate.parse("1990-01-01"));

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNumeroDocumento("123456789");
        usuario.setCorreo("test@example.com");
        usuario.setCelular("+1234567890");
        usuario.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        usuario.setIdRol(1L);

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("PROPIETARIO");

        when(rolesServicePort.getByNombre(PropietarioHandler.ROL_PROPIETARIO)).thenReturn(rol);
        when(propietarioServicePort.crearPropietario(any(Usuario.class))).thenReturn(usuario);

        // When
        PropietarioResponseDTO responseDTO = propietarioHandler.crearPropietario(requestDTO);

        // Then
        assertNotNull(responseDTO);
        assertEquals(usuario.getCorreo(), responseDTO.getCorreo());
        assertEquals(usuario.getCelular(), responseDTO.getCelular());
        verify(rolesServicePort).getByNombre(PropietarioHandler.ROL_PROPIETARIO);
        verify(propietarioServicePort).crearPropietario(any(Usuario.class));
    }

}