package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    Optional<ItemPedido> findByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);

    List<ItemPedido> findByPedidoId(Long pedidoId);
}