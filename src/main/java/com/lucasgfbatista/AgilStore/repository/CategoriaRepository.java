package com.lucasgfbatista.AgilStore.repository;

import com.lucasgfbatista.AgilStore.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNomeIgnoreCase();

}
