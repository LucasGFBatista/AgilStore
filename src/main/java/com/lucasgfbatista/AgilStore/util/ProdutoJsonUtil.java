package com.lucasgfbatista.AgilStore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasgfbatista.AgilStore.domain.Produto;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProdutoJsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File arquivo = new File("produtos.json");
    
    public static void salvarEmJson(List<Produto> produtos) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Produto> carregarDoJson() {
        try {
            if (!arquivo.exists()) return List.of();
            Produto[] produtos = mapper.readValue(arquivo, Produto[].class);
            return Arrays.asList(produtos);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
