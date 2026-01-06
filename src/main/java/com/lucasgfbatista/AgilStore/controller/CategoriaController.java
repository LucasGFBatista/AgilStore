package com.lucasgfbatista.AgilStore.controller;

import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public CategoriaResponseDTO criarCategoria(@RequestParam CategoriaRequestDTO dto) {
        return categoriaService.criarCategoria(dto);
    }
    
    
    @GetMapping(value = {"/", ""})
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaService.listarTodosCategorias();
    }



    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaRequestDTO dto) {

        return categoriaService.atualizarCategoria(id, dto);

    }
    
    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
    }
}
