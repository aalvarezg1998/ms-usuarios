package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.api.IUsuarioServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import com.aalvarezg.ms_usuarios.domain.spi.IUsuaioPersistencePort;
import com.aalvarezg.ms_usuarios.domain.spi.IPasswordEncoderPort;


public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuaioPersistencePort usuaioPersistencePort;
    private final IPasswordEncoderPort passwordEncoder;

    public UsuarioUseCase(IUsuaioPersistencePort usuaioPersistencePort, IPasswordEncoderPort passwordEncoder) {
        this.usuaioPersistencePort = usuaioPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String encodePassword = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(encodePassword);
        return this.usuaioPersistencePort.saveUsuario(usuario);
    }
}
