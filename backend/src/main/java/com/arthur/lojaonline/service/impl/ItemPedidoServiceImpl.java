package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.ItemPedidoRequest;
import com.arthur.lojaonline.dto.response.ItemPedidoResponse;
import com.arthur.lojaonline.model.entity.ItemPedido;
import com.arthur.lojaonline.model.entity.Pedido;
import com.arthur.lojaonline.model.entity.Produto;
import com.arthur.lojaonline.repository.ItemPedidoRepository;
import com.arthur.lojaonline.repository.PedidoRepository;
import com.arthur.lojaonline.repository.ProdutoRepository;
import com.arthur.lojaonline.service.ItemPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public ItemPedidoResponse cadastrar(ItemPedidoRequest request) {

        Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (itemPedidoRepository.findByPedidoIdAndProdutoId(request.getPedidoId(), request.getProdutoId())
                .isPresent()) {
            throw new RuntimeException("Este produto já foi adicionado ao pedido.");
        }

        BigDecimal precoUnitario = produto.getPreco();
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(request.getQuantidade()));

        ItemPedido itemPedido = ItemPedido.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(request.getQuantidade())
                .precoUnitario(precoUnitario)
                .subtotal(subtotal)
                .build();

        ItemPedido salvo = itemPedidoRepository.save(itemPedido);

        atualizarValorTotalPedido(pedido);

        return converterParaResponse(salvo);
    }

    @Override
    public List<ItemPedidoResponse> listarTodos() {
        return itemPedidoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public ItemPedidoResponse buscarPorId(Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido não encontrado."));

        return converterParaResponse(itemPedido);
    }

    @Override
    public ItemPedidoResponse atualizar(Long id, ItemPedidoRequest request) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido não encontrado."));

        Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        BigDecimal precoUnitario = produto.getPreco();
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(request.getQuantidade()));

        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(request.getQuantidade());
        itemPedido.setPrecoUnitario(precoUnitario);
        itemPedido.setSubtotal(subtotal);

        ItemPedido atualizado = itemPedidoRepository.save(itemPedido);

        atualizarValorTotalPedido(pedido);

        return converterParaResponse(atualizado);
    }

    @Override
    public void deletar(Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido não encontrado."));

        Pedido pedido = itemPedido.getPedido();

        itemPedidoRepository.delete(itemPedido);

        atualizarValorTotalPedido(pedido);
    }

    private void atualizarValorTotalPedido(Pedido pedido) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedido.getId());

        BigDecimal total = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(total);
        pedidoRepository.save(pedido);
    }

    private ItemPedidoResponse converterParaResponse(ItemPedido itemPedido) {
        ItemPedidoResponse response = new ItemPedidoResponse();

        response.setId(itemPedido.getId());
        response.setPedidoId(itemPedido.getPedido().getId());
        response.setProdutoId(itemPedido.getProduto().getId());
        response.setProdutoNome(itemPedido.getProduto().getNome());
        response.setQuantidade(itemPedido.getQuantidade());
        response.setPrecoUnitario(itemPedido.getPrecoUnitario());
        response.setSubtotal(itemPedido.getSubtotal());

        return response;
    }
}