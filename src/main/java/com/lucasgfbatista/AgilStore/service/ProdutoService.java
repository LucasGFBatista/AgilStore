package com.lucasgfbatista.AgilStore.service;

import com.lucasgfbatista.AgilStore.domain.Produto;
import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.exception.ResourceNotFoundException;
import com.lucasgfbatista.AgilStore.mapper.ProdutoMapper;
import com.lucasgfbatista.AgilStore.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class ProdutoService {

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
    - [ ] - Deletar por id
    - [ ] - Buscar por id
    - [ ] - Buscar por nome
    * */


    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {

        Produto produto = produtoMapper.toEntity(dto);

        produtoRepository.save(produto);

        return produtoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDTO> listarTodosProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toResponse)
                .toList();
    }

    public ProdutoResponseDTO atualiarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Produto", "id", id)
                );

        produto.setNome(dto.nome());
        produto.setQntEstoque(dto.qntEstoque());
        produto.setPreco(dto.preco());
        produto.setCategoria(produtoMapper.toEntity(dto).getCategoria());

        produtoRepository.save(produto);

        return produtoMapper.toResponse(produto);

    }
}
