package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusEntregaRepository extends JpaRepository<StatusEntrega, Long> {

    boolean existsByNome(String nome);

    List<StatusEntrega> findByNomeContainingIgnoreCase(String nome);

    Optional<StatusEntrega> findByNomeIgnoreCase(String nome);
}