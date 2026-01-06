package com.lucasgfbatista.AgilStore.util;

import java.util.List;

public interface JsonStorageService<T>{

    void salvar(List<T> dados);

    List<T> carregar();

}
