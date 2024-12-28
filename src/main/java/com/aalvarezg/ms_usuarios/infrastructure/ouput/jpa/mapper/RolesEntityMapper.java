package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_usuarios.domain.model.Rol;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity.RolesEntity;

public class RolesEntityMapper {
    private RolesEntityMapper(){
        throw new IllegalStateException("Mapper para roles");
    }

    public static RolesEntity toEntity(Rol rol){
        RolesEntity rolesEntity =  new RolesEntity();
        rolesEntity.setId(rol.getId());
        rolesEntity.setNombre(rol.getNombre());
        rolesEntity.setDescripcion(rol.getDescripcion());
        return rolesEntity;
    }

    public static Rol toRol(RolesEntity rolesEntity){
        Rol rol =  new Rol();
        rol.setId(rolesEntity.getId());
        rol.setNombre(rolesEntity.getNombre());
        rol.setDescripcion(rolesEntity.getDescripcion());
        return rol;
    }

}
