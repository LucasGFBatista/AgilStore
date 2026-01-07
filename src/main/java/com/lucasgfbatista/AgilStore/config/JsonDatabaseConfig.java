package com.lucasgfbatista.AgilStore.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucasgfbatista.AgilStore.domain.Categoria;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.repository.CategoriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucasgfbatista.AgilStore.util.*;

import java.util.List;

@Configuration
public class JsonDatabaseConfig {

    private static final String CAMINHO_ARQUIVO = "json_projeto/";

    @Bean
    public JsonToDatabaseInitializer<CategoriaResponseDTO, Categoria> categoriaJsonLoader(
            CategoriaRepository categoriaRepository
    ) {
        JsonStorageService<CategoriaResponseDTO> categoriaJsonStorage =
                new JsonStorageServiceImpl<>(
                        CAMINHO_ARQUIVO+"categorias.json",
                        new TypeReference<>() {}
                );

        return new JsonToDatabaseInitializer<>(
                categoriaJsonStorage,
                categoriaRepository,
                dto -> {
                    Categoria c = new Categoria();
                    c.setId(dto.id());
                    c.setNome(dto.nome());
                    return c;
                }
        );
    }



}
