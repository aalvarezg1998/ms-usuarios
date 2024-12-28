package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_usuarios.domain.model.Usuario;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.UsuariosEntity;

public class UsurioEntityMapper {

    private UsurioEntityMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static UsuariosEntity toEntity(Usuario usuario){
        UsuariosEntity usuariosEntity = new UsuariosEntity();
        usuariosEntity.setId(usuario.getId());
        usuariosEntity.setNombre(usuario.getNombre());
        usuariosEntity.setApellido(usuario.getApellido());
        usuariosEntity.setNumeroDocumento(usuario.getNumeroDocumento());
        usuariosEntity.setCorreo(usuario.getCorreo());
        usuariosEntity.setCelular(usuario.getCelular());
        usuariosEntity.setClave(usuario.getClave());
        usuariosEntity.setFechaNacimiento(usuario.getFechaNacimiento());
        usuariosEntity.setIdRol(usuario.getIdRol());
        return usuariosEntity;
    }

    public static Usuario toUsuario(UsuariosEntity usuariosEntity){
        Usuario usuario = new Usuario();
        usuario.setId(usuariosEntity.getId());
        usuario.setApellido(usuariosEntity.getApellido());
        usuario.setNombre(usuariosEntity.getNombre());
        usuario.setCorreo(usuariosEntity.getCorreo());
        usuario.setCelular(usuariosEntity.getCelular());
        usuario.setClave(usuariosEntity.getClave());
        usuario.setFechaNacimiento(usuariosEntity.getFechaNacimiento());
        usuario.setNumeroDocumento(usuariosEntity.getNumeroDocumento());
        usuario.setIdRol(usuariosEntity.getIdRol());
        return usuario;
    }
}
