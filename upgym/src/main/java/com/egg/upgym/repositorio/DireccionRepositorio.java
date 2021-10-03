package com.egg.upgym.repositorio;

import com.egg.upgym.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepositorio extends JpaRepository<Direccion, String> {
    
}
