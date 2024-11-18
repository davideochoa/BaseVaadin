package com.adminencuesta360.application.security;

import com.adminencuesta360.application.data.entity.UsuarioEntity;
import com.adminencuesta360.application.data.repository.UsuarioRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<UsuarioEntity> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> usuarioRepository.findByNombreUsuario(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }

}
