package com.arthur.lojaonline.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponse {

    private Long id;

    private Long pedidoId;

    private Long produtoId;
    private String produtoNome;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}