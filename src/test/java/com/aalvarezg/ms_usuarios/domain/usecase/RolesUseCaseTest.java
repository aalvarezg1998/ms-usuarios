package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.domain.spi.IRolesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RolesUseCaseTest {
    @InjectMocks
    private RolesUseCase rolesUseCase;
    @Mock
    private IRolesPersistencePort rolesPersistencePort;

    @BeforeEach
    public void setUp() {
        rolesPersistencePort = Mockito.mock(IRolesPersistencePort.class);
        rolesUseCase = new RolesUseCase(rolesPersistencePort);
    }

    @Test
    void testGetByNombre_ShouldReturnRole() {
        // Given
        String nombreRol = "PROPIETARIO";
        Rol expectedRole = new Rol();
        expectedRole.setNombre(nombreRol);
        when(rolesPersistencePort.getByNombre(nombreRol)).thenReturn(expectedRole);

        // When
        Rol actualRole = rolesUseCase.getByNombre(nombreRol);

        // Then
        assertEquals(expectedRole, actualRole);
        assertEquals(nombreRol, actualRole.getNombre());
        Mockito.verify(rolesPersistencePort).getByNombre(nombreRol);
    }
}