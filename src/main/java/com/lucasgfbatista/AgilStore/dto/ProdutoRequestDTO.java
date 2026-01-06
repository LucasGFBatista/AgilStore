package com.lucasgfbatista.AgilStore.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO (
        String nome,
        long categoriaId,
        Integer qntEstoque,
        BigDecimal preco
) {
}
