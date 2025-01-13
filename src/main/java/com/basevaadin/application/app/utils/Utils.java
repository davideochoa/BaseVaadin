package com.basevaadin.application.app.utils;

import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.data.entity.RolEntity;
import com.basevaadin.application.app.data.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
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
        dto.setEsReseteadoPassword(usuarioEntity.getEsReseteadoPassword());

        // Convertir roles a DTOs
        if (usuarioEntity.getRoles() != null) {
            dto.setRoles(usuarioEntity.getRoles().stream()
                    .map(Utils::toDTO) // Convierte cada RolEntity a RolDTO
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static List<UsuarioDTO> convertToDTOList(List<UsuarioEntity> usuarioEntities) {
        return usuarioEntities.stream()
                .map(Utils::toDTO) // Llama al método estático de conversión
                .collect(Collectors.toList()); // Recolecta el resultado en una lista
    }
}
