package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.EntregaRequest;
import com.arthur.lojaonline.dto.response.EntregaResponse;
import com.arthur.lojaonline.model.entity.Endereco;
import com.arthur.lojaonline.model.entity.Entrega;
import com.arthur.lojaonline.model.entity.Pedido;
import com.arthur.lojaonline.model.entity.StatusEntrega;
import com.arthur.lojaonline.model.entity.Transportadora;
import com.arthur.lojaonline.repository.EnderecoRepository;
import com.arthur.lojaonline.repository.EntregaRepository;
import com.arthur.lojaonline.repository.PedidoRepository;
import com.arthur.lojaonline.repository.StatusEntregaRepository;
import com.arthur.lojaonline.repository.TransportadoraRepository;
import com.arthur.lojaonline.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaServiceImpl implements EntregaService {

        private final EntregaRepository entregaRepository;
        private final PedidoRepository pedidoRepository;
        private final EnderecoRepository enderecoRepository;
        private final TransportadoraRepository transportadoraRepository;
        private final StatusEntregaRepository statusEntregaRepository;

        @Override
        @Transactional
        public EntregaResponse salvar(EntregaRequest request) {
                validarRequest(request);

                Pedido pedido = buscarPedidoPorId(request.getPedidoId());
                Endereco endereco = buscarEnderecoPorId(request.getEnderecoId());
                StatusEntrega statusEntrega = buscarStatusEntregaPorId(request.getStatusEntregaId());
                Transportadora transportadora = buscarTransportadoraSeInformada(request.getTransportadoraId());

                Entrega entrega = new Entrega();
                entrega.setPedido(pedido);
                entrega.setEndereco(endereco);
                entrega.setTransportadora(transportadora);
                entrega.setStatusEntrega(statusEntrega);
                entrega.setDataEnvio(request.getDataEnvio() != null ? request.getDataEnvio() : LocalDateTime.now());
                entrega.setDataEntrega(request.getDataEntrega());
                entrega.setObservacoes(request.getObservacoes());

                entrega = entregaRepository.save(entrega);

                return toResponse(entrega);
        }

        @Override
        public List<EntregaResponse> listarTodos() {
                List<Entrega> entregas = entregaRepository.findAll();

                if (entregas.isEmpty()) {
                        throw new RuntimeException("Nenhuma entrega cadastrada no sistema");
                }

                return entregas.stream()
                                .map(this::toResponse)
                                .toList();
        }

        @Override
        public EntregaResponse buscarPorId(Long id) {
                validarId(id);

                Entrega entrega = buscarEntregaPorId(id);
                return toResponse(entrega);
        }

        @Override
        @Transactional
        public EntregaResponse atualizar(Long id, EntregaRequest request) {
                validarId(id);
                validarRequest(request);

                Entrega entrega = buscarEntregaPorId(id);
                Pedido pedido = buscarPedidoPorId(request.getPedidoId());
                Endereco endereco = buscarEnderecoPorId(request.getEnderecoId());
                StatusEntrega statusEntrega = buscarStatusEntregaPorId(request.getStatusEntregaId());
                Transportadora transportadora = buscarTransportadoraSeInformada(request.getTransportadoraId());

                entrega.setPedido(pedido);
                entrega.setEndereco(endereco);
                entrega.setTransportadora(transportadora);
                entrega.setStatusEntrega(statusEntrega);
                entrega.setDataEnvio(request.getDataEnvio() != null ? request.getDataEnvio() : entrega.getDataEnvio());
                entrega.setDataEntrega(request.getDataEntrega());
                entrega.setObservacoes(request.getObservacoes());

                entrega = entregaRepository.save(entrega);

                return toResponse(entrega);
        }

        @Override
        @Transactional
        public void deletar(Long id) {
                validarId(id);

                Entrega entrega = buscarEntregaPorId(id);
                entregaRepository.delete(entrega);
        }

        private Entrega buscarEntregaPorId(Long id) {
                return entregaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));
        }

        private Pedido buscarPedidoPorId(Long id) {
                validarId(id);
                return pedidoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
        }

        private Endereco buscarEnderecoPorId(Long id) {
                validarId(id);
                return enderecoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
        }

        private StatusEntrega buscarStatusEntregaPorId(Long id) {
                validarId(id);
                return statusEntregaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Status da entrega não encontrado com ID: " + id));
        }

        private Transportadora buscarTransportadoraSeInformada(Long id) {
                if (id == null) {
                        return null;
                }

                validarId(id);
                return transportadoraRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada com ID: " + id));
        }

        private void validarId(Long id) {
                if (id == null || id <= 0) {
                        throw new RuntimeException("ID inválido");
                }
        }

        private void validarRequest(EntregaRequest request) {
                if (request == null) {
                        throw new RuntimeException("Os dados da entrega não foram informados");
                }

                if (request.getPedidoId() == null || request.getPedidoId() <= 0) {
                        throw new RuntimeException("Pedido é obrigatório");
                }

                if (request.getEnderecoId() == null || request.getEnderecoId() <= 0) {
                        throw new RuntimeException("Endereço é obrigatório");
                }

                if (request.getStatusEntregaId() == null || request.getStatusEntregaId() <= 0) {
                        throw new RuntimeException("Status da entrega é obrigatório");
                }
        }

        private EntregaResponse toResponse(Entrega entrega) {
                return new EntregaResponse(
                                entrega.getId(),
                                entrega.getPedido() != null ? entrega.getPedido().getId() : null,
                                entrega.getEndereco() != null ? entrega.getEndereco().getId() : null,
                                entrega.getTransportadora() != null ? entrega.getTransportadora().getId() : null,
                                entrega.getStatusEntrega() != null ? entrega.getStatusEntrega().getId() : null,
                                entrega.getDataEnvio(),
                                entrega.getDataEntrega(),
                                entrega.getObservacoes());
        }
}