package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.domain.spi.IRolesPersistencePort;
import com.aalvarezg.ms_usuarios.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.mapper.RolesEntityMapper;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolesJpaAdapter implements IRolesPersistencePort {
    private final IRolRepository rolRepository;

    @Override
    public Rol getByNombre(String nombreRol) {
        return rolRepository.findByNombre(nombreRol)
                .map(RolesEntityMapper::toRol)
                .orElseThrow(NoDataFoundException::new);
    }
}
