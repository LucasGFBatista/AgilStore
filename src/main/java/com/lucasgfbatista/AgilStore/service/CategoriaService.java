package com.lucasgfbatista.AgilStore.service;

import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.exception.ResourceNotFoundException;
import com.lucasgfbatista.AgilStore.mapper.CategoriaMapper;
import com.lucasgfbatista.AgilStore.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    - [x] - Lista todos
    - [x] - Atualizar por id
    - [ ] - Deletar por id
    - [ ] - Buscar por id
    - [ ] - Buscar por nome
    * */


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto) {

        Categoria categoria = categoriaMapper.toEntity(dto);

        categoriaRepository.save(categoria);

        return categoriaMapper.toResponse(categoria);
    }

    public List<CategoriaResponseDTO> listarTodosCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponseDTO atualiarCategoria(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        categoria.setNome(dto.nome());

        categoriaRepository.save(categoria);

        return categoriaMapper.toResponse(categoria);

    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "id", id);
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaResponseDTO buscarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        return categoriaMapper.toResponse(categoria);
    }


    public CategoriaResponseDTO buscarCategoria(String nome) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", nome)
                );

        return categoriaMapper.toResponse(categoria);
    }

}
