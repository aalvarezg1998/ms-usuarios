package com.aalvarezg.ms_usuarios.application.handler;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;
import com.aalvarezg.ms_usuarios.application.mapper.UsuarioMapper;
import com.aalvarezg.ms_usuarios.domain.api.IPropietarioServicePort;
import com.aalvarezg.ms_usuarios.domain.api.IRolesServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PropietarioHandler implements IPropietarioHandler{
    public static final String ROL_PROPIETARIO = "PROPIETARIO";

    private final IPropietarioServicePort propietarioServicePort;
    private final IRolesServicePort rolesServicePort;
    @Override
    public PropietarioResponseDTO crearPropietario(PropietarioRequestDTO propietarioRequestDTO) {
        Usuario propietario = UsuarioMapper.propietarioRequestToUsuario(propietarioRequestDTO);
        Rol rolPropietario = rolesServicePort.getByNombre(ROL_PROPIETARIO);
        propietario.setIdRol(rolPropietario.getId());
        Usuario propietarioGuardado = propietarioServicePort.crearPropietario(propietario);
        return UsuarioMapper.usuaioToPropietarioRequest(propietarioGuardado);
    }
}
