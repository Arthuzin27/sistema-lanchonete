package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusPagamentoRepository extends JpaRepository<StatusPagamento, Long> {
    
    Optional<StatusPagamento> findByNome(String nome);
    
    boolean existsByNome(String nome);
}