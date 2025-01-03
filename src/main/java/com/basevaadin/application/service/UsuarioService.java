package com.basevaadin.application.service;

import com.basevaadin.application.data.DTO.UsuarioDTO;
import com.basevaadin.application.data.entity.UsuarioEntity;
import com.basevaadin.application.facade.UsuarioFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioFacade usuarioFacade;

    public UsuarioService(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    UsuarioEntity findByNombreUsuario(String username){
        return usuarioFacade.findByNombreUsuario(username);
    }

    public Optional<UsuarioDTO> findByNombreUsuarioAndPassword(String nombreUsuario, String password) {
        return usuarioFacade.findByNombreUsuarioAndPassword(nombreUsuario, password)
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombrePropio(),
                        usuario.getCorreo(),
                        usuario.getNombreUsuario(),
                        usuario.getEsAdministrador(),
                        usuario.getEsReseteadoPassword()
                ));
    }

    public List<UsuarioDTO> findAllByOrderByNombrePropioAsc() {
        List<UsuarioEntity> usuarios = usuarioFacade.findAllByOrderByNombrePropioAsc();

        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombrePropio(),
                        usuario.getCorreo(),
                        usuario.getNombreUsuario(),
                        usuario.getEsAdministrador(),
                        usuario.getEsReseteadoPassword()
                ))
                .collect(Collectors.toList());
    }

}
