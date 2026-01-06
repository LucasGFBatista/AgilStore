package com.lucasgfbatista.AgilStore.controller;

import com.lucasgfbatista.AgilStore.service.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /* todo
     * - [ ] - POST - CRIAR
     * - [ ] - GET - LISTAR TODOS
     * - [ ] - GET - BUSCAR POR ID
     * - [ ] - GET - BUSCAR POR NOME
     * - [ ] - PUT - ATUALIZAR POR ID
     * - [ ] - DELETE - DELETAR POR ID
     * */
}
