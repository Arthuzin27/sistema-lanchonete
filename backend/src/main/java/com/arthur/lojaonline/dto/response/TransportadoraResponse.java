package com.arthur.lojaonline.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportadoraResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private Integer prazoEntregaDias;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}