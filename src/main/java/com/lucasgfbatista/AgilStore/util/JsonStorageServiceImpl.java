package com.lucasgfbatista.AgilStore.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class JsonStorageServiceImpl<T> implements JsonStorageService<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String caminhoArquivo;
    private final TypeReference<List<T>> typeReference;

    public JsonStorageServiceImpl(
            String caminhoArquivo,
            TypeReference<List<T>> typeReference
    ) {
        this.caminhoArquivo = caminhoArquivo;
        this.typeReference = typeReference;
    }

    @Override
    public void salvar(List<T> dados) {
        try {
            File arquivo = new File(caminhoArquivo);

            File pasta = arquivo.getParentFile();
            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs();
            }

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivo, dados);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Json", e);

        }
    }

    @Override
    public List<T> carregar() {
        try {
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()) {
                return Collections.emptyList();
            }

            return mapper.readValue(arquivo, typeReference);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler Json", e);

        }
    }
}
