package com.basevaadin.application.app.service;

import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.facade.UsuarioFacade;
import com.basevaadin.application.app.utils.Utils;
import com.basevaadin.application.security.SecurityConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UsuarioService {

    private final SecurityConfiguration securityConfiguration;
    private final UsuarioFacade usuarioFacade;

    public UsuarioService(SecurityConfiguration securityConfiguration, UsuarioFacade usuarioFacade) {
        this.securityConfiguration = securityConfiguration;
        this.usuarioFacade = usuarioFacade;
    }

    public List<UsuarioDTO> findAllByOrderByNombrePropioAsc() {
        return Utils.convertToDTOList(usuarioFacade.findAllByOrderByNombrePropioAsc(), Utils::toDTO);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        PasswordEncoder passwordEncoder = securityConfiguration.passwordEncoder();

        if(usuarioDTO.getId() == null || usuarioDTO.getId() == 0) {
            usuarioDTO.setEsReseteadoPassword(true);
            usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getNombreUsuario()));
        }

        return Utils.toDTO(usuarioFacade.save(Utils.toEntity(usuarioDTO)));
    }
}
