package com.lucasgfbatista.AgilStore.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        long id,
        String nome,
        long categoriaId,
        Integer qntEstoque,
        BigDecimal preco
) {
}
