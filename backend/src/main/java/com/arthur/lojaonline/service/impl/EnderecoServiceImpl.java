package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.EnderecosRequest;
import com.arthur.lojaonline.dto.response.EnderecosResponse;
import com.arthur.lojaonline.model.entity.Cliente;
import com.arthur.lojaonline.model.entity.Endereco;
import com.arthur.lojaonline.model.entity.TipoEndereco;
import com.arthur.lojaonline.repository.ClienteRepository;
import com.arthur.lojaonline.repository.EnderecosRepository;
import com.arthur.lojaonline.repository.TipoEnderecoRepository;
import com.arthur.lojaonline.service.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecosRepository enderecosRepository;
    private final ClienteRepository clienteRepository;
    private final TipoEnderecoRepository tipoEnderecoRepository;

    public EnderecoServiceImpl(EnderecosRepository enderecosRepository,
                                ClienteRepository clienteRepository,
                                TipoEnderecoRepository tipoEnderecoRepository) {
        this.enderecosRepository = enderecosRepository;
        this.clienteRepository = clienteRepository;
        this.tipoEnderecoRepository = tipoEnderecoRepository;
    }

    @Override
    public EnderecosResponse cadastrarEnderecos(EnderecosRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        TipoEndereco tipoEndereco = tipoEnderecoRepository.findById(request.getTipoEnderecoId())
                .orElseThrow(() -> new RuntimeException("Tipo de endereço não encontrado"));

        Endereco end = new Endereco();
        end.setCliente(cliente);
        end.setTipoEndereco(tipoEndereco);
        end.setCep(request.getCep());
        end.setRua(request.getRua());
        end.setNumero(request.getNumero());
        end.setComplemento(request.getComplemento());
        end.setBairro(request.getBairro());
        end.setCidade(request.getCidade());
        end.setEstado(request.getEstado());
        end.setPrincipal(request.getPrincipal());

        enderecosRepository.save(end);

        return converterParaResponse(end);
    }

    @Override
    public EnderecosResponse buscarPorId(Long id) {
        Endereco end = enderecosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        return converterParaResponse(end);
    }

    @Override
    public List<EnderecosResponse> listarTodos() {
        List<Endereco> enderecos = enderecosRepository.findAll();
        return enderecos.stream().map(this::converterParaResponse).toList();
    }

    @Override
    public EnderecosResponse atualizarEnderecos(Long id, EnderecosRequest request) {
        Endereco end = enderecosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        TipoEndereco tipoEndereco = tipoEnderecoRepository.findById(request.getTipoEnderecoId())
                .orElseThrow(() -> new RuntimeException("Tipo de endereço não encontrado"));

        end.setCliente(cliente);
        end.setTipoEndereco(tipoEndereco);
        end.setCep(request.getCep());
        end.setRua(request.getRua());
        end.setNumero(request.getNumero());
        end.setComplemento(request.getComplemento());
        end.setBairro(request.getBairro());
        end.setCidade(request.getCidade());
        end.setEstado(request.getEstado());
        end.setPrincipal(request.getPrincipal());

        enderecosRepository.save(end);

        return converterParaResponse(end);
    }

    @Override
    public void deletarEndereco(Long id) {
        if (!enderecosRepository.existsById(id)) {
            throw new RuntimeException("Endereço não encontrado");
        }

        enderecosRepository.deleteById(id);
    }

    private EnderecosResponse converterParaResponse(Endereco end) {
        EnderecosResponse response = new EnderecosResponse();
        response.setId(end.getId());
        response.setCep(end.getCep());
        response.setRua(end.getRua());
        response.setNumero(end.getNumero());
        response.setComplemento(end.getComplemento());
        response.setBairro(end.getBairro());
        response.setCidade(end.getCidade());
        response.setEstado(end.getEstado());
        response.setPrincipal(end.getPrincipal());
        response.setDataCadastro(end.getDataCadastro());
        response.setDataAtualizacao(end.getDataAtualizacao());

        return response;
    }
}