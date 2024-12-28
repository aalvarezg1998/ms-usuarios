package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import com.aalvarezg.ms_usuarios.domain.spi.IUsuaioPersistencePort;
import com.aalvarezg.ms_usuarios.infrastructure.exception.UsuarioAlreadyExitsException;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.UsuariosEntity;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.mapper.UsurioEntityMapper;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuaioPersistencePort {
    private final IUsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        usuarioRepository.findByNumeroDocumentoOrCorreo(usuario.getNumeroDocumento(), usuario.getCorreo()).ifPresent(usuariosEntity -> {
            throw new UsuarioAlreadyExitsException();
        });
        UsuariosEntity usuariosEntity = usuarioRepository.save(UsurioEntityMapper.toEntity(usuario));
        return UsurioEntityMapper.toUsuario(usuariosEntity);
    }
}
