package com.aalvarezg.ms_usuarios.application.mapper;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static PropietarioResponseDTO usuaioToPropietarioRequest(Usuario usuario) {
        PropietarioResponseDTO propietarioResponseDTO = new PropietarioResponseDTO();
        propietarioResponseDTO.setNombre(usuario.getNombre());
        propietarioResponseDTO.setApellido(usuario.getApellido());
        propietarioResponseDTO.setCelular(usuario.getCelular());
        propietarioResponseDTO.setCorreo(usuario.getCorreo());
        propietarioResponseDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        return propietarioResponseDTO;
    }

    public static Usuario propietarioRequestToUsuario(PropietarioRequestDTO propietarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombre(propietarioRequest.getNombre());
        usuario.setApellido(propietarioRequest.getApellido());
        usuario.setClave(propietarioRequest.getClave());
        usuario.setCelular(propietarioRequest.getCelular());
        usuario.setNumeroDocumento(propietarioRequest.getNumeroDocumento());
        usuario.setFechaNacimiento(propietarioRequest.getFechaNacimiento());
        usuario.setCorreo(propietarioRequest.getCorreo());
        return usuario;
    }
}
