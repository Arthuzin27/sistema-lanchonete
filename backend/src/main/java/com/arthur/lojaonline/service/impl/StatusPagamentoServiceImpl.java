package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.StatusPagamentoRequest;
import com.arthur.lojaonline.dto.response.StatusPagamentoResponse;
import com.arthur.lojaonline.model.entity.StatusPagamento;
import com.arthur.lojaonline.repository.StatusPagamentoRepository;
import com.arthur.lojaonline.service.StatusPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusPagamentoServiceImpl implements StatusPagamentoService {
    
    private final StatusPagamentoRepository statusPagamentoRepository;
    
    @Override
    @Transactional
    public StatusPagamentoResponse criar(StatusPagamentoRequest request) {
        if (statusPagamentoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe um status de pagamento com este nome: " + request.getNome());
        }
        
        StatusPagamento statusPagamento = new StatusPagamento();
        statusPagamento.setNome(request.getNome());
        statusPagamento.setDescricao(request.getDescricao());
        
        statusPagamento = statusPagamentoRepository.save(statusPagamento);
        return converterParaResponse(statusPagamento);
    }
    
    @Override
    @Transactional
    public StatusPagamentoResponse atualizar(Long id, StatusPagamentoRequest request) {
        StatusPagamento statusPagamento = buscarEntityPorId(id);
        
        if (!statusPagamento.getNome().equals(request.getNome()) &&
            statusPagamentoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe um status de pagamento com este nome: " + request.getNome());
        }
        
        statusPagamento.setNome(request.getNome());
        statusPagamento.setDescricao(request.getDescricao());
        
        statusPagamento = statusPagamentoRepository.save(statusPagamento);
        return converterParaResponse(statusPagamento);
    }
    
    @Override
    @Transactional
    public void deletar(Long id) {
        StatusPagamento statusPagamento = buscarEntityPorId(id);
        statusPagamentoRepository.delete(statusPagamento);
    }
    
    @Override
    public StatusPagamentoResponse buscarPorId(Long id) {
        StatusPagamento statusPagamento = buscarEntityPorId(id);
        return converterParaResponse(statusPagamento);
    }
    
    @Override
    public List<StatusPagamentoResponse> buscarTodos() {
        return statusPagamentoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public StatusPagamento buscarEntityPorId(Long id) {
        return statusPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status de pagamento não encontrado com ID: " + id));
    }
    
    @Override
    public List<StatusPagamentoResponse> buscarPorNome(String nome) {
        return statusPagamentoRepository.findByNome(nome)
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }
    
    private StatusPagamentoResponse converterParaResponse(StatusPagamento statusPagamento) {
        return new StatusPagamentoResponse(
                statusPagamento.getId(),
                statusPagamento.getNome(),
                statusPagamento.getDescricao(),
                statusPagamento.getDataCadastro(),
                statusPagamento.getDataAtualizacao()
        );
    }
}