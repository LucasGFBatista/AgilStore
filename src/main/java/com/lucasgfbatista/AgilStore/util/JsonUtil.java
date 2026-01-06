package com.lucasgfbatista.AgilStore.util;


import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void salvar(String caminhoArquivo, T objeto) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminhoArquivo), objeto);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar JSON: " + e.getMessage(), e);
        }
    }


}
