package com.aalvarezg.ms_usuarios.application.handler;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;

public interface IPropietarioHandler {
    PropietarioResponseDTO crearPropietario(PropietarioRequestDTO porpietarioRequest);
}
