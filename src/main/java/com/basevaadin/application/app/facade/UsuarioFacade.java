package com.basevaadin.application.app.facade;

import com.basevaadin.application.app.data.entity.RolEntity;
import com.basevaadin.application.app.data.entity.UsuarioEntity;
import com.basevaadin.application.app.data.repository.RolRepository;
import com.basevaadin.application.app.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioFacade {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioFacade(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public UsuarioEntity findByNombreUsuario(String username){
        return usuarioRepository.findByNombreUsuario(username);
    }

    public Optional<UsuarioEntity> findByNombreUsuarioAndPassword(String nombreUsuario, String password) {
        return usuarioRepository.findByNombreUsuarioAndPassword(nombreUsuario, password);
    }

    public List<UsuarioEntity> findAllByOrderByNombrePropioAsc(){
        return usuarioRepository.findAllByOrderByNombrePropioAsc();
    }

    public UsuarioEntity guardarUsuarioConRoles(UsuarioEntity usuario, List<String> nombresRoles) {
        // Cargar los roles desde la base de datos
        HashSet<RolEntity> roles = new HashSet<>();
        for (String nombreRol : nombresRoles) {
            RolEntity rol = rolRepository.findByNombre(nombreRol);
            if (rol != null) {
                roles.add(rol);
            }
        }

        // Asignar los roles al usuario
        usuario.setRoles(roles);

        // Guardar el usuario
        return usuarioRepository.save(usuario);
    }

}
