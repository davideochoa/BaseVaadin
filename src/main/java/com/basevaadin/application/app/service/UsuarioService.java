package com.basevaadin.application.app.service;

import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.data.entity.RolEntity;
import com.basevaadin.application.app.data.entity.UsuarioEntity;
import com.basevaadin.application.app.facade.UsuarioFacade;
import com.basevaadin.application.app.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public UsuarioDTO findByNombreUsuarioAndPassword(String nombreUsuario, String password) {
        return Utils.toDTO(usuarioFacade.findByNombreUsuarioAndPassword(nombreUsuario, password).get());
    }

    public List<UsuarioDTO> findAllByOrderByNombrePropioAsc() {
        return Utils.convertToDTOList(usuarioFacade.findAllByOrderByNombrePropioAsc());
    }

    public UsuarioEntity guardarUsuarioConRoles(UsuarioEntity usuario, List<String> nombresRoles){
        return usuarioFacade.guardarUsuarioConRoles(usuario,nombresRoles);
    }

}
