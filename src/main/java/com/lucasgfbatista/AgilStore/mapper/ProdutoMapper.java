package com.lucasgfbatista.AgilStore.mapper;

import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.domain.Produto;
import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.exception.ResourceNotFoundException;
import com.lucasgfbatista.AgilStore.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final CategoriaRepository categoriaRepository;

    public ProdutoMapper(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Categoria",
                                "id",
                                dto.categoriaId())
                );

        produto.setCategoria(categoria);
        produto.setQntEstoque(dto.qntEstoque());
        produto.setPreco(dto.preco());


        return produto;
    }


    public ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria() != null ? produto.getCategoria().getId() : null,
                produto.getQntEstoque(),
                produto.getPreco()

        );
    }
}
