package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusPagamentoRequest {
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 20, message = "O nome deve ter no máximo 20 caracteres")
    private String nome;
    
    private String descricao;
}