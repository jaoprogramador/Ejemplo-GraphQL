package com.jao.spring.graphql.exception;

public class RecetaNotFound extends RuntimeException {
    public RecetaNotFound(String mensaje) {
        super(mensaje);
    }
}
