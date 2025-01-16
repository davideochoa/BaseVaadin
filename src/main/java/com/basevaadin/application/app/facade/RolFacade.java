package com.basevaadin.application.app.facade;

import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.data.entity.RolEntity;
import com.basevaadin.application.app.data.entity.UsuarioEntity;
import com.basevaadin.application.app.data.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolFacade {
    RolRepository rolRepository;

    @Autowired
    public RolFacade(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public RolEntity findByNombre(String nombre){
        return rolRepository.findByNombre(nombre);
    }

    public List<RolEntity> findAllByOrderByNombreAsc() { return rolRepository.findAllByOrderByNombreAsc(); }
}
