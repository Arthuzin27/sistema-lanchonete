package com.arthur.lojaonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusPagamentoResponse {
    
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}