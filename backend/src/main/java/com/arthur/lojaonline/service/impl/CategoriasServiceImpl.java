package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.CategoriasRequest;
import com.arthur.lojaonline.dto.response.CategoriasResponse;
import com.arthur.lojaonline.model.entity.Categorias;
import com.arthur.lojaonline.repository.CategoriasRepository;
import com.arthur.lojaonline.service.CategoriasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasServiceImpl implements CategoriasService {

    private final CategoriasRepository categoriasRepository;

    public CategoriasServiceImpl(CategoriasRepository categoriaRepository) {
        this.categoriasRepository = categoriaRepository;
    }

    @Override
    public CategoriasResponse criarCategoria(CategoriasRequest request) {
        validarNomeExistente(request.getNome());

        Categorias categoria = new Categorias();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        categoria.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        categoriasRepository.save(categoria);

        return converterParaResponse(categoria);
    }

    @Override
    public CategoriasResponse buscarPorId(Long id) {
        Categorias categoria = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return converterParaResponse(categoria);
    }

    @Override
    public List<CategoriasResponse> listarTodas() {
        List<Categorias> categorias = categoriasRepository.findAll();
        return categorias.stream().map(this::converterParaResponse).collect(Collectors.toList());
    }

    @Override
    public CategoriasResponse atualizarCategoria(Long id, CategoriasRequest request) {
        Categorias categoria = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getNome().equals(request.getNome())) {
            validarNomeExistente(request.getNome());
            categoria.setNome(request.getNome());
        }

        categoria.setDescricao(request.getDescricao());
        categoria.setAtivo(request.getAtivo() != null ? request.getAtivo() : categoria.getAtivo());

        categoriasRepository.save(categoria);

        return converterParaResponse(categoria);
    }

    @Override
    public void deletarCategoria(Long id) {
        if (!categoriasRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriasRepository.deleteById(id);
    }

    private CategoriasResponse converterParaResponse(Categorias categoria) {
        CategoriasResponse response = new CategoriasResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());
        response.setDescricao(categoria.getDescricao());
        response.setAtivo(categoria.getAtivo());
        response.setDataCadastro(categoria.getDataCadastro());
        return response;
    }

    private void validarNomeExistente(String nome) {
        if (categoriasRepository.existsByNome(nome)) {
            throw new RuntimeException("Já existe uma categoria com o nome: " + nome);
        }
    }
}