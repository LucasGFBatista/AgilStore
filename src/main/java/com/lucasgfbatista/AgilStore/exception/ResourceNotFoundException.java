package com.lucasgfbatista.AgilStore.exception;


public class ResourceNotFoundException extends RuntimeException {

    private final String recurso;
    private final String campo;
    private final Object valor;

    public ResourceNotFoundException(
            String recurso,
            String campo,
            Object valor
    ) {
        super(recurso + " não encontradeo com " + campo + ": " + valor);
        this.recurso = recurso;
        this.campo = campo;
        this.valor = valor;
    }

    public ResourceNotFoundException(String mensagem, String recurso, String campo, Object valor) {
        super(mensagem);
        this.recurso = recurso;
        this.campo = campo;
        this.valor = valor;
    }

    public ResourceNotFoundException(String mensagem, Throwable cause, String recurso, String campo, Object valor) {
        super(mensagem, cause);
        this.recurso = recurso;
        this.campo = campo;
        this.valor = valor;
    }

    public ResourceNotFoundException(Throwable cause, String recurso, String campo, Object valor) {
        super(cause);
        this.recurso = recurso;
        this.campo = campo;
        this.valor = valor;
    }


}