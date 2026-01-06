package com.lucasgfbatista.AgilStore.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void salvar(String caminhoArquivo, T objeto) {
        try {
            File arquivo = new File(caminhoArquivo);


            File pasta = arquivo.getParentFile();
            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs();
            }

            mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(arquivo, objeto);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar JSON: " + caminhoArquivo,
                    e
            );
        }
    }
}
