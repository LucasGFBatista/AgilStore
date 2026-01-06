package com.lucasgfbatista.AgilStore.util;

import com.lucasgfbatista.AgilStore.domain.Produto;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProdutoJsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File arquivo = new File("produto.json");

    public static void salvarEmJson(List<Produto> produtos) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
