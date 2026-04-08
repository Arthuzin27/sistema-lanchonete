package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.TransportadoraRequest;
import com.arthur.lojaonline.dto.response.TransportadoraResponse;
import com.arthur.lojaonline.model.entity.Transportadora;
import com.arthur.lojaonline.repository.TransportadoraRepository;
import com.arthur.lojaonline.service.TransportadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportadoraServiceImpl implements TransportadoraService {

    private final TransportadoraRepository transportadoraRepository;

    @Override
    @Transactional
    public TransportadoraResponse cadastrar(TransportadoraRequest request) {

        if (transportadoraRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe uma transportadora com esse nome.");
        }

        if (transportadoraRepository.existsByCnpj(request.getCnpj())) {
            throw new RuntimeException("Já existe uma transportadora com esse CNPJ.");
        }

        Transportadora transportadora = new Transportadora();
        transportadora.setNome(request.getNome());
        transportadora.setTelefone(request.getTelefone());
        transportadora.setEmail(request.getEmail());
        transportadora.setCnpj(request.getCnpj());
        transportadora.setPrazoEntregaDias(request.getPrazoEntregaDias());
        transportadora.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        return converterParaResponse(transportadoraRepository.save(transportadora));
    }

    @Override
    public List<TransportadoraResponse> listarTodas() {
        return transportadoraRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public TransportadoraResponse buscarPorId(Long id) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada."));

        return converterParaResponse(transportadora);
    }

    @Override
    @Transactional
    public TransportadoraResponse atualizar(Long id, TransportadoraRequest request) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada."));

        if (!transportadora.getNome().equalsIgnoreCase(request.getNome())
                && transportadoraRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Já existe outra transportadora com esse nome.");
        }

        if (!transportadora.getCnpj().equals(request.getCnpj())
                && transportadoraRepository.existsByCnpj(request.getCnpj())) {
            throw new RuntimeException("Já existe outra transportadora com esse CNPJ.");
        }

        transportadora.setNome(request.getNome());
        transportadora.setTelefone(request.getTelefone());
        transportadora.setEmail(request.getEmail());
        transportadora.setCnpj(request.getCnpj());
        transportadora.setPrazoEntregaDias(request.getPrazoEntregaDias());
        transportadora.setAtivo(request.getAtivo() != null ? request.getAtivo() : transportadora.getAtivo());

        return converterParaResponse(transportadoraRepository.save(transportadora));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada."));

        transportadoraRepository.delete(transportadora);
    }

    private TransportadoraResponse converterParaResponse(Transportadora transportadora) {
        return new TransportadoraResponse(
                transportadora.getId(),
                transportadora.getNome(),
                transportadora.getTelefone(),
                transportadora.getEmail(),
                transportadora.getCnpj(),
                transportadora.getPrazoEntregaDias(),
                transportadora.getAtivo(),
                transportadora.getDataCadastro(),
                transportadora.getDataAtualizacao()
        );
    }
}