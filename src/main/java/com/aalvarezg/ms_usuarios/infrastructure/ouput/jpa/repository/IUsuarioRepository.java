package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository;

import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<UsuariosEntity, Long> {
    Optional<UsuariosEntity> findByNumeroDocumentoOrCorreo(String numeroDocumento, String correo);
}
