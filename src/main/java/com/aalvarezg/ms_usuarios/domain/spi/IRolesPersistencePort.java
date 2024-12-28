package com.aalvarezg.ms_usuarios.domain.spi;

import com.aalvarezg.ms_usuarios.domain.model.Rol;

public interface IRolesPersistencePort {
    Rol getByNombre(String nombreRol);
}
