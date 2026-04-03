package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecosRepository extends JpaRepository<Endereco, Long>{
    
    List<Endereco> findByClienteId(Long clienteId);

    Optional<Endereco> findByClienteIdAndPrincipalTrue(Long clienteId);

    Optional<Endereco> findByIdAndClienteId(Long id, Long clienteId);
}
