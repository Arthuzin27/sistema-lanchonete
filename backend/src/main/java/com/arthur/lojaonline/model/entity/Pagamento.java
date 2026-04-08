package com.arthur.lojaonline.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_pagamento_id", nullable = false)
    private StatusPagamento statusPagamento;

    @Column(name = "valor_pago", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "data_pagamento", insertable = false, updatable = false)
    private LocalDateTime dataPagamento;
}