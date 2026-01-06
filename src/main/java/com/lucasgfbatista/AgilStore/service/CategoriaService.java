package com.lucasgfbatista.AgilStore.service;

import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.mapper.CategoriaMapper;
import com.lucasgfbatista.AgilStore.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    /* todo

    - [x] - Criar
    - [ ] - Lista todos
    - [ ] - Atualizar por id
    - [ ] - Deletar por id
    - [ ] - Buscar por id
    - [ ] - Buscar por nome
    * */


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto) {

        Categoria categoria = categoriaMapper.toEntity(dto);

        categoriaRepository.save(categoria);

        return categoriaMapper.toResponse(categoria);
    }
}
