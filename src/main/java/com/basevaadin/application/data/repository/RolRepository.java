package com.basevaadin.application.data.repository;

import com.basevaadin.application.data.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    RolEntity findByNombre(String nombre);
}
