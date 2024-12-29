package com.basevaadin.application.web;

import com.basevaadin.application.data.DTO.UsuarioDTO;
import com.basevaadin.application.data.request.LoginRequest;
import com.basevaadin.application.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users") // Versión de la API
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> findByUsernameAndPassword(@RequestBody LoginRequest loginRequest) {
        Optional<UsuarioDTO> usuarioOpt = usuarioService.findByNombreUsuarioAndPassword(
                loginRequest.getNombreUsuario(), loginRequest.getPassword());

        return usuarioOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
