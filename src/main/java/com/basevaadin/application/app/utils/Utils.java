package com.basevaadin.application.app.utils;

import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.data.entity.RolEntity;
import com.basevaadin.application.app.data.entity.UsuarioEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static UsuarioEntity toEntity(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setId(usuarioDTO.getId());
        usuarioEntity.setNombrePropio(usuarioDTO.getNombrePropio());
        usuarioEntity.setCorreo(usuarioDTO.getCorreo());
        usuarioEntity.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuarioEntity.setPassword(usuarioDTO.getPassword());
        usuarioEntity.setEsReseteadoPassword(usuarioDTO.getEsReseteadoPassword());

        // Convertir roles
        if (usuarioDTO.getRoles() != null) {
            usuarioEntity.setRoles(usuarioDTO.getRoles().stream()
                    .map(Utils::toEntity) // Convertir RolDTO a RolEntity
                    .collect(Collectors.toSet()));
        }

        return usuarioEntity;
    }

    public static RolEntity toEntity(RolDTO rolDTO) {
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(rolDTO.getId());
        rolEntity.setNombre(rolDTO.getNombre());
        rolEntity.setDescripcion(rolDTO.getDescripcion());
        return rolEntity;
    }

    public static <T, U> List<U> convertToDTOList(List<T> entities, Function<T, U> mapper) {
        return entities.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static RolDTO toDTO(RolEntity rolEntity) {
        return new RolDTO(
                rolEntity.getId(),
                rolEntity.getNombre(),
                rolEntity.getDescripcion()
        );
    }

    public static UsuarioDTO toDTO(UsuarioEntity usuarioEntity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuarioEntity.getId());
        dto.setNombrePropio(usuarioEntity.getNombrePropio());
        dto.setCorreo(usuarioEntity.getCorreo());
        dto.setNombreUsuario(usuarioEntity.getNombreUsuario());
        dto.setPassword(usuarioEntity.getPassword());
        dto.setEsReseteadoPassword(usuarioEntity.getEsReseteadoPassword());

        // Convertir roles a DTOs
        if (usuarioEntity.getRoles() != null) {
            dto.setRoles(usuarioEntity.getRoles().stream()
                    .map(Utils::toDTO) // Convierte cada RolEntity a RolDTO
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

}
