package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.PagamentoRequest;
import com.arthur.lojaonline.dto.response.PagamentoResponse;
import com.arthur.lojaonline.model.entity.FormaPagamento;
import com.arthur.lojaonline.model.entity.Pagamento;
import com.arthur.lojaonline.model.entity.Pedido;
import com.arthur.lojaonline.model.entity.StatusPagamento;
import com.arthur.lojaonline.repository.FormaPagamentoRepository;
import com.arthur.lojaonline.repository.PagamentoRepository;
import com.arthur.lojaonline.repository.PedidoRepository;
import com.arthur.lojaonline.repository.StatusPagamentoRepository;
import com.arthur.lojaonline.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

        private final PagamentoRepository pagamentoRepository;
        private final PedidoRepository pedidoRepository;
        private final FormaPagamentoRepository formaPagamentoRepository;
        private final StatusPagamentoRepository statusPagamentoRepository;

        @Override
        public PagamentoResponse cadastrar(PagamentoRequest request) {

                Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

                FormaPagamento formaPagamento = formaPagamentoRepository.findById(request.getFormaPagamentoId())
                                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada."));

                StatusPagamento statusPagamento = statusPagamentoRepository.findById(request.getStatusPagamentoId())
                                .orElseThrow(() -> new RuntimeException("Status do pagamento não encontrado."));

                Pagamento pagamento = Pagamento.builder()
                                .pedido(pedido)
                                .formaPagamento(formaPagamento)
                                .statusPagamento(statusPagamento)
                                .valorPago(request.getValorPago())
                                .build();

                return converterParaResponse(pagamentoRepository.save(pagamento));
        }

        @Override
        public List<PagamentoResponse> listarTodos() {
                return pagamentoRepository.findAll()
                                .stream()
                                .map(this::converterParaResponse)
                                .toList();
        }

        @Override
        public PagamentoResponse buscarPorId(Long id) {
                Pagamento pagamento = pagamentoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado."));

                return converterParaResponse(pagamento);
        }

        @Override
        public PagamentoResponse atualizar(Long id, PagamentoRequest request) {
                Pagamento pagamento = pagamentoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado."));

                Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

                FormaPagamento formaPagamento = formaPagamentoRepository.findById(request.getFormaPagamentoId())
                                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada."));

                StatusPagamento statusPagamento = statusPagamentoRepository.findById(request.getStatusPagamentoId())
                                .orElseThrow(() -> new RuntimeException("Status do pagamento não encontrado."));

                pagamento.setPedido(pedido);
                pagamento.setFormaPagamento(formaPagamento);
                pagamento.setStatusPagamento(statusPagamento);
                pagamento.setValorPago(request.getValorPago());

                return converterParaResponse(pagamentoRepository.save(pagamento));
        }

        @Override
        public void deletar(Long id) {
                Pagamento pagamento = pagamentoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado."));

                pagamentoRepository.delete(pagamento);
        }

        private PagamentoResponse converterParaResponse(Pagamento pagamento) {
                PagamentoResponse response = new PagamentoResponse();

                response.setId(pagamento.getId());
                response.setPedidoId(pagamento.getPedido().getId());
                response.setFormaPagamentoId(pagamento.getFormaPagamento().getId());
                response.setFormaPagamentoNome(pagamento.getFormaPagamento().getNome());
                response.setStatusPagamentoId(pagamento.getStatusPagamento().getId());
                response.setStatusPagamentoNome(pagamento.getStatusPagamento().getNome());
                response.setValorPago(pagamento.getValorPago());
                response.setDataPagamento(pagamento.getDataPagamento());

                return response;
        }
}