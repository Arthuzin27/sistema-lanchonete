package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.StatusPagamentoRequest;
import com.arthur.lojaonline.dto.response.StatusPagamentoResponse;
import com.arthur.lojaonline.model.entity.StatusPagamento;

import java.util.List;

public interface StatusPagamentoService {
    
    StatusPagamentoResponse criar(StatusPagamentoRequest requestDTO);
    
    StatusPagamentoResponse atualizar(Long id, StatusPagamentoRequest requestDTO);
    
    void deletar(Long id);
    
    StatusPagamentoResponse buscarPorId(Long id);
    
    List<StatusPagamentoResponse> buscarTodos();
    
    StatusPagamento buscarEntityPorId(Long id);
    
    List<StatusPagamentoResponse> buscarPorNome(String nome);
}