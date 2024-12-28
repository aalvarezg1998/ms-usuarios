package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.api.IRolesServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.domain.spi.IRolesPersistencePort;

public class RolesUseCase implements IRolesServicePort {
    private final IRolesPersistencePort rolesPersistencePort;

    public RolesUseCase(IRolesPersistencePort rolesPersistencePort) {
        this.rolesPersistencePort = rolesPersistencePort;
    }

    @Override
    public Rol getByNombre(String nombreRol) {
        return rolesPersistencePort.getByNombre(nombreRol);
    }
}
