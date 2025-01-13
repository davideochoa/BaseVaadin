package com.basevaadin.application.app.data.repository;

import com.basevaadin.application.app.data.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    RolEntity findByNombre(String nombre);
}
