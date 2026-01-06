package com.lucasgfbatista.AgilStore.util;


import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void salvar(String caminhoArquivo, T objeto) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminhoArquivo), objeto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar JSON: " + e.getMessage(), e);
        }
    }

    public static <T> T ler(String caminhoArquivo, Class<T> classe){
        try {
            File arquivo = new File(caminhoArquivo);

            if(!arquivo.exists()){
                return null;
            }
            return mapper.readValue(arquivo, classe);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler JSON: " + e.getMessage(), e);
        }
    }
}
