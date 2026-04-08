package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.ItemPedidoRequest;
import com.arthur.lojaonline.dto.response.ItemPedidoResponse;

import java.util.List;

public interface ItemPedidoService {

    ItemPedidoResponse cadastrar(ItemPedidoRequest request);

    List<ItemPedidoResponse> listarTodos();

    ItemPedidoResponse buscarPorId(Long id);

    ItemPedidoResponse atualizar(Long id, ItemPedidoRequest request);

    void deletar(Long id);
}