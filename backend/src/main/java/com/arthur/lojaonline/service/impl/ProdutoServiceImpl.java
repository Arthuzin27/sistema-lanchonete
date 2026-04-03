package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.ProdutoRequest;
import com.arthur.lojaonline.dto.response.ProdutoResponse;
import com.arthur.lojaonline.model.entity.Categoria;
import com.arthur.lojaonline.model.entity.Produto;
import com.arthur.lojaonline.repository.CategoriaRepository;
import com.arthur.lojaonline.repository.ProdutoRepository;
import com.arthur.lojaonline.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProdutoResponse criarProduto(ProdutoRequest request) {
        validarNomeExistente(request.getNome());

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setCategoria(categoria);
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());
        produto.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        produto = produtoRepository.save(produto);

        return converterParaResponse(produto);
    }

    @Override
    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return converterParaResponse(produto);
    }

    @Override
    public List<ProdutoResponse> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!produto.getNome().equals(request.getNome())) {
            validarNomeExistente(request.getNome());
            produto.setNome(request.getNome());
        }

        produto.setCategoria(categoria);
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());
        produto.setAtivo(request.getAtivo() != null ? request.getAtivo() : produto.getAtivo());

        produto = produtoRepository.save(produto);

        return converterParaResponse(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }

    private ProdutoResponse converterParaResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setCategoriaId(produto.getCategoria().getId());
        response.setCategoriaNome(produto.getCategoria().getNome());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());
        response.setAtivo(produto.getAtivo());
        response.setDataCadastro(produto.getDataCadastro());
        response.setDataAtualizacao(produto.getDataAtualizacao());

        return response;
    }

    private void validarNomeExistente(String nome) {
        if (produtoRepository.existsByNome(nome)) {
            throw new RuntimeException("Já existe um produto com o nome: " + nome);
        }
    }
}