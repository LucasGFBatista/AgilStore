package com.lucasgfbatista.AgilStore.service;

import com.lucasgfbatista.AgilStore.domain.Produto;
import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.mapper.ProdutoMapper;
import com.lucasgfbatista.AgilStore.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

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
    - [ ] - Lista todos
    - [ ] - Atualizar por id
    - [ ] - Deletar por id
    - [ ] - Buscar por id
    - [ ] - Buscar por nome
    * */


    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {

        Produto produto = produtoMapper.toEntity(dto);

        produtoRepository.save(produto);

        return produtoMapper.toResponse(produto);
    }

    
}
