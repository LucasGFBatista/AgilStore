package com.lucasgfbatista.AgilStore.repository;

import com.lucasgfbatista.AgilStore.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNomeIgnoreCase(String nome);
    Optional<Produto> findByCategoriaNomeIgnoreCase(String categoriaNome);
}
