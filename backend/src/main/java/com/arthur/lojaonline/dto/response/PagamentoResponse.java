package com.arthur.lojaonline.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponse {

    private Long id;

    private Long pedidoId;

    private Long formaPagamentoId;
    private String formaPagamentoNome;

    private Long statusPagamentoId;
    private String statusPagamentoNome;

    private BigDecimal valorPago;
    private LocalDateTime dataPagamento;
}