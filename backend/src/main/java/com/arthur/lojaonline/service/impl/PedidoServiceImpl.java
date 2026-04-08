package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.PedidoRequest;
import com.arthur.lojaonline.dto.response.PedidoResponse;
import com.arthur.lojaonline.model.entity.Cliente;
import com.arthur.lojaonline.model.entity.Pedido;
import com.arthur.lojaonline.model.entity.StatusPedido;
import com.arthur.lojaonline.repository.ClienteRepository;
import com.arthur.lojaonline.repository.PedidoRepository;
import com.arthur.lojaonline.repository.StatusPedidoRepository;
import com.arthur.lojaonline.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final StatusPedidoRepository statusPedidoRepository;

    @Override
    public PedidoResponse cadastrar(PedidoRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        StatusPedido statusPedido = statusPedidoRepository.findById(request.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status do pedido não encontrado."));

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .statusPedido(statusPedido)
                .valorTotal(BigDecimal.ZERO)
                .build();

        return converterParaResponse(pedidoRepository.save(pedido));
    }

    @Override
    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        return converterParaResponse(pedido);
    }

    @Override
    public PedidoResponse atualizar(Long id, PedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        StatusPedido statusPedido = statusPedidoRepository.findById(request.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status do pedido não encontrado."));

        pedido.setCliente(cliente);
        pedido.setStatusPedido(statusPedido);

        return converterParaResponse(pedidoRepository.save(pedido));
    }

    @Override
    public void deletar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        pedidoRepository.delete(pedido);
    }

    private PedidoResponse converterParaResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();

        response.setId(pedido.getId());
        response.setClienteId(pedido.getCliente().getId());
        response.setClienteNome(pedido.getCliente().getNomeCompleto());
        response.setStatusId(pedido.getStatusPedido().getId());
        response.setStatusNome(pedido.getStatusPedido().getNome());
        response.setDataPedido(pedido.getDataPedido());
        response.setValorTotal(pedido.getValorTotal());
        response.setDataAtualizacao(pedido.getDataAtualizacao());

        return response;
    }
}