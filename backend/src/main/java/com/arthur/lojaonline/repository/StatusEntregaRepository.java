package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusEntregaRepository extends JpaRepository<StatusEntrega, Long> {
    
    Optional<StatusEntrega> findByNome(String nome);
    
    boolean existsByNome(String nome);
}