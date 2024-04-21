package com.pathfinder.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathfinder.business.model.route.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    List<Ruta> findByUbicacionContaining(String ubicacion);
}
