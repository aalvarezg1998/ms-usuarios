package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository;

import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByNombre(String nombreRol);
}
