package com.basevaadin.application.data.DTO;

import com.basevaadin.application.data.entity.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombrePropio;
    private String correo;
    private String nombreUsuario;
    private Set<RolEntity> roles;
    private Boolean esReseteadoPassword;
}
