package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriasRequest {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
    private String descricao;

    private Boolean ativo = true; // padrão true
}