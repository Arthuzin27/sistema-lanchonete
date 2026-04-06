package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.StatusEntregaRequestDTO;
import com.arthur.lojaonline.dto.response.StatusEntregaResponseDTO;
import com.arthur.lojaonline.model.entity.Entrega;
import com.arthur.lojaonline.model.entity.StatusEntrega;
import com.arthur.lojaonline.repository.EntregaRepository;
import com.arthur.lojaonline.repository.StatusEntregaRepository;
import com.arthur.lojaonline.service.StatusEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusEntregaServiceImpl implements StatusEntregaService {
    
    private final StatusEntregaRepository statusEntregaRepository;
    private final EntregaRepository entregaRepository;
    
    @Override
    @Transactional
    public StatusEntregaResponse criar(StatusEntregaRequest request) {
        if (statusEntregaRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe um status de entrega com este nome: " + request.getNome());
        }
        
        // Validação: verificar se o nome não está vazio
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do status de entrega não pode estar vazio");
        }
        
        StatusEntrega statusEntrega = new StatusEntrega();
        statusEntrega.setNome(request.getNome().trim());
        statusEntrega.setDescricao(request.getDescricao());
        
  
        statusEntrega = statusEntregaRepository.save(statusEntrega);
        
        return converterParaResponse(statusEntrega);
    }
    
    @Override
    @Transactional
    public StatusEntregaResponse atualizar(Long id, StatusEntregaRequest request) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido para atualização");
        }
        
        StatusEntrega statusEntrega = buscarEntityPorId(id);
        
        // Validar nome
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do status de entrega não pode estar vazio");
        }
        
        if (!statusEntrega.getNome().equalsIgnoreCase(request.getNome()) &&
            statusEntregaRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe um status de entrega com este nome: " + request.getNome());
        }
        
        statusEntrega.setNome(request.getNome().trim());
        statusEntrega.setDescricao(request.getDescricao());
        
        statusEntrega = statusEntregaRepository.save(statusEntrega);
        
        return converterParaResponse(statusEntrega);
    }
    
    @Override
    @Transactional
    public void deletar(Long id) {

        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido para exclusão");
        }
        
        StatusEntrega statusEntrega = buscarEntityPorId(id);
        
        List<Entrega> entregasComStatus = entregaRepository.findByStatusEntrega(statusEntrega);
        
        if (!entregasComStatus.isEmpty()) {
            // Lançar exceção com detalhes das entregas afetadas
            String idsEntregas = entregasComStatus.stream()
                    .map(e -> String.valueOf(e.getId()))
                    .collect(Collectors.joining(", "));
            
            throw new RuntimeException(
                String.format("Não é possível deletar o status '%s' pois existem %d entrega(s) associada(s): [%s]",
                    statusEntrega.getNome(),
                    entregasComStatus.size(),
                    idsEntregas
                )
            );
        }
        
        if (isStatusPadraoSistema(statusEntrega)) {
            throw new RuntimeException("Não é possível deletar status padrão do sistema: " + statusEntrega.getNome());
        }
        
 
        statusEntregaRepository.delete(statusEntrega);
    }
    
    @Override
    public StatusEntregaResponse buscarPorId(Long id) {
        // Validar ID
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido para busca");
        }
        
        StatusEntrega statusEntrega = buscarEntityPorId(id);
        return converterParaResponse(statusEntrega);
    }
    
    @Override
    public List<StatusEntregaResponse> buscarTodos() {
        List<StatusEntrega> statusList = statusEntregaRepository.findAll();
        
        if (statusList.isEmpty()) {
            throw new RuntimeException("Nenhum status de entrega cadastrado no sistema");
        }
        
        return statusList.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public StatusEntrega buscarEntityPorId(Long id) {
        return statusEntregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status de entrega não encontrado com ID: " + id));
    }
    
    @Override
    public List<StatusEntregaResponse> buscarPorNome(String nome) {
        // Validar nome
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("O nome para busca não pode estar vazio");
        }
        
        List<StatusEntrega> statusList = statusEntregaRepository.findByNome(nome);
        
        if (statusList.isEmpty()) {
            throw new RuntimeException("Nenhum status de entrega encontrado com o nome: " + nome);
        }
        
        return statusList.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public StatusEntregaResponseDTO buscarPorNomeExato(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("O nome para busca não pode estar vazio");
        }
        
        StatusEntrega statusEntrega = statusEntregaRepository.findByNome(nome.trim())
                .orElseThrow(() -> new RuntimeException("Status de entrega não encontrado com o nome: " + nome));
        
        return converterParaResponse(statusEntrega);
    }
    
    @Override
    @Transactional
    public StatusEntregaResponse alternarStatusAtivo(Long id) {
        StatusEntrega statusEntrega = buscarEntityPorId(id);
        
        statusEntrega = statusEntregaRepository.save(statusEntrega);
        return converterParaResponse(statusEntrega);
    }
    
    @Override
    @Transactional
    public void inicializarStatusPadrao() {
        String[][] statusPadrao = {
            {"PENDENTE", "Aguardando envio do pedido"},
            {"ENVIADO", "Pedido enviado para transportadora"},
            {"EM_TRANSITO", "Pedido em rota de entrega"},
            {"ENTREGUE", "Pedido entregue ao destinatário"},
            {"CANCELADO", "Entrega cancelada"},
            {"EXTRAVIADO", "Pedido extraviado durante o transporte"},
            {"DEVOLVIDO", "Pedido devolvido ao remetente"}
        };
        
        for (String[] status : statusPadrao) {
            if (!statusEntregaRepository.existsByNome(status[0])) {
                StatusEntrega statusEntrega = new StatusEntrega();
                statusEntrega.setNome(status[0]);
                statusEntrega.setDescricao(status[1]);
                statusEntregaRepository.save(statusEntrega);
            }
        }
    }
    
    private boolean isStatusPadraoSistema(StatusEntrega statusEntrega) {
        List<String> statusProtegidos = List.of(
            "PENDENTE", "ENVIADO", "EM_TRANSITO", "ENTREGUE", "CANCELADO"
        );
        return statusProtegidos.contains(statusEntrega.getNome().toUpperCase());
    }

    private StatusEntregaResponse converterParaResponse(StatusEntrega statusEntrega) {
        return new StatusEntregaResponse(
                statusEntrega.getId(),
                statusEntrega.getNome(),
                statusEntrega.getDescricao(),
                statusEntrega.getDataCadastro(),
                statusEntrega.getDataAtualizacao()
        );
    }
}