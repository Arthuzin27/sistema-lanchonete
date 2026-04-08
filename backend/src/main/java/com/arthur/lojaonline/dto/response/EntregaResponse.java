package com.arthur.lojaonline.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaResponse {

    private Long id;
    private Long pedidoId;
    private Long enderecoId;
    private Long transportadoraId;
    private Long statusEntregaId;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataEntrega;
    private String observacoes;
}