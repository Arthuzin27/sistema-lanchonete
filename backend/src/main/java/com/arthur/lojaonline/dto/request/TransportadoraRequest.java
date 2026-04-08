package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportadoraRequest {

    @NotBlank(message = "O nome da transportadora é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Informe um email válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(max = 18, message = "O CNPJ deve ter no máximo 18 caracteres")
    private String cnpj;

    @NotNull(message = "O prazo de entrega é obrigatório")
    @Min(value = 1, message = "O prazo de entrega deve ser de no mínimo 1 dia")
    private Integer prazoEntregaDias;

    private Boolean ativo;
}