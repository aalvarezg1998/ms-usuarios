package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.RolesEntity;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IRolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolesJpaAdapterTest {

    @Mock
    private IRolRepository rolRepository;

    private RolesJpaAdapter rolesJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolesJpaAdapter = new RolesJpaAdapter(rolRepository);
    }

    @Test
    void getByNombre_shouldReturnRol_whenRolExists() {
        // Arrange
        String nombreRol = "ADMIN";
        RolesEntity mockRolEntity = new RolesEntity();
        mockRolEntity.setId(1L);
        mockRolEntity.setNombre(nombreRol);

        Rol expectedRol = new Rol();
        expectedRol.setId(1L);
        expectedRol.setNombre(nombreRol);

        when(rolRepository.findByNombre(nombreRol)).thenReturn(Optional.of(mockRolEntity));

        // Act
        Rol actualRol = rolesJpaAdapter.getByNombre(nombreRol);

        // Assert
        assertNotNull(actualRol);
        assertEquals(expectedRol.getId(), actualRol.getId());
        assertEquals(expectedRol.getNombre(), actualRol.getNombre());
        verify(rolRepository, times(1)).findByNombre(nombreRol);
    }

    @Test
    void getByNombre_shouldThrowException_whenRolDoesNotExist() {
        // Arrange
        String nombreRol = "NON_EXISTENT_ROLE";
        when(rolRepository.findByNombre(nombreRol)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> rolesJpaAdapter.getByNombre(nombreRol));
        verify(rolRepository, times(1)).findByNombre(nombreRol);
    }

}