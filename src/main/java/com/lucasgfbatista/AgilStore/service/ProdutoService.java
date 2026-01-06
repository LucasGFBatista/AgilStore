package com.lucasgfbatista.AgilStore.service;

import com.lucasgfbatista.AgilStore.domain.Produto;
import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.exception.ResourceNotFoundException;
import com.lucasgfbatista.AgilStore.mapper.ProdutoMapper;
import com.lucasgfbatista.AgilStore.repository.ProdutoRepository;
import com.lucasgfbatista.AgilStore.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private static final String JSON_PRODUTOS = "json_projeto/produtos.json";
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    /* todo

    - [x] - Criar
    - [x] - Lista todos
    - [x] - Atualizar por id
    - [x] - Deletar por id
    - [x] - Buscar por id
    - [x] - Buscar por nome
    * */


    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {

        Produto produto = produtoMapper.toEntity(dto);

        produtoRepository.save(produto);
        salvarBackupJson();
        return produtoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDTO> listarTodosProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toResponse)
                .toList();
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Produto", "id", id)
                );

        produto.setNome(dto.nome());
        produto.setQntEstoque(dto.qntEstoque());
        produto.setPreco(dto.preco());
        produto.setCategoria(produtoMapper.toEntity(dto).getCategoria());

        produtoRepository.save(produto);
        salvarBackupJson();
        return produtoMapper.toResponse(produto);

    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto", "id", id);
        }
        produtoRepository.deleteById(id);
        salvarBackupJson();
    }

    public ProdutoResponseDTO buscarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Produto", "id", id)
                );

        return produtoMapper.toResponse(produto);
    }


    public ProdutoResponseDTO buscarProduto(String nome) {
        Produto produto = produtoRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Produto", "id", nome)
                );

        return produtoMapper.toResponse(produto);
    }

    private void salvarBackupJson(){
        List<ProdutoResponseDTO> produtos = listarTodosProdutos();
        JsonUtil.salvar(JSON_PRODUTOS, produtos );
    }
}
