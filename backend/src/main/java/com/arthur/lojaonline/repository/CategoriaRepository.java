package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);
}