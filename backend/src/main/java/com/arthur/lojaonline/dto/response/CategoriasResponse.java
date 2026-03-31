package com.arthur.lojaonline.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CategoriasResponse {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
}