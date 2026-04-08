package com.arthur.lojaonline.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private Long id;

    private Long clienteId;
    private String clienteNome;

    private Long statusId;
    private String statusNome;

    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private LocalDateTime dataAtualizacao;
}