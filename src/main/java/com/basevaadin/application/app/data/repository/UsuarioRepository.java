package com.basevaadin.application.app.data.repository;

import com.basevaadin.application.app.data.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>, JpaSpecificationExecutor<UsuarioEntity> {
    UsuarioEntity findByNombreUsuario(String nombreUsuario);

    Optional<UsuarioEntity> findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    List<UsuarioEntity> findAllByOrderByNombrePropioAsc();

}
