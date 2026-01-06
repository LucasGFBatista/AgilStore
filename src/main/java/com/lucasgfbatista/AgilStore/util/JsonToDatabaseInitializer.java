package com.lucasgfbatista.AgilStore.json;

import com.lucasgfbatista.AgilStore.util.JsonStorageService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

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

   
}
