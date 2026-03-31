package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    boolean existsByNome(String nome);

    Optional<Categorias> findByNome(String nome);
}