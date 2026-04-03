package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.CategoriaRequest;
import com.arthur.lojaonline.dto.response.CategoriaResponse;
import com.arthur.lojaonline.model.entity.Categoria;
import com.arthur.lojaonline.repository.CategoriaRepository;
import com.arthur.lojaonline.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaResponse criarCategoria(CategoriaRequest request) {
        validarNomeExistente(request.getNome());

        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        categoria.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        categoriaRepository.save(categoria);

        return converterParaResponse(categoria);
    }

    @Override
    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return converterParaResponse(categoria);
    }

    @Override
    public List<CategoriaResponse> listarTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::converterParaResponse).collect(Collectors.toList());
    }

    @Override
    public CategoriaResponse atualizarCategoria(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getNome().equals(request.getNome())) {
            validarNomeExistente(request.getNome());
            categoria.setNome(request.getNome());
        }

        categoria.setDescricao(request.getDescricao());
        categoria.setAtivo(request.getAtivo() != null ? request.getAtivo() : categoria.getAtivo());

        categoriaRepository.save(categoria);

        return converterParaResponse(categoria);
    }

    @Override
    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaResponse converterParaResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());
        response.setDescricao(categoria.getDescricao());
        response.setAtivo(categoria.getAtivo());
        response.setDataCadastro(categoria.getDataCadastro());
        return response;
    }

    private void validarNomeExistente(String nome) {
        if (categoriaRepository.existsByNome(nome)) {
            throw new RuntimeException("Já existe uma categoria com o nome: " + nome);
        }
    }
}