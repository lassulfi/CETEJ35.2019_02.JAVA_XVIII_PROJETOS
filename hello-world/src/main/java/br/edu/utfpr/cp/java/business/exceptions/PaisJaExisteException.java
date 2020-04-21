package br.edu.utfpr.cp.java.business.exceptions;

public class PaisJaExisteException extends Exception {
    private static final long serialVersionUID = 1L;

    public PaisJaExisteException() {
        super("País já cadastrado.");
    }
}