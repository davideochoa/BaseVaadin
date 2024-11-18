package com.adminencuesta360.application.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombrePropio;
    private String correo;
    private String nombreUsuario;
    private Boolean esAdministrador;
    private Boolean esReseteadoPassword;
}
