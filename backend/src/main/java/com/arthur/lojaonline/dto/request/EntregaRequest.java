package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EntregaRequest {

    @NotNull(message = "O ID do pedido é obrigatório.")
    @Positive(message = "O ID do pedido deve ser um número positivo.")
    private Long pedidoId;

    @NotNull(message = "O ID do endereço é obrigatório.")
    @Positive(message = "O ID do endereço deve ser um número positivo.")
    private Long enderecoId;

    @Positive(message = "O ID da transportadora deve ser um número positivo.")
    private Long transportadoraId;

    @NotNull(message = "O ID do status da entrega é obrigatório.")
    @Positive(message = "O ID do status da entrega deve ser um número positivo.")
    private Long statusEntregaId;

    private LocalDateTime dataEnvio;

    private LocalDateTime dataEntrega;

    @Size(max = 1000, message = "As observações devem ter no máximo 1000 caracteres.")
    private String observacoes;
}