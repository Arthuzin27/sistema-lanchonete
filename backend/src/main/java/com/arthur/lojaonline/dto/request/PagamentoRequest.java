package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoRequest {

    @NotNull(message = "O ID do pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message = "O ID da forma de pagamento é obrigatório")
    private Long formaPagamentoId;

    @NotNull(message = "O ID do status do pagamento é obrigatório")
    private Long statusPagamentoId;

    @NotNull(message = "O valor pago é obrigatório")
    @DecimalMin(value = "0.00", inclusive = true, message = "O valor pago não pode ser negativo")
    private BigDecimal valorPago;
}