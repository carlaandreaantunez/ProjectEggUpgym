package com.egg.upgym.repositorio;

import com.egg.upgym.entidades.Gimnasio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GimnasioRepositorio extends JpaRepository<Gimnasio, String> {

    @Query("SELECT g FROM Gimnasio g WHERE direccion.provincia=:provincia")
    List<Gimnasio> buscarPorProvincia(@Param("provincia") String provincia);

    @Query("SELECT g FROM Gimnasio g WHERE direccion.provincia=:provincia AND direccion.ciudad=:ciudad")
    List<Gimnasio> buscarPorProvinciaYCiudad(@Param("provincia") String provincia,@Param("ciudad") String ciudad);

    @Query("SELECT g FROM Gimnasio g WHERE direccion.ciudad=:ciudad")
    List<Gimnasio> buscarPorCiudad(@Param("ciudad") String ciudad);

    @Query("SELECT g from Gimnasio g WHERE g.email = :email")
    Gimnasio buscarPorEmail(@Param("email") String email);

}
