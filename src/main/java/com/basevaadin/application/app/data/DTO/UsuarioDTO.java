package com.basevaadin.application.app.data.DTO;

import com.basevaadin.application.app.data.entity.RolEntity;
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
    private Set<RolDTO> roles;
    private Boolean esReseteadoPassword;
}
