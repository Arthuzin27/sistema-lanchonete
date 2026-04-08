package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    Optional<Transportadora> findByCnpj(String cnpj);

    Optional<Transportadora> findByNome(String nome);

    boolean existsByCnpj(String cnpj);

    boolean existsByNome(String nome);
}