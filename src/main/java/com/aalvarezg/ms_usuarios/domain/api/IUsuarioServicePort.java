package com.aalvarezg.ms_usuarios.domain.api;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;

public interface IUsuarioServicePort {
    Usuario saveUsuario(Usuario usuario);
}
