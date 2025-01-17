package com.basevaadin.application.app.facade;

import com.basevaadin.application.app.data.entity.UsuarioEntity;
import com.basevaadin.application.app.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UsuarioFacade {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioFacade(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioEntity> findAllByOrderByNombrePropioAsc(){
        return usuarioRepository.findAllByOrderByNombrePropioAsc();
    }

    @Transactional
    public void save(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
    }
}
