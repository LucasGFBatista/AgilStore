package com.lucasgfbatista.AgilStore.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.exception.ResourceNotFoundException;
import com.lucasgfbatista.AgilStore.mapper.CategoriaMapper;
import com.lucasgfbatista.AgilStore.repository.CategoriaRepository;
import com.lucasgfbatista.AgilStore.util.JsonStorageService;
import com.lucasgfbatista.AgilStore.util.JsonStorageServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private static final String JSON_CATEGORIAS = "json_projeto/categorias.json";
    private final JsonStorageService<CategoriaResponseDTO> categoriaJsonStorage = new JsonStorageServiceImpl<>(
            JSON_CATEGORIAS, new TypeReference<List<CategoriaResponseDTO>>() {
    }
    );
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
    - [x] - Deletar por id
    - [x] - Buscar por id
    - [x] - Buscar por nome
    * */


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto) {

        Categoria categoria = categoriaMapper.toEntity(dto);

        categoriaRepository.save(categoria);
        salvarCategoriasJson();
        return categoriaMapper.toResponse(categoria);
    }

    public List<CategoriaResponseDTO> listarTodosCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        categoria.setNome(dto.nome());

        categoriaRepository.save(categoria);

        salvarCategoriasJson();
        return categoriaMapper.toResponse(categoria);

    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "id", id);
        }
        categoriaRepository.deleteById(id);
        salvarCategoriasJson();
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


    // Trabalhar com Json

    private void salvarCategoriasJson() {
        List<CategoriaResponseDTO> categorias = listarTodosCategorias();
        categoriaJsonStorage.salvar(categorias);
    }

}
