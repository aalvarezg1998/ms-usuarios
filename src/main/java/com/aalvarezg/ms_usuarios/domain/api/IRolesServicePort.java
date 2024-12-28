package com.aalvarezg.ms_usuarios.domain.api;

import com.aalvarezg.ms_usuarios.domain.model.Rol;

public interface IRolesServicePort {
    Rol getByNombre(String nombreRol);
}
