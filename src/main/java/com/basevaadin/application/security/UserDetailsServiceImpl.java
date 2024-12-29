package com.basevaadin.application.security;

import com.basevaadin.application.data.entity.UsuarioEntity;
import com.basevaadin.application.data.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity user = usuarioRepository.findByNombreUsuario(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), user.getPassword(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(UsuarioEntity user) {
        List<GrantedAuthority> roles = new ArrayList<>();

        if(user.getEsAdministrador())
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return roles;

    }

}
