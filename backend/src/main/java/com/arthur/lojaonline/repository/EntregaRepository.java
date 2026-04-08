package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Entrega;
import com.arthur.lojaonline.model.entity.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByStatusEntrega(StatusEntrega statusEntrega);
}