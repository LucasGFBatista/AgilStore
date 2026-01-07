package com.lucasgfbatista.AgilStore.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

@Order(1)
public class JsonToDatabaseInitializer<T, E> implements ApplicationRunner {

    private final JsonStorageService<T> jsonStorageService;
    private final JpaRepository<E, Long> repository;
    private final Function<T, E> mapper;

    public JsonToDatabaseInitializer(
            JsonStorageService<T> jsonStorageService,
            JpaRepository<E, Long> repository,
            Function<T, E> mapper
    ) {
        this.jsonStorageService = jsonStorageService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void run(ApplicationArguments args) {

        // Se o banco já tiver dados, não duplicar
        if (repository.count() > 0) return;

        List<T> dados = jsonStorageService.carregar();
        if (dados.isEmpty()) return;

        for (T dto : dados) {
            E entity = mapper.apply(dto);
            repository.save(entity);
        }
    }
}
