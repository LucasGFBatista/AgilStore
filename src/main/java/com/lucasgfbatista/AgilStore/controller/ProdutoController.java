package com.lucasgfbatista.AgilStore.controller;

import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /* todo
     * - [x] - POST - CRIAR
     * - [x] - GET - LISTAR TODOS
     * - [x] - GET - BUSCAR POR ID
     * - [x] - GET - BUSCAR POR NOME
     * - [ ] - PUT - ATUALIZAR POR ID
     * - [ ] - DELETE - DELETAR POR ID
     * */


    @PostMapping
    public ProdutoResponseDTO criarProduto(@RequestBody ProdutoRequestDTO dto) {
        return produtoService.criarProduto(dto);
    }

    @GetMapping(value = {"/", ""})
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoService.listarTodosProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarProduto(@PathVariable Long id){
        return produtoService.buscarProduto(id);
    }

    @GetMapping("/{nome")
    public ProdutoResponseDTO buscarProduto(@PathVariable String nome{
        return produtoService.buscarProduto(nome);
    }

}
