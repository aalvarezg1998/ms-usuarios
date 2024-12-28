package com.aalvarezg.ms_usuarios.domain.spi;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;

public interface IUsuaioPersistencePort {
    Usuario saveUsuario(Usuario usuario);
}
