package com.lucasgfbatista.AgilStore.mapper;

import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {


    public Categoria toEntity(CategoriaRequestDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        return categoria;
    }


    public CategoriaResponseDTO toResponse(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
