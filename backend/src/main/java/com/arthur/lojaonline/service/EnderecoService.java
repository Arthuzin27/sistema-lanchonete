package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.EnderecosRequest;
import com.arthur.lojaonline.dto.response.EnderecosResponse;

import java.util.List;

public interface EnderecoService {
    
    EnderecosResponse cadastrarEnderecos(EnderecosRequest request);

    EnderecosResponse buscarPorId(Long id);

    List<EnderecosResponse> listarTodos();

    EnderecosResponse atualizarEnderecos (Long id, EnderecosRequest request);

    void deletarEndereco (Long id);
}
