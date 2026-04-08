package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}