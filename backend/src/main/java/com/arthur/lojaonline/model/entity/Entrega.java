package com.arthur.lojaonline.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Getter
@Setter
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    @ManyToOne
    @JoinColumn(name = "status_entrega_id", nullable = false)
    private StatusEntrega statusEntrega;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}