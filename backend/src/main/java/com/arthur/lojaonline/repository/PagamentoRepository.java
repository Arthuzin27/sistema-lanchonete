package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}