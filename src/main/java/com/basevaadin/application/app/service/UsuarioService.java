package com.basevaadin.application.app.service;

import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.facade.UsuarioFacade;
import com.basevaadin.application.app.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioFacade usuarioFacade;

    public UsuarioService(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<UsuarioDTO> findAllByOrderByNombrePropioAsc() {
        return Utils.convertToDTOList(usuarioFacade.findAllByOrderByNombrePropioAsc(), Utils::toDTO);
    }

    public void save(UsuarioDTO usuarioDTO) {
        if(usuarioDTO.getId() == null || usuarioDTO.getId() == 0) {
            usuarioDTO.setEsReseteadoPassword(true);
            usuarioDTO.setPassword(usuarioDTO.getNombreUsuario());
        }
        usuarioFacade.save(Utils.toEntity(usuarioDTO));
    }
}
